package com.thanosfisherman.elvis;

/**
 * Created by thanos on 12/22/17.
 */

public final class BigBox {
    private LilBox lilBox;

    public BigBox(LilBox lilBox) {
        this.lilBox = lilBox;
    }

    public BigBox() {
    }

    public LilBox getLilBox() {
        return lilBox;
    }
}
