package com.iwuyou.asyncloading;

import android.graphics.Bitmap;
import android.util.Pair;

import com.iwuyou.asyncloading.utils.BitmapUtils;

/**
 * 下载图片线程
 * Created by duanding on 16/9/27.
 */
public class ImageDownLoadThread extends Thread {
    public static final String IMAGE_URL = "http://upload.jianshu.io/users/upload_avatars/2379298/5cbe73156cc0.jpg";
    private volatile boolean isStart = false;
    private OnDownLoadFinish downLoadFinish;

    @Override
    public void run() {
        isStart = true;
        Pair<Bitmap,Integer> b = downloadImage();
        if (null != downLoadFinish) {
            if (null != b) {
                downLoadFinish.onSuccess(b.first);
            } else {
                downLoadFinish.onError(b.second, "下载失败");
            }
        }
    }

    public boolean isStart() {
        return isStart;
    }

    @Override
    public synchronized void start() {
        if (!isStart) {
            super.start();
        }
    }

    public Pair<Bitmap,Integer> downloadImage(){
        return BitmapUtils.getServerBitmapForResult(IMAGE_URL);
    }



    public static interface OnDownLoadFinish {
        void onSuccess(Bitmap bitmap);

        void onError(int errorCode, String error);
    }

    public void setDownLoadFinish(OnDownLoadFinish downLoadFinish) {
        this.downLoadFinish = downLoadFinish;
    }
}
