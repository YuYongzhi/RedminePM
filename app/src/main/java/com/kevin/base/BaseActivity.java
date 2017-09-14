package com.kevin.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * <p>
 * - Created by: maitao.
 * <br>
 * -       Date: 17-9-13.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getContentViewId());
        onCreateBase(savedInstanceState);
    }

    protected abstract @LayoutRes int getContentViewId();
    protected abstract void onCreateBase(Bundle savedInstanceState);

}
