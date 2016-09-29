package com.iwuyou.asyncloading.handler;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.iwuyou.asyncloading.ImageDownLoadThread;
import com.iwuyou.asyncloading.R;
import com.iwuyou.asyncloading.base.BaseFragment;
import com.iwuyou.asyncloading.utils.BitmapUtils;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.util.Log.e;

/**
 * Created by duanding on 16/9/26.
 */
public class AsyncHandlerFragment extends BaseFragment implements AsyncHandlerContract.View {
    @Bind(R.id.loading)
    Button loading;
    @Bind(R.id.image)
    ImageView image;
    private AsyncHandlerContract.Presenter mPresenter;

    private  Handler handler;

    private static class ImageViewHandler extends Handler{
        WeakReference<ImageView> weakImageView;
        public ImageViewHandler(ImageView imageV){
            weakImageView = new WeakReference<ImageView>(imageV);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final ImageView imageView = weakImageView.get();
            Bitmap bitmap = (Bitmap) msg.obj;
            imageView.setImageDrawable(new BitmapDrawable(imageView.getResources(),bitmap));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loading, null);
        ButterKnife.bind(this, rootView);
        handler = new ImageViewHandler(image);
        return rootView;
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadingSuccess() {

    }

    @Override
    public void onLoadingFail(int code, String msg) {

    }

    @Override
    public void updateImage(final Bitmap bitmap) {
        Message msg = handler.obtainMessage(1,bitmap);
        handler.sendMessage(msg);
        /**
         * 也可以在主线程创建handler然后通过post方式更新UI
         */
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                e("","-----------------------------");
//                image.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
//            }
//        });
    }


    @Override
    public void setPresenter(@Nullable AsyncHandlerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.loading)
    public void onClick() {
        mPresenter.downLoadImage();
    }
}
