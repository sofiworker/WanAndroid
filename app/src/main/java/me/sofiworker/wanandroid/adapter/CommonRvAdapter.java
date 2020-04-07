package me.sofiworker.wanandroid.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.fragment.home.Article;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/19 13:50
 */
public class CommonRvAdapter extends BaseQuickAdapter<Article, BaseViewHolder> implements LoadMoreModule {

    public CommonRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Article article) {
        viewHolder.setText(R.id.tv_article_author, !TextUtils.isEmpty(article.getAuthor()) ? article.getAuthor() :
                !TextUtils.isEmpty(article.getShareUser()) ? article.getShareUser() : getContext().getString(R.string.author_unknown))
                .setText(R.id.tv_article_time, article.getNiceShareDate().equals(getContext().getString(R.string.unknown_time)) ? article.getNiceDate() : article.getNiceShareDate())
                .setText(R.id.tv_chapter_name, article.getSuperChapterName() + "·" + HtmlCompat.fromHtml(article.getChapterName(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString());
        if (article.isFresh()) {
            viewHolder.setVisible(R.id.tv_is_new, true);
        }else {
            viewHolder.getView(R.id.tv_is_new).setVisibility(View.GONE);
        }
        if (!article.getDesc().isEmpty()) {
            ((TextView)viewHolder.getView(R.id.tv_article_title)).setMaxLines(1);
            viewHolder.setText(R.id.tv_article_title, HtmlCompat.fromHtml(article.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString().replaceAll("\n", ""));
            viewHolder.getView(R.id.tv_article_desc).setVisibility(View.VISIBLE);
            String value = HtmlCompat.fromHtml(article.getDesc(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString();
            String result = value.replaceAll("\n", "");
            viewHolder.setText(R.id.tv_article_desc, result);
        }else {
            viewHolder.getView(R.id.tv_article_desc).setVisibility(View.GONE);
            ((TextView)viewHolder.getView(R.id.tv_article_title)).setMaxLines(3);
            viewHolder.setText(R.id.tv_article_title, HtmlCompat.fromHtml(article.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString().replaceAll("\n", ""));
        }
        if (article.getTags() != null && !article.getTags().isEmpty()) {
            viewHolder.getView(R.id.tv_tag).setVisibility(View.VISIBLE);
            String tag = HtmlCompat.fromHtml(article.getTags().get(0).getName(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString();
            viewHolder.setText(R.id.tv_tag, tag);
        }else {
            viewHolder.getView(R.id.tv_tag).setVisibility(View.GONE);
        }
        if (!article.getEnvelopePic().isEmpty()) {
            viewHolder.setVisible(R.id.iv_article_cover, true);
            Glide.with(getContext())
                    .load(article.getEnvelopePic())
                    .error(R.drawable.crash_logo)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into((ImageView) viewHolder.getView(R.id.iv_article_cover));
        }else {
            viewHolder.getView(R.id.iv_article_cover).setVisibility(View.GONE);
        }

        if (article.getType() == 1) {
            viewHolder.getView(R.id.tv_is_top).setVisibility(View.VISIBLE);
        }else {
            viewHolder.getView(R.id.tv_is_top).setVisibility(View.GONE);
        }
    }
}
