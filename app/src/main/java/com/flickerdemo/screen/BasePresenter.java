package com.flickerdemo.screen;

/**
 * Base Presenter with common callbacks
 */

public interface BasePresenter<T> {

    void takeView(T pView);

    void dropView();

    void nextScreen();
}