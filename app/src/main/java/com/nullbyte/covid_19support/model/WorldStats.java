package com.nullbyte.covid_19support.model;

import com.google.gson.annotations.SerializedName;

public class WorldStats {
    @SerializedName("totalCases")
    private Integer totalCases;
    @SerializedName("totalDeaths")
    private Integer totalDeaths;
    @SerializedName("totalRecovered")
    private Integer totalRecovered;
    @SerializedName("newCases")
    private Integer newCases;
    @SerializedName("newDeaths")
    private Integer newDeaths;
    @SerializedName("takenAt")
    private String takenAt;

    public WorldStats(Integer totalCases, Integer totalDeaths, Integer totalRecovered, Integer newCases, Integer newDeaths, String takenAt) {
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
        this.takenAt = takenAt;
    }

    public Integer getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public Integer getNewCases() {
        return newCases;
    }

    public void setNewCases(Integer newCases) {
        this.newCases = newCases;
    }

    public Integer getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Integer newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(String takenAt) {
        this.takenAt = takenAt;
    }
}