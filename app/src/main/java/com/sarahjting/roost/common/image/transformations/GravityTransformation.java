package com.sarahjting.roost.common.image.transformations;

import com.sarahjting.roost.common.image.ImageTransformerRequest;
import org.im4java.core.IMOperation;

import java.util.Set;

// https://imagemagick.org/script/command-line-options.php#gravity
public class GravityTransformation implements Transformation {
    ImageTransformerRequest request;

    public static Set<String> GRAVITIES = Set.of(
            "northwest",
            "north",
            "northeast",
            "west",
            "center",
            "east",
            "southwest",
            "south",
            "southeast"
    );

    public GravityTransformation(ImageTransformerRequest request) {
        this.request = request;
    }

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void transform(IMOperation op) {
        String gravity = "center";
        if (this.request.has("g")) {
            String rawGravity = this.request.getFirst("g").toLowerCase();
            if (GRAVITIES.contains(rawGravity)) {
                gravity = rawGravity;
            }
        }

        op.gravity(gravity);
    }
}
