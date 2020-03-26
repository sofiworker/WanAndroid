package me.sofiworker.wanandroid.adapter;

import android.content.Context;
import android.graphics.Color;

import com.tamsiree.rxkit.RxTool;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.List;

import me.sofiworker.wanandroid.R;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 10:43
 * MagicIndicator 通用适配器
 */
public class CommonMiAdapter extends CommonNavigatorAdapter {

    private List<String> mTitleList;
    private OnViewPageListener mPageListener;

    public void setData(List<String> data) {
        mTitleList = data;
        notifyDataSetChanged();
    }

    public void setPageListener(OnViewPageListener mPageListener) {
        this.mPageListener = mPageListener;
    }

    @Override
    public int getCount() {
        return mTitleList == null ? 0 : mTitleList.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
        colorTransitionPagerTitleView.setNormalColor(RxTool.getContext().getColor(R.color.text_invert_alpha));
        colorTransitionPagerTitleView.setSelectedColor(Color.WHITE);
        colorTransitionPagerTitleView.setText(mTitleList.get(index));
        colorTransitionPagerTitleView.setOnClickListener(view -> mPageListener.currentPos(index));
        return colorTransitionPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        return null;
    }

    public interface OnViewPageListener {
        /**
         * 获取当前位置
         * @param pos 指示器位置
         */
        void currentPos(int pos);
    }
}
