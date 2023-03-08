package com.sarahjting.roost.upload;

import com.sarahjting.roost.upload.services.UploadService;
import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
public class UploadServerController {
    Logger logger = LoggerFactory.getLogger(UploadServerController.class);

    @Autowired
    UploadService uploadService;

    @Autowired
    UserService userService;

    // https://stackoverflow.com/a/52942866
    // https://stackoverflow.com/questions/5690228/spring-mvc-how-to-return-image-in-responsebody
    // thank u sven and marioosh
    @GetMapping(value = {
        "/files/{username:[a-zA-Z0-9]+}/{fileName:[a-zA-Z0-9-_]+}.{extension:[a-zA-Z0-9]+}",
        "/files/{username:[a-zA-Z0-9]+}/{fileName:[a-zA-Z0-9-_]+}"
    })
    public void file(
        @PathVariable("username") String username,
        @PathVariable("fileName") String fileName,
        @PathVariable(name = "extension", required = false) String extension,
        HttpServletResponse response
    ) throws IOException
    {
        Optional<User> user = userService.findOneByUsername(username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<Upload> upload = uploadService.findOneByUserAndFileName(user.get(), fileName);
        if (upload.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // TODO: check if file is public first

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(upload.get().getFileUrl());
        CloseableHttpResponse externalResponse = client.execute(request);

        if (externalResponse.getStatusLine().getStatusCode() != 200) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // TODO: add response headers (cache, etc)

        serveFile(upload.get(), externalResponse.getEntity().getContent(), response);
    }

    void serveFile(Upload upload, InputStream inputStream, HttpServletResponse response) throws IOException {
        response.setContentType(upload.getMimeType());
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    void serveText() {
        // TODO: determine whether to show code, markdown or plaintext
        // TODO: for html it would be nice to just render it straight to the screen... hmm..
    }

    void serveImage() {
        // TODO: file manipulation
    }
}
