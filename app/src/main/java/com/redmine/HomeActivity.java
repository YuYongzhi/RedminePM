package com.redmine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.kevin.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private List<Fragment> mFragments;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreateBase(Bundle savedInstanceState) {
        mFragments = new ArrayList<>();
        mFragments.addAll(getFragments());

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottomNavigationBar);
        bottomNavigationBar.clearAll();

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .addItem(
                        new BottomNavigationItem(R.mipmap.ic_launcher, "首页")
                                .setActiveColorResource(R.color.colorPrimary)
                )
                .addItem(
                        new BottomNavigationItem(R.mipmap.ic_launcher, "设置")
                                .setActiveColorResource(R.color.colorSettingPrimary)
                )
                .setFirstSelectedPosition(0)
                .initialise();

        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_layout, mFragments.get(0));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(Fragment.instantiate(this, HomeFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, SettingFragment.class.getName()));
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {
        if (mFragments != null) {
            if (position < mFragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = mFragments.get(position);
                if (!fragment.isAdded()) {
                    ft.add(R.id.content_layout, fragment);
                }
                ft.show(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (mFragments != null) {
            if (position < mFragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = mFragments.get(position);
                if (!fragment.isAdded()) {
                    ft.add(R.id.content_layout, fragment);
                }
                ft.hide(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}
