package com.underarmour.nytimes.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.underarmour.nytimes.R;
import com.underarmour.nytimes.ui.fragment.ArticleListFragment;
import com.underarmour.nytimes.utils.AppConstants;

public class HomeSearchActivity extends AppCompatActivity {

    private ArticleListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitleTextAppearance(this, R.style.RobotoBoldTextAppearance);
        setSupportActionBar(mToolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        listFragment = (ArticleListFragment) fragmentManager.findFragmentByTag(ArticleListFragment.TAG);
        if(listFragment == null) {
            listFragment = new ArticleListFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, listFragment, ArticleListFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem mSearch = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint(AppConstants.SEARCH_QUERY_HINT);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listFragment.search(query);
                searchView.setQuery("", false);
                searchView.setIconified(true);
                mSearch.collapseActionView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
