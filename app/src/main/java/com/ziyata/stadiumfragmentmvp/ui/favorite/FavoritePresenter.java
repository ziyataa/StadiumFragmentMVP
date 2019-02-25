package com.ziyata.stadiumfragmentmvp.ui.favorite;

import android.content.Context;

import com.ziyata.stadiumfragmentmvp.data.local.StadiumDatabase;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private final FavoriteContract.View view;
    private StadiumDatabase stadiumDatabase;

    public FavoritePresenter(FavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeams(Context context) {
        stadiumDatabase = StadiumDatabase.getStadiumDatabase(context);
        if (stadiumDatabase.stadiumDao().selectFavorite() != null){
            view.showDataList(stadiumDatabase.stadiumDao().selectFavorite());
        }else{
            view.showFailureMessage("Tidak ada data di favorite");
        }

        view.hideRefresh();
    }
}
