package cc.cloudist.app.android.template.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cloudist.android.mvp.nucleus.presenter.RequiresPresenter;
import cc.cloudist.app.android.template.R;
import cc.cloudist.app.android.template.data.model.News;
import cc.cloudist.app.android.template.view.base.NucleusActivity;
import cc.cloudist.app.android.template.presenter.MainPresenter;
import cc.cloudist.app.android.template.view.adapter.NewsAdapter;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusActivity<MainPresenter> {


    @Bind(R.id.recycler_news)
    RecyclerView mRecyclerNews;

    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new NewsAdapter();
        mRecyclerNews.setAdapter(mAdapter);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState == null) getPresenter().request();
    }

    public void setData(List<News.StoriesEntity> data) {
        mAdapter.update(data);
    }

    public void onNetworkError(Throwable throwable) {
    }
}
