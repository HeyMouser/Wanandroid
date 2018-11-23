package com.yh.wanandroid.ui.project;

import com.yh.wanandroid.R;
import com.yh.wanandroid.base.BaseFragment;

public class ProjectFragment extends BaseFragment {
    private static ProjectFragment projectFragment;

    public static ProjectFragment getInstance() {
        if (projectFragment == null)
            projectFragment = new ProjectFragment();
        return projectFragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_project;
    }

    @Override
    public void initData() {

    }
}