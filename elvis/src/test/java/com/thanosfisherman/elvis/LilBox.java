package com.thanosfisherman.elvis;

/**
 * Created by thanos on 12/22/17.
 */

public final class LilBox {

    private boolean isBoolean;
    private double iamDouble;

    public LilBox(boolean isBoolean, double iamDouble) {
        this.isBoolean = isBoolean;
        this.iamDouble = iamDouble;
    }

    public LilBox() {
    }

    public boolean isBoolean() {
        return isBoolean;
    }

    public double getIamDouble() {
        return iamDouble;
    }
}
