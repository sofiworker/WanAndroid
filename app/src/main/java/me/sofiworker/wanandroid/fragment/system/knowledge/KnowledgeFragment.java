package me.sofiworker.wanandroid.fragment.system.knowledge;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.activity.subsystem.SubSystemActivity;
import me.sofiworker.wanandroid.adapter.KnowledgeRvAdapter;
import me.sofiworker.wanandroid.base.BaseFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 11:39
 */
public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

    @BindView(R.id.rv_system_sub)
    RecyclerView mSystemRv;

    private KnowledgeRvAdapter mAdapter;

    private List<Knowledge> mKnowledgeList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system_sub;
    }

    @Override
    protected void initThings() {
        mPresenter = new KnowledgePresenter();
        mPresenter.attachView(this);
        initRv();
        loadData();
    }

    private void loadData() {
        mPresenter.loadKnowledgeData();
    }

    private void initRv() {
        mSystemRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new KnowledgeRvAdapter(R.layout.rv_item_system_sub);
        mAdapter.setClickEvent((knowledge, child) -> {
            Intent intent = new Intent(getContext(), SubSystemActivity.class);
            intent.putExtra("knowledge", knowledge);
            intent.putExtra("knowledge.child", child);
            startActivity(intent);
        });
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getContext(), SubSystemActivity.class);
            intent.putExtra("knowledge", mKnowledgeList.get(position));
            startActivity(intent);
        });
        mSystemRv.setAdapter(mAdapter);
    }

    @Override
    public void showKnowledgeData(List<Knowledge> data) {
        mKnowledgeList = data;
        mAdapter.setNewData(data);
    }
}
