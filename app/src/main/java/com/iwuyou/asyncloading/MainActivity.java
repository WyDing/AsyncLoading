package com.iwuyou.asyncloading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.iwuyou.asyncloading.AsyncLoadingActivity.EXTRA_ASYNCLOADING_MODE;
import static com.iwuyou.asyncloading.AsyncLoadingActivity.EXTRA_ASYNCLOADING_TYPE_ASYNCTASK;
import static com.iwuyou.asyncloading.AsyncLoadingActivity.EXTRA_ASYNCLOADING_TYPE_EVENTBUS;
import static com.iwuyou.asyncloading.AsyncLoadingActivity.EXTRA_ASYNCLOADING_TYPE_HANDLER;
import static com.iwuyou.asyncloading.AsyncLoadingActivity.EXTRA_ASYNCLOADING_TYPE_LOADER;
import static com.iwuyou.asyncloading.AsyncLoadingActivity.EXTRA_ASYNCLOADING_TYPE_RUNONUITHREAD;
import static com.iwuyou.asyncloading.AsyncLoadingActivity.EXTRA_ASYNCLOADING_TYPE_RXJAVA;
import static com.iwuyou.asyncloading.AsyncLoadingActivity.EXTRA_ASYNCLOADING_TYPE_VIEWPOST;

/**
 * Android 异步加载的几种实现方式
 * 1.使用Handler.
 * 2.AsyncTask
 * 3.Activity.runOnUiThread
 * 4.Loader
 * 5.View.post
 * 6.Rxjava
 * 7.EventBus
 * 8.agera
 * 本实例主要说明Android异步加载更新UI的几种方式,代码规范在每个小块严格试用MVP设计模式,使得在阅读过程较为轻松,
 * 这样导致重复代码较多,当然也可以将部分代码再次抽离,例如可以将所有的Contract都抽离出来让所有模块使用。
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.asyncloading_handler)
    Button asyncloadingHandler;
    @Bind(R.id.asyncloading_asynctask)
    Button asyncloadingAsynctask;
    @Bind(R.id.asyncloading_loader)
    Button asyncloadingLoader;
    @Bind(R.id.asyncloading_post)
    Button asyncloadingPost;
    @Bind(R.id.asyncloading_runonuithread)
    Button asyncloadingRunonuithread;
    @Bind(R.id.asyncloading_rxjava)
    Button asyncloadingRxjava;
    @Bind(R.id.asyncloading_eventbus)
    Button asyncloadingEventbus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.asyncloading_handler, R.id.asyncloading_asynctask, R.id.asyncloading_loader, R.id.asyncloading_post, R.id.asyncloading_runonuithread, R.id.asyncloading_rxjava, R.id.asyncloading_eventbus})
    public void onClick(View view) {
        Intent intent = new Intent(this,AsyncLoadingActivity.class);
        switch (view.getId()) {
            case R.id.asyncloading_handler:
                intent.putExtra(EXTRA_ASYNCLOADING_MODE,EXTRA_ASYNCLOADING_TYPE_HANDLER);
                break;
            case R.id.asyncloading_asynctask:
                intent.putExtra(EXTRA_ASYNCLOADING_MODE,EXTRA_ASYNCLOADING_TYPE_ASYNCTASK);
                break;
            case R.id.asyncloading_loader:
                intent.putExtra(EXTRA_ASYNCLOADING_MODE,EXTRA_ASYNCLOADING_TYPE_LOADER);
                break;
            case R.id.asyncloading_post:
                intent.putExtra(EXTRA_ASYNCLOADING_MODE,EXTRA_ASYNCLOADING_TYPE_VIEWPOST);
                break;
            case R.id.asyncloading_runonuithread:
                intent.putExtra(EXTRA_ASYNCLOADING_MODE,EXTRA_ASYNCLOADING_TYPE_RUNONUITHREAD);
                break;
            case R.id.asyncloading_rxjava:
                intent.putExtra(EXTRA_ASYNCLOADING_MODE,EXTRA_ASYNCLOADING_TYPE_RXJAVA);
                break;
            case R.id.asyncloading_eventbus:
                intent.putExtra(EXTRA_ASYNCLOADING_MODE,EXTRA_ASYNCLOADING_TYPE_EVENTBUS);
                break;
        }
        startActivity(intent);
    }


}
