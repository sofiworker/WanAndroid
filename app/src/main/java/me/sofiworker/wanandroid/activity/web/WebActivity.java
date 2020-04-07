package me.sofiworker.wanandroid.activity.web;

import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.base.BaseActivity;
import me.sofiworker.wanandroid.fragment.home.Article;
import me.sofiworker.wanandroid.fragment.home.BannerData;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/26 12:02
 */
public class WebActivity extends BaseActivity {

    @BindView(R.id.ll_web)
    LinearLayout mLinearLayout;

    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initThings() {
        BannerData bannerData = (BannerData) getIntent().getSerializableExtra("banner");
        Article article = (Article) getIntent().getSerializableExtra("article");
        String url = null;
        if (bannerData != null) {
            url = bannerData.getUrl();
        }
        if (article != null) {
            url = article.getLink();
        }
        createWeb(url);
    }

    private void createWeb(String url) {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .interceptUnkownUrl()
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }
}
