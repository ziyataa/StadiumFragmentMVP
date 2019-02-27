package com.ziyata.stadiumfragmentmvp.ui.favorite;

import android.content.Context;

import com.ziyata.stadiumfragmentmvp.model.StadiumItem;

import java.util.List;

public interface FavoriteContract {

    interface View{
        void showDataList(List<StadiumItem> teamsItemList);
        void showFailureMessage(String msg);
        void hideRefresh();
    }

    interface Presenter{
        void  getDataListTeams(Context context);

    }
}
