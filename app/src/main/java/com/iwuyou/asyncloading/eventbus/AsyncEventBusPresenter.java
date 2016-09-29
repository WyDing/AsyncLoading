package com.iwuyou.asyncloading.eventbus;

import android.graphics.Bitmap;

import com.iwuyou.asyncloading.ImageDownLoadThread;
import com.iwuyou.asyncloading.utils.BitmapUtils;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by duanding on 16/9/28.
 */

public class AsyncEventBusPresenter implements AsyncEventBusContract.Presenter,ImageDownLoadThread.OnDownLoadFinish{

    private AsyncEventBusContract.View mView;
    private ImageDownLoadThread thread;
    public AsyncEventBusPresenter(AsyncEventBusContract.View view) {
        this.mView = view;
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
        EventBus.getDefault().post(new BitmapEvent(bitmap));
    }

    @Override
    public void onError(int errorCode, String error) {

    }
}
