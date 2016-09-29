package com.iwuyou.asyncloading.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Pair;

import com.iwuyou.asyncloading.ImageDownLoadThread;
import com.iwuyou.asyncloading.utils.BitmapUtils;

/**
 * Created by duanding on 16/9/27.
 */

public class AsyncTaskPresenter implements AsyncTaskContract.Presenter{

    private AsyncTaskContract.View mView;

    public AsyncTaskPresenter(AsyncTaskContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void downLoadImage() {
       new AsyncTask<Void,Integer,Pair<Bitmap,Integer>>(){

           @Override
           protected Pair<Bitmap, Integer> doInBackground(Void... params) {
               return BitmapUtils.getServerBitmapForResult(ImageDownLoadThread.IMAGE_URL);
           }

           @Override
           protected void onPostExecute(Pair<Bitmap, Integer> bitmapIntegerPair) {
               if(0 == bitmapIntegerPair.second){
                   mView.updateImage(bitmapIntegerPair.first);
               }else {
                   mView.onLoadingFail(bitmapIntegerPair.second,"下载失败");
               }
           }
       }.execute();
    }
}
