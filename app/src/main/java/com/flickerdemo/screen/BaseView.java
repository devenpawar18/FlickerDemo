package com.flickerdemo.screen;

import android.content.Context;

/**
 * Base View to link presenter
 *
 * @param <T>
 */

public interface BaseView<T> {
    Context getContext();
}