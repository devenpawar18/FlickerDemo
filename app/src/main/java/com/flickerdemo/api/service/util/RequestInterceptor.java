package com.flickerdemo.api.service.util;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(final Chain pChain) throws IOException {
        Request original = pChain.request();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder();
        // Add headers
//        requestBuilder.addHeader("Cache-Control", "no-cache")
//        requestBuilder.addHeader("Cache-Control", "no-store");

        Request request = requestBuilder.build();
        return pChain.proceed(request);
    }
}
