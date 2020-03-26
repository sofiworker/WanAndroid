package me.sofiworker.wanandroid.adapter;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import me.sofiworker.wanandroid.base.BaseFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/19 9:44
 */
public class MainViewPagerAdapter extends FragmentStateAdapter {

    private List<BaseFragment> mFragmentList;

    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<BaseFragment> fragments) {
        super(fragmentActivity);
        this.mFragmentList = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }
}
