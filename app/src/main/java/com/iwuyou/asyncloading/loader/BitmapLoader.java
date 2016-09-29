package com.iwuyou.asyncloading.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.iwuyou.asyncloading.ImageDownLoadThread;
import com.iwuyou.asyncloading.utils.BitmapUtils;

import static android.util.Log.e;

/**
 * Created by duanding on 16/9/27.
 */

public class BitmapLoader extends AsyncTaskLoader<Bitmap>{
    Bitmap result;
    public BitmapLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        /**
         * 如果需要获取的数据已经存在，则直接调用deliverResult，
         * deliverResult会触发onLoadeFinished回调。
         */
        if (result != null) {
            deliverResult(result);
        } else {
            //必须在启动的时候调用 否则不会调用loadInBackground方法
            forceLoad();
        }

    }

    @Override
    public Bitmap loadInBackground() {
        Bitmap b = BitmapUtils.getServerBitmap(ImageDownLoadThread.IMAGE_URL);
        return b;
    }
}
