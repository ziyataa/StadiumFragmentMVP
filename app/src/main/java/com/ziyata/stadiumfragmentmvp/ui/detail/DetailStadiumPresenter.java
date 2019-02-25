package com.ziyata.stadiumfragmentmvp.ui.detail;

import android.content.Context;
import android.os.Bundle;

import com.ziyata.stadiumfragmentmvp.data.local.StadiumDatabase;
import com.ziyata.stadiumfragmentmvp.model.StadiumItem;
import com.ziyata.stadiumfragmentmvp.utils.Constants;

public class DetailStadiumPresenter implements DetailStadiumContract.Presenter {

    private final DetailStadiumContract.View view;
    private StadiumDatabase stadiumDatabase;

    public DetailStadiumPresenter(DetailStadiumContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailTeam(Bundle bundle) {
        if (bundle != null){
            StadiumItem stadiumItem = (StadiumItem) bundle.getSerializable(Constants.KEY_DATA);
            view.showDetailTeam(stadiumItem);
        }else{
            view.showFailureMessage("Data Kosong!!!");
        }
    }

    @Override
    public void addToFavorite(Context context, StadiumItem stadiumItem) {
        stadiumDatabase = StadiumDatabase.getStadiumDatabase(context);
        stadiumDatabase.stadiumDao().insertItem(stadiumItem);
        view.showSuccessMessage("Tersimpan");
    }

    @Override
    public void removeFavorite(Context context, StadiumItem stadiumItem) {
        stadiumDatabase = StadiumDatabase.getStadiumDatabase(context);
        stadiumDatabase.stadiumDao().delete(stadiumItem);
        view.showSuccessMessage("Terhapus");
    }

    @Override
    public Boolean checkFavorite(Context context, StadiumItem stadiumItem) {
        Boolean sFavorite = false;
        stadiumDatabase = StadiumDatabase.getStadiumDatabase(context);
        return sFavorite = stadiumDatabase.stadiumDao().selectedItem(stadiumItem.getIdTeam()) != null;

    }
}
