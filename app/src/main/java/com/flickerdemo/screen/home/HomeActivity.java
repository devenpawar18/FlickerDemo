package com.flickerdemo.screen.home;

import android.os.Bundle;

import com.flickerdemo.R;
import com.flickerdemo.application.FlickerDemoApplication;
import com.flickerdemo.application.scope.ScreenScope;
import com.flickerdemo.screen.BaseActivity;
import com.flickerdemo.screen.util.PopupManager;
import com.flickerdemo.util.ViewUtils;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;

/**
 * Initialize Home Presenter and View
 */
public class HomeActivity extends BaseActivity {
    @Inject
    protected HomeView mHomeView;

    private HomeActivity.HomeComponent mHomeComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mHomeComponent = DaggerHomeActivity_HomeComponent.builder()
                .appComponent(FlickerDemoApplication.getApplication().getAppComponent())
                .homeModule(new HomeActivity.HomeModule()).build();

        this.getHomeComponent().inject(this);

        setContentView(R.layout.activity_home);

        HomeView homeView = (HomeView) getSupportFragmentManager().findFragmentById(R.id.home_container);

        if (homeView == null) {
            homeView = this.mHomeView;
            ViewUtils.addViewToActivity(getSupportFragmentManager(), mHomeView, R.id.home_container);
        }
    }

    public HomeActivity.HomeComponent getHomeComponent() {
        return this.mHomeComponent;
    }

    public PopupManager getPopupManager() {
        return this.mPopupManager;
    }

    @Module
    public static class HomeModule {

    }

    @ScreenScope
    @Component(
            dependencies = {
                    FlickerDemoApplication.AppComponent.class
            },
            modules = {
                    HomeActivity.HomeModule.class
            }
    )
    public static interface HomeComponent extends FlickerDemoApplication.AppComponent {
        public void inject(final HomeActivity pHomeActivity);

        public void inject(final HomeView pHomeView);
    }
}