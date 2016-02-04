package cc.cloudist.app.android.template.view.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.cloudist.app.android.template.R;
import cc.cloudist.app.android.template.data.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News.StoriesEntity> mNewsList;

    public NewsAdapter() {
        mNewsList = new ArrayList<>();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(newsView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News.StoriesEntity entity = mNewsList.get(position);

        SimpleDraweeView newsImage = holder.newsImage;
        TextView newsTitle = holder.newsTitle;

        newsImage.setImageURI(Uri.parse(entity.getImages().get(0)));
        newsTitle.setText(entity.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void update(List<News.StoriesEntity> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.news_image)
        SimpleDraweeView newsImage;
        @Bind(R.id.text_news_title)
        TextView newsTitle;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
