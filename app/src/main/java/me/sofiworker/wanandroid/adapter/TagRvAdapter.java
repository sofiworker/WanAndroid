package me.sofiworker.wanandroid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import me.sofiworker.wanandroid.R;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/29 21:52
 */
public class TagRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private ClickEvent mClickEvent;
    private LongClickEvent mLongClickEvent;

    public void setClickEvent(ClickEvent mClickEvent) {
        this.mClickEvent = mClickEvent;
    }

    public void setLongClickEvent(LongClickEvent mLongClickEvent) {
        this.mLongClickEvent = mLongClickEvent;
    }

    public TagRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, String str) {
        viewHolder.setText(R.id.tv_search_tag, str);
        TextView textView = viewHolder.getView(R.id.tv_search_tag);
        textView.setOnClickListener(v -> {
            if (mClickEvent != null) {
                mClickEvent.onClick(textView.getText().toString());
            }
        });

        textView.setOnLongClickListener(v -> {
            if (mLongClickEvent != null) {
                ImageView imageView = viewHolder.getView(R.id.iv_delete);
                imageView.setVisibility(View.VISIBLE);
                imageView.setOnClickListener(v1 -> {
                    if (mLongClickEvent != null) {
                        mLongClickEvent.onLongClick(textView.getText().toString());
                    }
                });
                notifyAll();
            }
            return true;
        });
    }

    public interface ClickEvent {
        void onClick(String str);
    }

    public interface LongClickEvent {
        void onLongClick(String str);
    }
}
