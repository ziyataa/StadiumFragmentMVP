package com.ziyata.stadiumfragmentmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StadiumResponse {
    @SerializedName("teams")
    private List<StadiumItem> teams;

    public List<StadiumItem> getTeams(){
        return teams;
    }

    public void setTeams(List<StadiumItem> teams)
    {this.teams = teams;}
}
