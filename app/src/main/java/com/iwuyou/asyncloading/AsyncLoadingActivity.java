package com.iwuyou.asyncloading;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.iwuyou.asyncloading.asynctask.AsyncTaskFragment;
import com.iwuyou.asyncloading.asynctask.AsyncTaskPresenter;
import com.iwuyou.asyncloading.base.BaseActivity;
import com.iwuyou.asyncloading.eventbus.AsyncEventBusFragment;
import com.iwuyou.asyncloading.eventbus.AsyncEventBusPresenter;
import com.iwuyou.asyncloading.handler.AsyncHandlerFragment;
import com.iwuyou.asyncloading.handler.AsyncHandlerPresenter;
import com.iwuyou.asyncloading.loader.AsyncLoaderFragment;
import com.iwuyou.asyncloading.runonuithread.AsyncRunOnUIThreadFragment;
import com.iwuyou.asyncloading.runonuithread.AsyncRunOnUIThreadPresenter;
import com.iwuyou.asyncloading.rxjava.AsyncRxjavaFragment;
import com.iwuyou.asyncloading.rxjava.AsyncRxjavaPresenter;
import com.iwuyou.asyncloading.viewpost.AsyncViewpostFragment;
import com.iwuyou.asyncloading.viewpost.AsyncViewpostPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by duanding on 16/9/26.
 */
public class AsyncLoadingActivity extends BaseActivity {

    public static final String EXTRA_ASYNCLOADING_MODE = "EXTRA_ASYNCLOADING_MODE";
    public static final String EXTRA_ASYNCLOADING_TYPE_HANDLER = "EXTRA_ASYNCLOADING_TYPE_HANDLER";
    public static final String EXTRA_ASYNCLOADING_TYPE_ASYNCTASK = "EXTRA_ASYNCLOADING_TYPE_ASYNCTASK";
    public static final String EXTRA_ASYNCLOADING_TYPE_EVENTBUS = "EXTRA_ASYNCLOADING_TYPE_EVENTBUS";
    public static final String EXTRA_ASYNCLOADING_TYPE_LOADER = "EXTRA_ASYNCLOADING_TYPE_LOADER";
    public static final String EXTRA_ASYNCLOADING_TYPE_RUNONUITHREAD = "EXTRA_ASYNCLOADING_TYPE_RUNONUITHREAD";
    public static final String EXTRA_ASYNCLOADING_TYPE_RXJAVA = "EXTRA_ASYNCLOADING_TYPE_RXJAVA";
    public static final String EXTRA_ASYNCLOADING_TYPE_VIEWPOST = "EXTRA_ASYNCLOADING_TYPE_VIEWPOST";

    @Bind(R.id.title)
    TextView mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyncloading);
        ButterKnife.bind(this);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        Intent intent = getIntent();
        if (null != intent) {
            String mode = intent.getStringExtra(EXTRA_ASYNCLOADING_MODE);

            switch (mode) {
                case EXTRA_ASYNCLOADING_TYPE_ASYNCTASK: {
                    AsyncTaskFragment taskFragment = new AsyncTaskFragment();
                    t.replace(R.id.content,taskFragment).commit();
                    new AsyncTaskPresenter(taskFragment);
                    mTitle.setText(getResources().getString(R.string.asyncloading_asynctask));
                    break;
                }
                case EXTRA_ASYNCLOADING_TYPE_HANDLER: {
                    AsyncHandlerFragment handlerFragment = new AsyncHandlerFragment();
                    t.replace(R.id.content, handlerFragment).commit();
                    new AsyncHandlerPresenter(handlerFragment);
                    mTitle.setText(getResources().getString(R.string.asyncloading_handler));
                    break;
                }
                case EXTRA_ASYNCLOADING_TYPE_VIEWPOST: {
                    AsyncViewpostFragment viewpostFragment = new AsyncViewpostFragment();
                    t.replace(R.id.content,viewpostFragment).commit();
                    new AsyncViewpostPresenter(viewpostFragment);
                    mTitle.setText(getResources().getString(R.string.asyncloading_post));
                    break;
                }
                case EXTRA_ASYNCLOADING_TYPE_EVENTBUS: {
                    AsyncEventBusFragment eventBusFragment = new AsyncEventBusFragment();
                    t.replace(R.id.content,eventBusFragment).commit();
                    new AsyncEventBusPresenter(eventBusFragment);
                    mTitle.setText(getString(R.string.asyncloading_eventbus));
                    break;
                }
                case EXTRA_ASYNCLOADING_TYPE_RXJAVA: {
                    AsyncRxjavaFragment rxjavaFragment = new AsyncRxjavaFragment();
                    t.replace(R.id.content,rxjavaFragment).commit();
                    new AsyncRxjavaPresenter(rxjavaFragment);
                    mTitle.setText(getString(R.string.asyncloading_rxjava));
                    break;
                }
                case EXTRA_ASYNCLOADING_TYPE_RUNONUITHREAD: {
                    AsyncRunOnUIThreadFragment runOnUIThreadFragment = new AsyncRunOnUIThreadFragment();
                    t.replace(R.id.content,runOnUIThreadFragment).commit();
                    new AsyncRunOnUIThreadPresenter(runOnUIThreadFragment);
                    mTitle.setText(getResources().getString(R.string.asyncloading_runonuithread));
                    break;
                }
                case EXTRA_ASYNCLOADING_TYPE_LOADER: {
                    AsyncLoaderFragment loaderFragment = new AsyncLoaderFragment();
                    t.add(R.id.content,loaderFragment).commit();
                    mTitle.setText(getResources().getString(R.string.asyncloading_loader));
                    break;
                }
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
