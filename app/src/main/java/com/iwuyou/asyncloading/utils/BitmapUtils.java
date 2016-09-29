package com.iwuyou.asyncloading.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by duanding on 16/9/26.
 */
public class BitmapUtils {
    /**
     * 加载本地图片
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 下载图片返回对应的code以及结果
     * @param url
     * @return
     */
    public static Pair<Bitmap,Integer> getServerBitmapForResult(String url){
        Bitmap bmp = null;
        try {
            URL imgUrl = new URL(url);
            //打开连接
            URLConnection con = imgUrl.openConnection();
            InputStream in = con.getInputStream();
            bmp = BitmapFactory.decodeStream(in);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Pair.create(null,1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return Pair.create(null,2);
        }
        return Pair.create(bmp,0);
    }

    /**
     * 直接返回结果,忽视对应的code
     * @param url
     * @return
     */
    public static Bitmap getServerBitmap(String url){
        return getServerBitmapForResult(url).first;
    }
}
