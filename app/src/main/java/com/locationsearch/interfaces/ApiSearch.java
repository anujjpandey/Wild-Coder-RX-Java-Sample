package com.locationsearch.interfaces;

import com.locationsearch.entites.ApiIPSearchResponse;
import com.locationsearch.entites.ApiStateSearchResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wildcoder on 28/09/16.
 */
public interface ApiSearch {
    String getStateSearch = "state/search/IND?";

    @GET(getStateSearch)
    Observable<ApiStateSearchResponse> getGlobalSearch(@Query("text") String keywords);

    @GET("ip/{ip}/json")
    Observable<ApiIPSearchResponse> getIPDetail(@Path("ip") String id);

}
