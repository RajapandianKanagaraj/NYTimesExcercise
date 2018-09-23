package com.underarmour.nytimes.ui.base;


import android.support.v4.app.Fragment;

import com.underarmour.nytimes.mvp.base.BasePresenter;
import com.underarmour.nytimes.mvp.base.BaseView;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    public BaseFragment() {
    }

    @Override
    public String getStringResource(int stringId) {
        return getString(stringId);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

}
