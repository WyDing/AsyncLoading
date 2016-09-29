package com.iwuyou.asyncloading.handler;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.iwuyou.asyncloading.ImageDownLoadThread;


/**
 * Created by duanding on 16/9/26.
 */
public class AsyncHandlerPresenter implements AsyncHandlerContract.Presenter, ImageDownLoadThread.OnDownLoadFinish {

    private AsyncHandlerContract.View mView;
    private ImageDownLoadThread thread;

    public AsyncHandlerPresenter(@Nullable AsyncHandlerContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
        thread = new ImageDownLoadThread();
        thread.setDownLoadFinish(this);
    }

    @Override
    public void downLoadImage() {
        thread.start();
    }


    @Override
    public void onSuccess(Bitmap bitmap) {
        mView.updateImage(bitmap);
        mView.onLoadingSuccess();
    }

    @Override
    public void onError(int errorCode, String error) {
        mView.onLoadingFail(errorCode,error);
    }
}
