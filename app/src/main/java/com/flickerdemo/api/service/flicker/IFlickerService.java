package com.flickerdemo.api.service.flicker;

import com.flickerdemo.api.model.PhotoInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IFlickerService {
    @GET("services/rest")
    public Observable<PhotoInfo> getPhotoInfo(@Query("method") String pMethod, @Query("api_key") String pAPIKey, @Query("format") String pFormat, @Query("nojsoncallback") String pNoJSONCallback, @Query("text") String pText, @Query("page") int pPage);
}