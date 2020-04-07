package me.sofiworker.wanandroid.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import me.sofiworker.wanandroid.base.BaseFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 11:56
 */
public class CommonVpAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> mFragmentList;

    public void setFragmentList(List<BaseFragment> fragmentList) {
        this.mFragmentList = fragmentList;
        notifyDataSetChanged();
    }

    public void addFragment(BaseFragment baseFragment) {
        this.mFragmentList.add(baseFragment);
        notifyDataSetChanged();
    }

    public CommonVpAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }
}
