package com.ziyata.stadiumfragmentmvp.ui.Stadium;

import com.ziyata.stadiumfragmentmvp.model.StadiumItem;

import java.util.List;

public interface StadiumContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataList(List<StadiumItem> stadiumItemList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataListItem();
    }
}
