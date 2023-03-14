package com.sarahjting.roost.common.image.transformations;

import org.im4java.core.IMOperation;


public interface Transformation {
    public boolean check();
    public void transform(IMOperation op);
}
