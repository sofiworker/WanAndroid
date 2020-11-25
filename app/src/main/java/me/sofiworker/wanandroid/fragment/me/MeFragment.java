package me.sofiworker.wanandroid.fragment.me;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.activity.login.LoginActivity;
import me.sofiworker.wanandroid.base.BaseFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/24 20:56
 */
public class MeFragment extends BaseFragment<MePresenter> implements MeContract.View {

    @BindView(R.id.civ_me_head)
    CircleImageView mCircleImageView;
    @BindView(R.id.fl_setting)
    FrameLayout mSettingFl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initThings() {

    }

    @OnClick({R.id.civ_me_head, R.id.fl_setting})
    void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.civ_me_head:
                intent.setClass(Objects.requireNonNull(getActivity()), LoginActivity.class);
                break;
            case R.id.fl_setting:
//                intent.setClass(getContext(), );
            default:
                break;
        }
        startActivity(intent);
    }
}
