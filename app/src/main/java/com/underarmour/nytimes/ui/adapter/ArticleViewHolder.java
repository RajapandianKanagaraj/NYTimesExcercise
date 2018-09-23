package com.underarmour.nytimes.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.underarmour.nytimes.R;
import com.underarmour.nytimes.models.Byline;
import com.underarmour.nytimes.models.Article;
import com.underarmour.nytimes.models.Headline;
import com.underarmour.nytimes.models.Multimedia;
import com.underarmour.nytimes.network.APIConstants;
import com.underarmour.nytimes.utils.AppConstants;
import com.underarmour.nytimes.utils.DateUtil;

import java.util.ArrayList;

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView headlineTextView;
    private ImageView headlineImage;
    private TextView authorTextView;
    private TextView dateTextView;
    private Article article;
    private ArticleListAdapter.OnArticleSelectListener onArticleSelectListener;

    public ArticleViewHolder(CardView itemView, ArticleListAdapter.OnArticleSelectListener onArticleSelectListener) {
        super(itemView);
        this.onArticleSelectListener = onArticleSelectListener;

        headlineTextView = itemView.findViewById(R.id.text_view_headline);
        headlineImage = itemView.findViewById(R.id.image_view_headline);
        dateTextView = itemView.findViewById(R.id.textview_date);
        authorTextView = itemView.findViewById(R.id.textview_author);

        itemView.setOnClickListener(this);
    }

    public void bindView(Article article) {
        if(article != null) {
            this.article = article;
            String thumbnailImageUrl = null;
            ArrayList<Multimedia> multimediaList = article.getMultimedia();

            if (multimediaList != null) {
                for (Multimedia multimedia : multimediaList) {
                    if (multimedia.getSubType().equals(AppConstants.IMAGE_SIZE_THUMBNAIL)) {
                        thumbnailImageUrl = multimedia.getUrl();
                        break;
                    }
                }
            }

            if (thumbnailImageUrl != null) {
                Picasso.get().load(APIConstants.IMAGE_BASE_URL + "/" + thumbnailImageUrl)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.no_image_found)
                        .resize(AppConstants.THUMB_IMAGE_WIDTH, AppConstants.THUMB_IMAGE_HEIGHT)
                        .centerCrop()
                        .into(headlineImage);
            }

            Headline headline = article.getHeadline();
            if (headline != null) {
                headlineTextView.setText(headline.getMain());
            }
            String publishedDate = DateUtil.formatDate(article.getPubDate());
            dateTextView.setText(publishedDate);

            Byline byline = article.getByline();
            if (byline != null) {
                authorTextView.setText(byline.getOriginal());
            }
        }
    }

    @Override
    public void onClick(View view) {
        onArticleSelectListener.onArticleSelect(article);
    }
}
