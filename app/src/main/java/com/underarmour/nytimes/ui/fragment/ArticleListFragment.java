package com.underarmour.nytimes.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.underarmour.nytimes.R;
import com.underarmour.nytimes.presenter.SearchPresenterImpl;
import com.underarmour.nytimes.ui.activity.ArticleDetailsActivity;
import com.underarmour.nytimes.utils.AppConstants;
import com.underarmour.nytimes.utils.PaginationScrollListener;
import com.underarmour.nytimes.models.Article;
import com.underarmour.nytimes.mvp.SearchMVPContract;
import com.underarmour.nytimes.ui.base.BaseFragment;
import com.underarmour.nytimes.ui.adapter.ArticleListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListFragment extends BaseFragment implements SearchMVPContract.SearchArticleView, ArticleListAdapter.OnArticleSelectListener {

    public static final String TAG = ArticleListFragment.class.getName();

    private RecyclerView articleListView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Article> articleList = new ArrayList<>();
    private ArticleListAdapter listAdapter;
    private TextView searchResultsTextView;
    private TextView noArticlesErrorTextView;
    private TextView noNetworkErrorTextView;
    private SearchMVPContract.SearchPresenter searchPresenter;
    private String searchQueryText;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 0;
    private View rootView;
    private boolean isNewSearch;

    public ArticleListFragment() {
        setRetainInstance(true);
        searchPresenter = new SearchPresenterImpl();
        searchPresenter.setView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_article_list, container, false);

            searchResultsTextView = rootView.findViewById(R.id.text_search_query);
            noArticlesErrorTextView = rootView.findViewById(R.id.textview_no_articles);
            noNetworkErrorTextView = rootView.findViewById(R.id.no_network_error_view);

            articleListView = rootView.findViewById(R.id.article_list_view);
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            articleListView.setLayoutManager(linearLayoutManager);
            listAdapter = new ArticleListAdapter(articleList, this);
            articleListView.setAdapter(listAdapter);

            articleListView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
                @Override
                protected void loadMoreItems() {
                    noNetworkErrorTextView.setVisibility(View.GONE);
                    isLoading = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(currentPage > 0) {
                                listAdapter.addLoadingFooter();
                                searchPresenter.loadMoreArticles(searchQueryText);
                            }
                        }
                    }, AppConstants.DELAY_TIME);
                }

                @Override
                public int getTotalPageCount() {
                    return currentPage;
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });

        }
        return rootView;
    }

    public void search(String searchText) {
        isNewSearch = true;
        articleList.clear();
        listAdapter.notifyDataSetChanged();
        searchResultsTextView.setVisibility(View.GONE);
        noArticlesErrorTextView.setVisibility(View.GONE);
        articleListView.setVisibility(View.VISIBLE);
        listAdapter.addLoadingFooter();
        searchQueryText = searchText;
        searchPresenter.startSearch(searchText);
    }

    private void showSearchQuery(String searchText) {
        String formattedText = getResources().getString(R.string.label_search_results, searchText);

        TypefaceSpan robotoRegularSpan = new TypefaceSpan("sans-serif-thin");
        TypefaceSpan robotoRegularItalicSpan = new TypefaceSpan("sans-serif-medium");

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(formattedText);
        spannableStringBuilder.setSpan(robotoRegularSpan, 0, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(robotoRegularItalicSpan, 20, formattedText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        searchResultsTextView.setVisibility(View.VISIBLE);
        searchResultsTextView.setText(spannableStringBuilder);
        isNewSearch = false;
    }

    @Override
    public void showArticleList(final ArrayList<Article> newArticleList) {
        noArticlesErrorTextView.setVisibility(View.GONE);
        noNetworkErrorTextView.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isLoading = false;
                listAdapter.removeLoadingFooter();
                if(newArticleList == null || newArticleList.isEmpty()) {
                    isLastPage = true;
                    if(isNewSearch) {
                        showNoArticlesErrorMessage();
                    }
                } else {
                    currentPage += 1;
                    searchPresenter.setCurrentPage(currentPage);
                    showSearchQuery(searchQueryText);
                    articleList.addAll(newArticleList);
                    listAdapter.notifyDataSetChanged();
                }
            }
        },AppConstants.DELAY_TIME);
    }

    @Override
    public void showNoArticlesErrorMessage() {
        searchResultsTextView.setVisibility(View.GONE);
        noNetworkErrorTextView.setVisibility(View.GONE);
        noArticlesErrorTextView.setVisibility(View.VISIBLE);
        articleListView.setVisibility(View.GONE);
        noArticlesErrorTextView.setText(getResources().getString(R.string.no_articles_error_message, searchQueryText));
    }

    @Override
    public void showNoNetworkErrorMessage() {
        if(isNewSearch) {
            searchResultsTextView.setVisibility(View.GONE);
            noArticlesErrorTextView.setVisibility(View.VISIBLE);
            articleListView.setVisibility(View.GONE);
            noArticlesErrorTextView.setText(getStringResource(R.string.no_network_error_message));
        } else {
            isLoading = false;
            listAdapter.removeLoadingFooter();
            noNetworkErrorTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onArticleSelect(Article article) {
        Intent intent = new Intent(getActivity(), ArticleDetailsActivity.class);
        intent.putExtra(AppConstants.BUNDLE_KEY_ARTICLE, article);
        getActivity().startActivity(intent);
    }
}
