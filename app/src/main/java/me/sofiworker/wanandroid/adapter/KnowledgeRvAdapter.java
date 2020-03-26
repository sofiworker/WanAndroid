package me.sofiworker.wanandroid.adapter;

import android.view.LayoutInflater;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;
import java.util.UUID;

import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.fragment.system.knowledge.Knowledge;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 12:30
 */
public class KnowledgeRvAdapter extends BaseQuickAdapter<Knowledge, BaseViewHolder> {

    private ClickEvent mClickEvent;

    public KnowledgeRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setClickEvent(ClickEvent clickEvent) {
        this.mClickEvent = clickEvent;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Knowledge knowledge) {
        viewHolder.setText(R.id.tv_system_title, knowledge.getName());

        String tag = UUID.randomUUID().toString();
        FlexboxLayout flexboxLayout = viewHolder.getView(R.id.fbl_system_content);

        List<Knowledge.KnowledgeChild> children = knowledge.getChildren();
        if (flexboxLayout.getTag() == null) {
            flexboxLayout.setTag(tag);
            for (Knowledge.KnowledgeChild child : children) {
                TextView subTitleTv = (TextView) LayoutInflater.from(flexboxLayout.getContext()).inflate(R.layout.fbl_item_system, flexboxLayout, false);
                subTitleTv.setText(child.getName());
                subTitleTv.setOnClickListener((v -> {
                    if (mClickEvent != null) {
                        mClickEvent.onClick(knowledge, child);
                    }
                }));
                flexboxLayout.addView(subTitleTv);
            }
        }else {
            flexboxLayout.removeAllViews();
            for (Knowledge.KnowledgeChild child : children) {
                TextView subTitleTv = (TextView) LayoutInflater.from(flexboxLayout.getContext()).inflate(R.layout.fbl_item_system, flexboxLayout, false);
                subTitleTv.setText(child.getName());
                subTitleTv.setOnClickListener((v -> {
                    if (mClickEvent != null) {
                        mClickEvent.onClick(knowledge, child);
                    }
                }));
                flexboxLayout.addView(subTitleTv);
            }
        }
    }

    public interface ClickEvent {
        void onClick(Knowledge knowledge, Knowledge.KnowledgeChild children);
    }
}
