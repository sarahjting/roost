package com.sarahjting.roost.common.image;

import com.sarahjting.roost.common.image.transformations.Transformation;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.Pipe;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class ImageTransformer {

    Logger logger = LoggerFactory.getLogger(ImageTransformer.class);
    ImageTransformerRequest request;

    Set<Transformation> transformations;

    public ImageTransformer(ImageTransformerRequest request) {
        this.request = request;
        Reflections reflections = new Reflections("com.sarahjting.roost.common.image.transformations", new SubTypesScanner(false));
        transformations = reflections.getSubTypesOf(Transformation.class)
            .stream()
            .map(clazz -> {
                try {
                    return clazz.getConstructor(ImageTransformerRequest.class).newInstance(request);
                    // ok sure java whatever u say
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            })
            .filter(t -> t != null && t.check())
            .collect(Collectors.toSet());
    }

    public void transform(InputStream inputStream, OutputStream outputStream) throws IOException, InterruptedException, IM4JavaException {
        IMOperation op = new IMOperation();
        op.autoOrient();

        transformations.stream().forEach(t -> t.transform(op));

        Pipe pipeIn = new Pipe(inputStream, null);
        Pipe pipeOut = new Pipe(null, outputStream);
        ConvertCmd convert = new ConvertCmd();
        convert.setInputProvider(pipeIn);
        convert.setOutputConsumer(pipeOut);

        op.addImage("-"); // stdin
        op.addImage(request.getFileExtension() == null ? "-" : String.format("%s:-", request.getFileExtension())); // stdout

        logger.info(op.getCmdArgs().toString());
        convert.run(op);
    }
}
