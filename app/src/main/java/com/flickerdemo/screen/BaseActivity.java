package com.flickerdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.flickerdemo.application.FlickerDemoApplication;
import com.flickerdemo.screen.util.PopupManager;

public class BaseActivity extends AppCompatActivity {
    protected PopupManager mPopupManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FlickerDemoApplication.getApplication().getAppComponent().inject(this);

        this.mPopupManager = new PopupManager(this);
    }
}