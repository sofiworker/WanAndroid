package me.sofiworker.wanandroid.fragment.wechat;

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
 * @date 2020/3/21 19:39
 */
public class WeChatFragment extends BaseFragment<WechatPresenter> implements WechatContract.View {

    @BindView(R.id.vp_wechat)
    ViewPager mWechatVp;
    @BindView(R.id.mi_wechat)
    MagicIndicator mWechatMi;

    private List<Wechat> mWechatList;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private CommonMiAdapter mMiAdapter;
    private CommonVpAdapter mVpAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initThings() {
        mPresenter = new WechatPresenter();
        mPresenter.attachView(this);

        initVpAndMi();
        loadData();
    }

    private void initVpAndMi() {
        CommonNavigator navigator = new CommonNavigator(getContext());
        mWechatMi.setNavigator(navigator);
        mMiAdapter = new CommonMiAdapter();
        mMiAdapter.setPageListener(pos -> {
            mWechatVp.setCurrentItem(pos);
        });
        navigator.setAdapter(mMiAdapter);
        ViewPagerHelper.bind(mWechatMi, mWechatVp);

        if (getFragmentManager() != null) {
            mVpAdapter = new CommonVpAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }
        mWechatVp.setAdapter(mVpAdapter);
    }

    private void loadData() {
        mPresenter.loadWechatData();
    }


    @Override
    public void showDataWithMi(List<Wechat> wechatList) {
        mWechatList = wechatList;
        List<String> titleList = new ArrayList<>();
        for (Wechat wechat : wechatList) {
            titleList.add(wechat.getName());
        }
        mMiAdapter.setData(titleList);
        loadSubFragmentList();
    }

    private void loadSubFragmentList() {
        for (Wechat wechat : mWechatList) {
            SubFragment fragment = new SubFragment(wechat.getId(), SubFragment.Type.wechat);
            mFragmentList.add(fragment);
        }
        mVpAdapter.setFragmentList(mFragmentList);
    }
}
