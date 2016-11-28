package com.locationsearch.entites;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wildcoder on 29/09/16.
 */
public class RestResponse {
    @SerializedName("messages")
    private List<String> messages;


    @SerializedName("result")
    private List<State> results;

    public List<State> getResults() {
        return results;
    }

    public void setResults(List<State> results) {
        this.results = results;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
