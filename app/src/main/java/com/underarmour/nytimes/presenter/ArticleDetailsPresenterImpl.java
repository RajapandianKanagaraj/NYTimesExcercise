package com.underarmour.nytimes.presenter;

import com.underarmour.nytimes.R;
import com.underarmour.nytimes.models.Article;
import com.underarmour.nytimes.models.Byline;
import com.underarmour.nytimes.models.Headline;
import com.underarmour.nytimes.models.Multimedia;
import com.underarmour.nytimes.mvp.ArticleDetailsMVPContract;
import com.underarmour.nytimes.mvp.base.BaseView;
import com.underarmour.nytimes.network.APIConstants;
import com.underarmour.nytimes.utils.AppConstants;
import com.underarmour.nytimes.utils.DateUtil;

import java.util.ArrayList;

public class ArticleDetailsPresenterImpl implements ArticleDetailsMVPContract.ArticleDetailsPresenter {

    private ArticleDetailsMVPContract.ArticleDetailsView articleDetailsView;

    public ArticleDetailsPresenterImpl() {

    }

    @Override
    public void showArticleDetails(Article article) {
        if (article != null) {
            Headline headline = article.getHeadline();
            if (headline != null) {
                articleDetailsView.showHeadline(headline.getMain());
            }
            String snippet = article.getSnippet();
            if (snippet != null) {
                articleDetailsView.showSnippet(snippet);
            }

            String publishedDate = DateUtil.formatDate(article.getPubDate());
            if (publishedDate != null) {
                articleDetailsView.showPublishedDate(publishedDate);
            }

            Byline byline = article.getByline();
            if (byline != null) {
                articleDetailsView.showAuthor(byline.getOriginal());
            }

            String thumbnailImageUrl = null;
            ArrayList<Multimedia> multimediaList = article.getMultimedia();

            if (multimediaList != null) {
                for (Multimedia multimedia : multimediaList) {
                    if (multimedia.getSubType().equals(AppConstants.IMAGE_SIZE_LARGE)) {
                        thumbnailImageUrl = multimedia.getUrl();
                        break;
                    }
                }
            }

            if (thumbnailImageUrl != null) {
                articleDetailsView.loadArticleImage(APIConstants.IMAGE_BASE_URL + "/" + thumbnailImageUrl);
            }
            articleDetailsView.showSource(articleDetailsView.getStringResource(R.string.source_by) + article.getSource());
        }
    }

    @Override
    public void setView(BaseView baseView) {
        articleDetailsView = (ArticleDetailsMVPContract.ArticleDetailsView) baseView;
    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewDestroy() {

    }
}
