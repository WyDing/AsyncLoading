package com.iwuyou.asyncloading.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.iwuyou.asyncloading.R;
import com.iwuyou.asyncloading.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.util.Log.e;

/**
 * Created by duanding on 16/9/27.
 */

public class AsyncLoaderFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Bitmap> {

    @Bind(R.id.loading)
    Button loading;
    @Bind(R.id.image)
    ImageView image;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.loading)
    public void onClick() {
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
        return new BitmapLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Bitmap> loader, Bitmap data) {
        image.setImageDrawable(new BitmapDrawable(getResources(),data));
    }

    @Override
    public void onLoaderReset(Loader<Bitmap> loader) {

    }
}
