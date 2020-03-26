package me.sofiworker.wanandroid.fragment.project;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.adapter.CommonMiAdapter;
import me.sofiworker.wanandroid.adapter.CommonVpAdapter;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.fragment.subfragment.SubFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/24 8:54
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.vp_project)
    ViewPager mProjectVp;
    @BindView(R.id.mi_project)
    MagicIndicator mProjectMi;

    private List<Project> mProjectList;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private CommonMiAdapter mMiAdapter;
    private CommonVpAdapter mVpAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initThings() {
        mPresenter = new ProjectPresenter();
        mPresenter.attachView(this);

        initVpAndMi();
        loadData();
    }

    private void initVpAndMi() {
        CommonNavigator navigator = new CommonNavigator(getContext());
        mProjectMi.setNavigator(navigator);
        mMiAdapter = new CommonMiAdapter();
        mMiAdapter.setPageListener(pos -> {
            mProjectVp.setCurrentItem(pos);
        });
        navigator.setAdapter(mMiAdapter);
        ViewPagerHelper.bind(mProjectMi, mProjectVp);

        if (getFragmentManager() != null) {
            mVpAdapter = new CommonVpAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }
        mProjectVp.setAdapter(mVpAdapter);
    }

    private void loadData() {
        mPresenter.loadProjectTitle();
    }

    @Override
    public void showProjectTitleList(List<Project> data) {
        mProjectList = data;
        List<String> titleList = new ArrayList<>();
        for (Project item : data) {
            titleList.add(item.getName());
        }
        mMiAdapter.setData(titleList);

        loadSubFragmentList();
    }

    private void loadSubFragmentList() {
        for (Project project : mProjectList) {
            SubFragment fragment = new SubFragment(project.getId(), SubFragment.Type.project);
            mFragmentList.add(fragment);
        }
        mVpAdapter.setFragmentList(mFragmentList);
    }
}
