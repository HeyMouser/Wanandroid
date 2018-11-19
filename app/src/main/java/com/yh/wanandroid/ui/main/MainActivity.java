package com.yh.wanandroid.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.yh.wanandroid.R;
import com.yh.wanandroid.base.BaseActivity;
import com.yh.wanandroid.ui.dashboard.DashboardFragment;
import com.yh.wanandroid.ui.mine.MineFragment;
import com.yh.wanandroid.ui.project.ProjectFragment;
import com.yh.wanandroid.ui.wx.WxFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigationView)
    BottomNavigationView navigationView;

    private List<Fragment> fragments;
    private int lastIndex;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    selectFragment(0);
                    break;
                case R.id.navigation_dashboard:
                    selectFragment(1);
                    break;
                case R.id.navigation_wx:
                    selectFragment(2);
                    break;
                case R.id.navigation_project:
                    selectFragment(3);
                    break;
                case R.id.navigation_me:
                    selectFragment(4);
                    break;
            }

            return true;
        }
    };

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    @Override
    protected void initData() {
        initFragment();
        selectFragment(0);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.getInstance());
        fragments.add(DashboardFragment.getInstance());
        fragments.add(WxFragment.getInstance());
        fragments.add(ProjectFragment.getInstance());
        fragments.add(MineFragment.getInstance());
    }

    private void selectFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragments.get(index);
        Fragment lastFragment = fragments.get(lastIndex);
        lastIndex = index;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.frame_layout, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }
}
