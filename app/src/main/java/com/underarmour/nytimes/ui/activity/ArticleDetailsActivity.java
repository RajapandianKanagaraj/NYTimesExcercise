package com.underarmour.nytimes.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.underarmour.nytimes.R;
import com.underarmour.nytimes.ui.fragment.ArticleDetailsFragment;
import com.underarmour.nytimes.utils.AppConstants;

public class ArticleDetailsActivity extends AppCompatActivity {

    private ArticleDetailsFragment articleDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        Bundle bundle = getIntent().getExtras();

        FragmentManager fragmentManager = getSupportFragmentManager();
        articleDetailsFragment = (ArticleDetailsFragment) fragmentManager.findFragmentByTag(ArticleDetailsFragment.TAG);
        if(articleDetailsFragment == null) {
            articleDetailsFragment = new ArticleDetailsFragment();
            articleDetailsFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, articleDetailsFragment, ArticleDetailsFragment.TAG);
            fragmentTransaction.commit();
        }
    }
}
