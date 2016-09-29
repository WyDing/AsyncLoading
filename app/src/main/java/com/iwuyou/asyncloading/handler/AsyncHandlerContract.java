package com.iwuyou.asyncloading.handler;

import android.graphics.Bitmap;

import com.iwuyou.asyncloading.base.BasePresenter;
import com.iwuyou.asyncloading.base.BaseView;

/**
 * Created by duanding on 16/9/26.
 */

public class AsyncHandlerContract {
    interface View extends BaseView<Presenter> {
        void onLoading();

        void onLoadingSuccess();

        void onLoadingFail(int code, String msg);

        void updateImage(Bitmap bitmap);
    }

    interface Presenter extends BasePresenter {
        void downLoadImage();
    }
}
