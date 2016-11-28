package com.locationsearch.entites;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wildcoder on 29/09/16.
 */
public class ApiStateSearchResponse {

    @SerializedName("RestResponse")
    private RestResponse restResponse;

    /**
     * @return
     */
    public RestResponse getRestResponse() {
        return restResponse;
    }

    /**
     * @param restResponse
     */
    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }
}
