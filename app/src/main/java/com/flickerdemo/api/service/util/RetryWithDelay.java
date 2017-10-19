package com.flickerdemo.api.service.util;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/* package */ class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {
    private static final int[] HTTP_STATUS_CODES_DO_NOT_RETRY = {
            // TODO Probably more codes here. Potentially all < SC_INTERNAL_SERVER_ERROR?
            HttpURLConnection.HTTP_BAD_REQUEST,
            HttpURLConnection.HTTP_NOT_FOUND,
            HttpURLConnection.HTTP_CONFLICT,
            HttpURLConnection.HTTP_FORBIDDEN
    };

    private final int mRetryCountMaximum;
    private final long mRetryDelayMillis;

    private int mRetryCount;

    public RetryWithDelay(final int pRetryCountMaximum, final long pRetryDelayMillis) {
        this.mRetryCountMaximum = pRetryCountMaximum;
        this.mRetryDelayMillis = pRetryDelayMillis;
        this.mRetryCount = 0;
    }

//    protected boolean shouldRetry(final Throwable pThrowable) {
//        if (RetrofitUtils.isRetroFitError(pThrowable)) {
//            final RetrofitError retrofitError = (RetrofitError) pThrowable;
//            final Response response = retrofitError.getResponse();
//            if (response != null) {
//                final int status = response.getStatus();
//                if (ArrayUtils.contains(HTTP_STATUS_CODES_DO_NOT_RETRY, status)) {
//                    return false;
//                }
//            }
//        } else if (ErrorUtils.isAPIError(pThrowable)) {
//            final APIError apiError = (APIError) pThrowable;
//            final int status = apiError.getStatus();
//            if (ArrayUtils.contains(HTTP_STATUS_CODES_DO_NOT_RETRY, status)) {
//                return false;
//            }
//        } else if (pThrowable instanceof CardException) {
//            return false;
//        }
//        return this.mRetryCount < this.mRetryCountMaximum;
//    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> pAttempts) throws Exception {
        return pAttempts.flatMap(new Function<Throwable, Observable<?>>() {
            @Override
            public Observable<?> apply(final Throwable pThrowable) {
                RetryWithDelay.this.mRetryCount++;
                //  final boolean retry = RetryWithDelay.this.shouldRetry(pThrowable);
                final boolean retry = true;
                if (retry) {
                    return Observable.timer(RetryWithDelay.this.mRetryDelayMillis, TimeUnit.MILLISECONDS);
                } else {
                    return Observable.error(pThrowable);
                }
            }
        });
    }
}