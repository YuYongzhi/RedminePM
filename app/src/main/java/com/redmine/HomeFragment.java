package com.redmine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.kevin.base.BaseFragment;

/**
 * <p>
 * - Created by: maitao.
 * <br>
 * -       Date: 17-9-14.
 */

public class HomeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }
}
