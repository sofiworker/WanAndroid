package me.sofiworker.wanandroid.fragment.system;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.adapter.CommonMiAdapter;
import me.sofiworker.wanandroid.adapter.CommonVpAdapter;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.fragment.system.knowledge.KnowledgeFragment;
import me.sofiworker.wanandroid.fragment.system.navigation.NavigationFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 9:57
 */
public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemContract.View {

    @BindView(R.id.mi_system)
    MagicIndicator mSystemMi;

    @BindView(R.id.vp_system)
    ViewPager mSystemVp;

    private List<BaseFragment> mFragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initThings() {
        mPresenter = new SystemPresenter();
        mPresenter.attachView(this);
        initIndicator();
        initVp();
    }

    private void initIndicator() {
        CommonNavigator navigator = new CommonNavigator(getContext());
        navigator.setAdjustMode(true);
        CommonMiAdapter adapter = new CommonMiAdapter();
        adapter.setData(Arrays.asList(getResources().getStringArray(R.array.system_array)));
        adapter.setPageListener(pos -> mSystemVp.setCurrentItem(pos));
        navigator.setAdapter(adapter);
        mSystemMi.setNavigator(navigator);
        ViewPagerHelper.bind(mSystemMi, mSystemVp);
    }

    private void initVp() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new KnowledgeFragment());
        mFragmentList.add(new NavigationFragment());
        if (getFragmentManager() != null) {
            CommonVpAdapter adapter = new CommonVpAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            adapter.setFragmentList(mFragmentList);
            mSystemVp.setAdapter(adapter);
        }
    }
}
