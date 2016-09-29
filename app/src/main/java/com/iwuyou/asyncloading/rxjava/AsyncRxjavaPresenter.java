package com.iwuyou.asyncloading.rxjava;

import android.graphics.Bitmap;

import com.iwuyou.asyncloading.ImageDownLoadThread;
import com.iwuyou.asyncloading.eventbus.BitmapEvent;
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

public class AsyncRxjavaPresenter implements AsyncRxjavaContract.Presenter{

    private AsyncRxjavaContract.View mView;

    public AsyncRxjavaPresenter(AsyncRxjavaContract.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void downLoadImage() {
//
        Observer<Bitmap> bitmapObserver = new Observer<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onLoadingFail(0,"下载失败");
            }

            @Override
            public void onNext(Bitmap bitmap) {
                mView.updateImage(bitmap);
            }
        };

        Observable<Bitmap> observable = Observable.create(new Observable.OnSubscribe<Bitmap>(){
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = BitmapUtils.getServerBitmap(ImageDownLoadThread.IMAGE_URL);
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(bitmapObserver);
    }


}
