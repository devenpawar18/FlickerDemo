package com.flickerdemo.screen.home;

import android.os.Bundle;

import com.flickerdemo.R;
import com.flickerdemo.application.FlickerDemoApplication;
import com.flickerdemo.screen.BaseActivity;
import com.flickerdemo.screen.util.PopupManager;
import com.flickerdemo.util.ViewUtils;

import javax.inject.Inject;

/**
 * Initialize Home Presenter and View
 */
public class HomeActivity extends BaseActivity {
    @Inject
    protected HomeView mHomeView;

    private HomeComponent mHomeComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mHomeComponent = DaggerHomeComponent.builder()
                .appComponent(FlickerDemoApplication.getApplication().getAppComponent())
                .homeModule(new HomeModule()).build();

        this.getHomeComponent().inject(this);

        setContentView(R.layout.activity_home);

        HomeView homeView = (HomeView) getSupportFragmentManager().findFragmentById(R.id.home_container);

        if (homeView == null) {
            homeView = this.mHomeView;
            ViewUtils.addViewToActivity(getSupportFragmentManager(), mHomeView, R.id.home_container);
        }
    }

    public HomeComponent getHomeComponent() {
        return this.mHomeComponent;
    }

    public PopupManager getPopupManager() {
        return this.mPopupManager;
    }
}