package me.sofiworker.wanandroid.activity.subsystem;

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
import me.sofiworker.wanandroid.base.BaseActivity;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.fragment.subfragment.SubFragment;
import me.sofiworker.wanandroid.fragment.system.knowledge.Knowledge;
import me.sofiworker.wanandroid.util.LogUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/26 16:18
 */
public class SubSystemActivity extends BaseActivity {

    @BindView(R.id.mi_subsystem)
    MagicIndicator magicIndicator;

    @BindView(R.id.vp_subsystem)
    ViewPager viewPager;

    private CommonVpAdapter mVpAdapter;
    private Knowledge mKnowledge;
    private Knowledge.KnowledgeChild mKnowledgeChild;
    private List<BaseFragment> mFragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subsystem;
    }

    @Override
    protected void initThings() {
        mKnowledge = (Knowledge) getIntent().getSerializableExtra("knowledge");
        mKnowledgeChild = (Knowledge.KnowledgeChild) getIntent().getSerializableExtra("knowledge.child");

        initVpAndMi();
    }

    private void initVpAndMi() {
        CommonNavigator navigator = new CommonNavigator(this);
        magicIndicator.setNavigator(navigator);
        CommonMiAdapter miAdapter = new CommonMiAdapter();
        miAdapter.setData(getTitleList());
        miAdapter.setPageListener(pos -> viewPager.setCurrentItem(pos));
        navigator.setAdapter(miAdapter);
        ViewPagerHelper.bind(magicIndicator, viewPager);

        mVpAdapter = new CommonVpAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(mVpAdapter);
        createSubFragment();
        if (mKnowledgeChild != null) {
            int index = 0;
            for (Knowledge.KnowledgeChild child : mKnowledge.getChildren()) {
                if (child.getName().equals(mKnowledgeChild.getName())) {
                    break;
                }
                index++;
            }
            LogUtil.d(mKnowledgeChild.getName()+index);
            magicIndicator.onPageSelected(index);
            viewPager.setCurrentItem(index);
        }
    }

    private List<String> getTitleList() {
        List<String> titleList = new ArrayList<>();
        for (Knowledge.KnowledgeChild child : mKnowledge.getChildren()) {
            titleList.add(child.getName());
        }
        return titleList;
    }

    private void createSubFragment() {
        for (Knowledge.KnowledgeChild child : mKnowledge.getChildren()) {
            SubFragment subFragment = new SubFragment(child.getId(), SubFragment.Type.subsystem);
            mFragmentList.add(subFragment);
        }
        mVpAdapter.setFragmentList(mFragmentList);
    }
}
