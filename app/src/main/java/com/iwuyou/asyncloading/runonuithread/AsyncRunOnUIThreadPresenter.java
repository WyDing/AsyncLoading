package com.iwuyou.asyncloading.runonuithread;

import android.graphics.Bitmap;

import com.iwuyou.asyncloading.ImageDownLoadThread;

/**
 * Created by duanding on 16/9/27.
 */

public class AsyncRunOnUIThreadPresenter implements AsyncRunOnUIThreadContract.Presenter,ImageDownLoadThread.OnDownLoadFinish{

    private AsyncRunOnUIThreadContract.View mView;
    private ImageDownLoadThread mImageDownLoadThread;

    public AsyncRunOnUIThreadPresenter(AsyncRunOnUIThreadContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
        mImageDownLoadThread = new ImageDownLoadThread();
        mImageDownLoadThread.setDownLoadFinish(this);
    }

    @Override
    public void downLoadImage() {
        mImageDownLoadThread.start();
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
