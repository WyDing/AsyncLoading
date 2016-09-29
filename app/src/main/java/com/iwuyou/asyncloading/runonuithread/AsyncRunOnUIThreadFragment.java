package com.iwuyou.asyncloading.runonuithread;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by duanding on 16/9/27.
 */

public class AsyncRunOnUIThreadFragment extends BaseFragment implements AsyncRunOnUIThreadContract.View {
    @Bind(R.id.loading)
    Button loading;
    @Bind(R.id.image)
    ImageView image;
    private AsyncRunOnUIThreadContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loading, null);
        mPresenter = new AsyncRunOnUIThreadPresenter(this);
        ButterKnife.bind(this, rootView);
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
        /**
         * 实际上runOnUiThread方法是使用主线程的mHandler.post方法,和之前的Handler包中注释掉的方法有异曲同工之妙
         */
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
            }
        });
    }

    @Override
    public void setPresenter(AsyncRunOnUIThreadContract.Presenter presenter) {
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
