package com.iwuyou.asyncloading.eventbus;

import android.app.usage.UsageEvents;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.iwuyou.asyncloading.R;
import com.iwuyou.asyncloading.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by duanding on 16/9/28.
 */

public class AsyncEventBusFragment extends BaseFragment implements AsyncEventBusContract.View {

    @Bind(R.id.loading)
    Button loading;
    @Bind(R.id.image)
    ImageView image;

    private AsyncEventBusContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loading, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 事件响应方法
     * 接收消息
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BitmapEvent event) {
        image.setImageDrawable(new BitmapDrawable(getResources(),event.getBitmap()));
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
    public void updateImage(Bitmap bitmap) {
        image.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
    }

    @Override
    public void setPresenter(AsyncEventBusContract.Presenter presenter) {
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
