package com.sarahjting.roost.common.image.transformations;

import com.sarahjting.roost.common.image.ImageTransformerRequest;
import org.im4java.core.IMOperation;

// https://imagemagick.org/script/command-line-options.php#background
public class BackgroundTransformation implements Transformation {
    ImageTransformerRequest request;

    public BackgroundTransformation(ImageTransformerRequest request) {
        this.request = request;
    }

    @Override
    public boolean check() {
        return this.request.has("b");
    }

    @Override
    public void transform(IMOperation op) {
        String color = this.request.getFirst("b");
        if (color.startsWith("rgb:")) {
            op.background(String.format("#%s", color.substring(4)));
        } else {
            op.background(color);
        }
    }
}
