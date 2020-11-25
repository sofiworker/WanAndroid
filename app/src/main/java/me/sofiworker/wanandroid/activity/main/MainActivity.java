package me.sofiworker.wanandroid.activity.main;

import android.view.MenuItem;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.adapter.CommonVpAdapter;
import me.sofiworker.wanandroid.base.BaseActivity;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.fragment.home.HomeFragment;
import me.sofiworker.wanandroid.fragment.me.MeFragment;
import me.sofiworker.wanandroid.fragment.project.ProjectFragment;
import me.sofiworker.wanandroid.fragment.system.SystemFragment;
import me.sofiworker.wanandroid.fragment.wechat.WeChatFragment;
import me.sofiworker.wanandroid.view.MainViewPager;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 20:11
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.btm_nav_main)
    BottomNavigationView mBottomNav;
    @BindView(R.id.vp_main)
    MainViewPager mMainViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initThings() {
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);


        initBottomNav();
        initViewPager();
    }

    private void initBottomNav() {
        mBottomNav.setSelectedItemId(R.id.item_home);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_home:
                    mMainViewPager.setCurrentItem(0);
                    break;
                case R.id.item_system:
                    mMainViewPager.setCurrentItem(1);
                    break;
                case R.id.item_wx_public_number:
                    mMainViewPager.setCurrentItem(2);
                    break;
                case R.id.item_project:
                    mMainViewPager.setCurrentItem(3);
                    break;
                case R.id.item_me:
                    mMainViewPager.setCurrentItem(4);
                    break;
                default:break;
            }
            return true;
        });
    }

    private void initViewPager() {
        CommonVpAdapter adapter = new CommonVpAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.setFragmentList(createFragments());
        mMainViewPager.setAdapter(adapter);
        mMainViewPager.setOffscreenPageLimit(4);
        mMainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBottomNav.setSelectedItemId(R.id.item_home);
                        break;
                    case 1:
                        mBottomNav.setSelectedItemId(R.id.item_system);
                        break;
                    case 2:
                        mBottomNav.setSelectedItemId(R.id.item_wx_public_number);
                        break;
                    case 3:
                        mBottomNav.setSelectedItemId(R.id.item_project);
                        break;
                    case 4:
                        mBottomNav.setSelectedItemId(R.id.item_me);
                        break;
                    default:break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<BaseFragment> createFragments(){
        List<BaseFragment> fragmentList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
        SystemFragment systemFragment = new SystemFragment();
        fragmentList.add(systemFragment);
        WeChatFragment weChatFragment = new WeChatFragment();
        fragmentList.add(weChatFragment);
        ProjectFragment projectFragment = new ProjectFragment();
        fragmentList.add(projectFragment);
        MeFragment meFragment = new MeFragment();
        fragmentList.add(meFragment);
        return fragmentList;
    }
}
