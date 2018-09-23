package com.underarmour.nytimes.mvp.base;

public interface BasePresenter {

    void setView(BaseView baseView);

    void onViewResume();

    void onViewPause();

    void onViewDestroy();
}
