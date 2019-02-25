package com.ziyata.stadiumfragmentmvp.ui.detail;

import android.content.Context;
import android.os.Bundle;

import com.ziyata.stadiumfragmentmvp.model.StadiumItem;

public interface DetailStadiumContract {

    interface View{
        void showDetailTeam(StadiumItem stadiumItem);
        void showFailureMessage(String msg);
        void showSuccessMessage(String msg);
    }

    interface Presenter{
        void getDetailTeam(Bundle bundle);
        void addToFavorite(Context context, StadiumItem stadiumItem);
        void removeFavorite(Context context, StadiumItem stadiumItem);
        Boolean checkFavorite(Context context, StadiumItem stadiumItem);
    }
}
