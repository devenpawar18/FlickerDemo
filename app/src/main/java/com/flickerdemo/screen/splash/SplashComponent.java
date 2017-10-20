package com.flickerdemo.screen.splash;

import com.flickerdemo.application.scope.ScreenScope;
import com.flickerdemo.screen.app.AppComponent;

import dagger.Component;

/**
 * Created by dpawar on 10/19/17.
 */

@ScreenScope
@Component(
        dependencies = {
                AppComponent.class
        },
        modules = {
                SplashModule.class
        }
)
public interface SplashComponent extends AppComponent {
    public void inject(final SplashActivity pSplashActivity);

    public void inject(final SplashView pSplashView);
}