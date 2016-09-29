package com.iwuyou.asyncloading.asynctask;

import android.graphics.Bitmap;

import com.iwuyou.asyncloading.base.BasePresenter;
import com.iwuyou.asyncloading.base.BaseView;
import com.iwuyou.asyncloading.handler.AsyncHandlerContract;

/**
 * Created by duanding on 16/9/27.
 */

public class AsyncTaskContract {
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
