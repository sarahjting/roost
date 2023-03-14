package com.sarahjting.roost.common.image.transformations;

import com.sarahjting.roost.common.image.ImageTransformerRequest;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SizeTransformation implements Transformation {
    Logger logger = LoggerFactory.getLogger(SizeTransformation.class);
    ImageTransformerRequest request;

    public SizeTransformation(ImageTransformerRequest request) {
        this.request = request;
    }

    @Override
    public boolean check() {
        return this.request.hasInt("h") || this.request.hasInt("w");
    }

    @Override
    public void transform(IMOperation op) {
        String resizeFlag = null;

        if (this.request.has("c")) {
            String resizeOption = this.request.getFirst("c").toLowerCase();
            if (resizeOption.equals("crop")) {
                resizeFlag = null;
            } else if (resizeOption.equals("fill")) {
                resizeFlag = "^";
            } else if (resizeOption.equals("pad")) {
                resizeFlag = ">";
            }
        }

        Integer width = this.request.hasInt("w") ? this.request.getFirstInt("w") : null;
        Integer height = this.request.hasInt("h") ? this.request.getFirstInt("h") : null;

        op.thumbnail(width, height, resizeFlag).extent(width, height);
    }
}
