package com.underarmour.nytimes.mvp.base;

import android.view.View;

public interface BaseView {

    String getStringResource(int stringId);

    void setPresenter(BasePresenter presenter);

    void setRootView(View rootView);

}
