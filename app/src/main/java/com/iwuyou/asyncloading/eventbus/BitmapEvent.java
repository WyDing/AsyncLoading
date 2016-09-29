package com.iwuyou.asyncloading.eventbus;

import android.graphics.Bitmap;

/**
 * Created by duanding on 16/9/28.
 */

public class BitmapEvent {
    private Bitmap bitmap;

    public BitmapEvent(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
