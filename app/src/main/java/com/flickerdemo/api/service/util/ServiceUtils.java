package com.flickerdemo.api.service.util;

import com.flickerdemo.util.service.IFailureCallback;
import com.flickerdemo.util.service.ISuccessCallback;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServiceUtils {
    private static final int RETRY_COUNT = 5;
    private static final long RETRY_DELAY_MILLIS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
    private static final long TIMEOUT_MILLIS = TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);

    protected ServiceUtils() {

    }

    public static <T> Disposable defaults(final Observable<T> pObservable, final ISuccessCallback<T> pSuccessCallback, final IFailureCallback pFailureCallback) {
        return ServiceUtils.defaults(pObservable, pSuccessCallback, pFailureCallback, TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    }

    public static <T> Disposable defaults(final Observable<T> pObservable, final ISuccessCallback<T> pSuccessCallback, final IFailureCallback pFailureCallback, final long pTimeout, final TimeUnit pTimeoutTimeUnit) {
        return pObservable.retryWhen(new RetryWithDelay(RETRY_COUNT, RETRY_DELAY_MILLIS))
                .timeout(pTimeout, pTimeoutTimeUnit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pSuccessCallback, pFailureCallback);
    }

//    public static <T> Subscription defaultsNoTimeout(final Observable<T> pObservable, final ISuccessCallback<T> pSuccessCallback, final IFailureCallback pFailureCallback) {
//        return pObservable.retryWhen(new RetryWithDelay(RETRY_COUNT, RETRY_DELAY_MILLIS))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(pSuccessCallback, pFailureCallback);
//    }
//
//    public static <T> Subscription defaultsNoRetry(final Observable<T> pObservable, final ISuccessCallback<T> pSuccessCallback, final IFailureCallback pFailureCallback) {
//        return pObservable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(pSuccessCallback, pFailureCallback);
//    }

//    public static <T> Subscription fireAndForget(final Observable<T> pObservable) {
//        return pObservable.retryWhen(new RetryWithDelay(RETRY_COUNT, RETRY_DELAY_MILLIS))
//                .timeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .subscribe(new ISuccessCallback<T>() {
//                    @Override
//                    public void call(final T pData) {
//                    /* Nothing. */
//                    }
//                }, new IFailureCallback() {
//                    @Override
//                    public void call(final Throwable pThrowable) {
//                        CrashUtils.log(pThrowable);
//                    }
//                });
//    }
//
//    public static long[] extractIds(@NonNull final List<? extends IHasId> pHasIds) {
//        final long[] ids = new long[pHasIds.size()];
//        for (int i = 0; i < ids.length; i++) {
//            final IHasId hasId = pHasIds.get(i);
//            ids[i] = hasId.getId();
//        }
//        return ids;
//    }
//
//    public static int[] extractStateIds(@NonNull final List<? extends IState> pStates) {
//        final int[] states = new int[pStates.size()];
//        for (int i = 0; i < states.length; i++) {
//            final IState state = pStates.get(i);
//            states[i] = state.getId();
//        }
//        return states;
//    }
//
//    public static String joinExpansions(final String... pExpansions) {
//        return StringUtils.join(pExpansions, "&");
//    }
}