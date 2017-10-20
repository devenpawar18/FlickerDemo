package com.flickerdemo.screen.splash;

import android.os.Bundle;

import com.flickerdemo.R;
import com.flickerdemo.api.service.flicker.FlickerService;
import com.flickerdemo.application.FlickerDemoApplication;
import com.flickerdemo.screen.BaseActivity;
import com.flickerdemo.util.ViewUtils;

import javax.inject.Inject;

/**
 * Initialize Splash Presenter and View
 */
public class SplashActivity extends BaseActivity {
    @Inject
    protected SplashView mSplashView;

    @Inject
    protected FlickerService mFlickerService;

    private SplashComponent mSplashComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mSplashComponent = DaggerSplashComponent.builder()
                .appComponent(FlickerDemoApplication.getApplication().getAppComponent())
                .splashModule(new SplashModule()).build();

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
}