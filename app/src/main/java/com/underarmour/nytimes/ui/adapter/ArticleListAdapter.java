package com.underarmour.nytimes.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.underarmour.nytimes.R;
import com.underarmour.nytimes.models.Article;

import java.util.ArrayList;

public class ArticleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ARTICLE_VIEW = 1;
    private static final int LOADING_VIEW = 2;

    private ArrayList<Article> articleList;
    private boolean isLoadingAdded = false;
    private OnArticleSelectListener onArticleSelectListener;

    public ArticleListAdapter(ArrayList<Article> articleList, OnArticleSelectListener onArticleSelectListener) {
        this.articleList = articleList;
        this.onArticleSelectListener = onArticleSelectListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == ARTICLE_VIEW) {
            CardView articleView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_article_layout, parent, false);
            ArticleViewHolder resturantViewHolder = new ArticleViewHolder(articleView, onArticleSelectListener);
            return resturantViewHolder;
        } else {
            View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_loading, parent, false);
            LoadingViewHolder loadingViewHolder = new LoadingViewHolder(loadingView);
            return loadingViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position) == ARTICLE_VIEW) {
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
            Article article = articleList.get(position);
            if(article.getId() != null) {
                articleViewHolder.bindView(article);
            }
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == articleList.size() - 1 && isLoadingAdded) ? LOADING_VIEW : ARTICLE_VIEW;
    }

    public void add(Article mc) {
        articleList.add(mc);
        notifyItemInserted(articleList.size() - 1);
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Article());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = 0;
        Article item = getItem(position);

        if (item != null) {
            articleList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Article getItem(int position) {
        if(articleList != null && !articleList.isEmpty()) {
            return articleList.get(position);
        }
        return null;
    }

    public interface OnArticleSelectListener {
        void onArticleSelect(Article article);
    }
}
