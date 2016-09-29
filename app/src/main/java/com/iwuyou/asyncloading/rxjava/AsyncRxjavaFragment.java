package com.iwuyou.asyncloading.rxjava;

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
 * Created by duanding on 16/9/28.
 */

public class AsyncRxjavaFragment extends BaseFragment implements AsyncRxjavaContract.View {

    @Bind(R.id.loading)
    Button loading;
    @Bind(R.id.image)
    ImageView image;

    private AsyncRxjavaContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loading, null);
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
    public void updateImage(Bitmap bitmap) {
        image.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
    }

    @Override
    public void setPresenter(AsyncRxjavaContract.Presenter presenter) {
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
