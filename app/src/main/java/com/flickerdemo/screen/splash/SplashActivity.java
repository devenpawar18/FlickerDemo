package com.flickerdemo.screen.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.flickerdemo.R;
import com.flickerdemo.api.service.flicker.FlickerService;
import com.flickerdemo.application.FlickerDemoApplication;
import com.flickerdemo.application.scope.ScreenScope;
import com.flickerdemo.util.ViewUtils;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;

/**
 * Initialize Splash Presenter and View
 */
public class SplashActivity extends AppCompatActivity {
    @Inject
    protected SplashView mSplashView;

    @Inject
    protected FlickerService mFlickerService;

    private SplashComponent mSplashComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mSplashComponent = DaggerSplashActivity_SplashComponent.builder()
                .appComponent(FlickerDemoApplication.getApplication().getAppComponent())
                .splashModule(new SplashActivity.SplashModule()).build();

        this.getSplashComponent().inject(this);

        setContentView(R.layout.activity_splash);

        SplashView splashView = (SplashView) getSupportFragmentManager().findFragmentById(R.id.splash_container);

        if (splashView == null) {
            splashView = this.mSplashView;
            ViewUtils.addViewToActivity(getSupportFragmentManager(), splashView, R.id.splash_container);
        }
    }

    public SplashComponent getSplashComponent() {
        return this.mSplashComponent;
    }

    @Module
    public static class SplashModule {

    }

    @ScreenScope
    @Component(
            dependencies = {
                    FlickerDemoApplication.AppComponent.class
            },
            modules = {
                    SplashModule.class
            }
    )
    public static interface SplashComponent extends FlickerDemoApplication.AppComponent {
        public void inject(final SplashActivity pSplashActivity);

        public void inject(final SplashView pSplashView);
    }
}