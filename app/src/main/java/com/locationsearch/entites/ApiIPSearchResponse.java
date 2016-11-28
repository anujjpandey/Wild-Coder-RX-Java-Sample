package com.locationsearch.entites;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wildcoder on 29/09/16.
 */
public class ApiIPSearchResponse {


    @SerializedName("RestResponse")
    private RestIPResponse restResponse;

    /**
     * @return
     */
    public RestIPResponse getRestResponse() {
        return restResponse;
    }

    /**
     * @param restResponse
     */
    public void setRestResponse(RestIPResponse restResponse) {
        this.restResponse = restResponse;
    }
}
