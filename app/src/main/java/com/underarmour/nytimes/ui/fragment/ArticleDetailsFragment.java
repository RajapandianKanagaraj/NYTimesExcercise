package com.underarmour.nytimes.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.underarmour.nytimes.R;
import com.underarmour.nytimes.models.Article;
import com.underarmour.nytimes.mvp.ArticleDetailsMVPContract;
import com.underarmour.nytimes.presenter.ArticleDetailsPresenterImpl;
import com.underarmour.nytimes.ui.base.BaseFragment;
import com.underarmour.nytimes.utils.AppConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleDetailsFragment extends BaseFragment implements ArticleDetailsMVPContract.ArticleDetailsView, View.OnClickListener{

    public static final String TAG = ArticleDetailsFragment.class.getName();

    private View rootView;
    private TextView headLineTextView;
    private TextView snippetTextView;
    private TextView dateTextView;
    private TextView authorTextView;
    private TextView sourceTextView;
    private ImageView articleImage;
    private Button readFullArticle;
    private Article article;
    private ArticleDetailsPresenterImpl articleDetailsPresenter;

    public ArticleDetailsFragment() {
        setRetainInstance(true);
        articleDetailsPresenter = new ArticleDetailsPresenterImpl();
        articleDetailsPresenter.setView(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            article = bundle.getParcelable(AppConstants.BUNDLE_KEY_ARTICLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_article_details, container, false);
            headLineTextView = rootView.findViewById(R.id.textview_headline);
            snippetTextView = rootView.findViewById(R.id.textview_snippet);
            dateTextView = rootView.findViewById(R.id.textview_date);
            authorTextView = rootView.findViewById(R.id.textview_author);
            sourceTextView = rootView.findViewById(R.id.textview_source);
            articleImage = rootView.findViewById(R.id.imageview_article);
            readFullArticle = rootView.findViewById(R.id.button_read_full_article);
            readFullArticle.setOnClickListener(this);
        }
        articleDetailsPresenter.showArticleDetails(article);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        String url = article.getWebUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void showHeadline(String headline) {
        headLineTextView.setText(headline);
    }

    @Override
    public void showSnippet(String snippet) {
        snippetTextView.setText(snippet);
    }

    @Override
    public void showSource(String source) {
        sourceTextView.setText(source);
    }

    @Override
    public void showPublishedDate(String date) {
        dateTextView.setText(date);
    }

    @Override
    public void showAuthor(String author) {
        authorTextView.setText(author);
    }

    @Override
    public void loadArticleImage(String url) {
        Picasso.get().load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_found)
                .into(articleImage);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }
}
