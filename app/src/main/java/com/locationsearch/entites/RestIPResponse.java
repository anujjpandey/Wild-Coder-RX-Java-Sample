package com.locationsearch.entites;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wildcoder on 29/09/16.
 */

public class RestIPResponse {


    @SerializedName("result")
    private Loc location;

    public Loc getLocation() {
        return location;
    }

    public void setLocation(Loc location) {
        this.location = location;
    }

    public class Loc {
        @SerializedName("ip")
        String ip;

        @SerializedName("lat")
        private double lat = 0;

        @SerializedName("longi")
        private double longi = 0;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLongi() {
            return longi;
        }

        public void setLongi(double longi) {
            this.longi = longi;
        }
    }


}
