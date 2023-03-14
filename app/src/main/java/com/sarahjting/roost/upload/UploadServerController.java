package com.sarahjting.roost.upload;

import com.sarahjting.roost.common.image.ImageTransformer;
import com.sarahjting.roost.common.image.ImageTransformerRequest;
import com.sarahjting.roost.upload.services.UploadService;
import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tika.Tika;
import org.im4java.core.IM4JavaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerMapping;

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
    // https://stackoverflow.com/questions/35990095/requestmapping-with-slashes-and-dot
    @GetMapping(value = {
        "/files/{username:[a-zA-Z0-9]+}/**"
    })
    public void file(
        @PathVariable("username") String username,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, IM4JavaException, InterruptedException
    {
        // check if user exists
        Optional<User> user = userService.findOneByUsername(username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // break the path down
        ImageTransformerRequest transformerRequest = new ImageTransformerRequest(
            ((String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE))
                .substring(String.format("/files/%s/", user.get().getUsername()).length())
        );

        // check if upload exists
        Optional<Upload> upload = uploadService.findOneByUserAndFileName(user.get(), transformerRequest.getFileName());
        if (upload.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // TODO: check if file is public first

        // download the file (this should eventually use the private download endpoint instead of the public file)
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet downloadRequest = new HttpGet(upload.get().getFileUrl());
        CloseableHttpResponse externalResponse = client.execute(downloadRequest);
        if (externalResponse.getStatusLine().getStatusCode() != 200) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // TODO: add response headers (cache, etc)

        // format and output the file
        if (upload.get().getType() == UploadType.IMAGE) {
            serveImage(upload.get(), externalResponse.getEntity().getContent(), response, transformerRequest);
        } else {
            serveFile(upload.get(), externalResponse.getEntity().getContent(), response);
        }
    }

    void serveFile(Upload upload, InputStream inputStream, HttpServletResponse response) throws IOException {
        response.setContentType(upload.getMimeType());
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    void serveText() {
        // TODO: determine whether to show code, markdown or plaintext
        // TODO: for html it would be nice to just render it straight to the screen... hmm..
    }

    void serveImage(Upload upload, InputStream inputStream, HttpServletResponse response, ImageTransformerRequest transformerRequest)
            throws IOException, IM4JavaException, InterruptedException {
        if (transformerRequest.getFileExtension() == null) {
            transformerRequest.setFileExtension(upload.getOriginalFileExtension());
        }

        Tika tika = new Tika();
        String mimeType = tika.detect(String.format("-.%s", transformerRequest.getFileExtension()));
        String[] mimeTypeParts = mimeType.split("/");
        if (!mimeTypeParts[0].equals("image")) {
            transformerRequest.setFileExtension("png");
            mimeType = "image/png";
        }

        response.setContentType(mimeType);

        ImageTransformer imageTransformer = new ImageTransformer(transformerRequest);
        imageTransformer.transform(inputStream, response.getOutputStream());
    }
}
