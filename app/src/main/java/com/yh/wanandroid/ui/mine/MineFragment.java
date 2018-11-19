package com.yh.wanandroid.ui.mine;

import com.yh.wanandroid.R;
import com.yh.wanandroid.base.BaseFragment;

public class MineFragment extends BaseFragment {

    private static MineFragment mineFragment;

    public static MineFragment getInstance(){
        if (mineFragment == null)
            mineFragment = new MineFragment();
        return mineFragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_mine;
    }

    @Override
    public int initData() {
        return 0;
    }
}
