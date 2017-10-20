package com.flickerdemo.screen.home;

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
                HomeModule.class
        }
)
public interface HomeComponent extends AppComponent {
    public void inject(final HomeActivity pHomeActivity);

    public void inject(final HomeView pHomeView);
}