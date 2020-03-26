package me.sofiworker.wanandroid.adapter;

import android.view.LayoutInflater;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;
import java.util.UUID;

import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.fragment.home.Article;
import me.sofiworker.wanandroid.fragment.system.navigation.Navigation;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/21 19:28
 */
public class NavigationRvAdapter extends BaseQuickAdapter<Navigation, BaseViewHolder> {

    private ClickEvent mClickEvent;

    public void setClickEvent(ClickEvent clickEvent) {
        this.mClickEvent = clickEvent;
    }

    public NavigationRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Navigation navigation) {
        viewHolder.setText(R.id.tv_system_title, navigation.getName());
        String tag = UUID.randomUUID().toString();
        FlexboxLayout flexboxLayout = viewHolder.getView(R.id.fbl_system_content);
        List<Article> articles = navigation.getArticles();
        if (flexboxLayout.getTag() == null) {
            flexboxLayout.setTag(tag);
            for (Article article : articles) {
                TextView subTitleTv = (TextView) LayoutInflater.from(flexboxLayout.getContext()).inflate(R.layout.fbl_item_system, flexboxLayout, false);
                subTitleTv.setOnClickListener((v -> {
                    if (mClickEvent != null) {
                        mClickEvent.onClick(article);
                    }
                }));
                subTitleTv.setText(article.getTitle());
                flexboxLayout.addView(subTitleTv);
            }
        }else {
            flexboxLayout.removeAllViews();
            for (Article article : articles) {
                TextView subTitleTv = (TextView) LayoutInflater.from(flexboxLayout.getContext()).inflate(R.layout.fbl_item_system, flexboxLayout, false);
                subTitleTv.setOnClickListener((v -> {
                    if (mClickEvent != null) {
                        mClickEvent.onClick(article);
                    }
                }));
                subTitleTv.setText(article.getTitle());
                flexboxLayout.addView(subTitleTv);
            }
        }
    }

    public interface ClickEvent {
        void onClick(Article article);
    }
}
