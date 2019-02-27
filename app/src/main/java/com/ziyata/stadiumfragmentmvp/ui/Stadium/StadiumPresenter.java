package com.ziyata.stadiumfragmentmvp.ui.Stadium;

import com.ziyata.stadiumfragmentmvp.data.remote.ApiClient;
import com.ziyata.stadiumfragmentmvp.data.remote.ApiInterface;
import com.ziyata.stadiumfragmentmvp.model.StadiumItem;
import com.ziyata.stadiumfragmentmvp.model.StadiumResponse;
import com.ziyata.stadiumfragmentmvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StadiumPresenter implements StadiumContract.Presenter {

    private final StadiumContract.View view;
    private ApiInterface apiInterface = ApiClient.getCliend().create(ApiInterface.class);

    public StadiumPresenter(StadiumContract.View view) {
        this.view = view;
    }


    @Override
    public void getDataListItem() {
        view.showProgress();
        Call<StadiumResponse> call = apiInterface.getAllTeams(Constants.S, Constants.C);
        call.enqueue(new Callback<StadiumResponse>() {
            @Override
            public void onResponse(Call<StadiumResponse> call, Response<StadiumResponse> response) {
                view.hideProgress();
                if (response.body() != null){
                    view.showDataList(response.body().getTeams());
                }else{
                    view.showFailureMessage("Data Kosong");
                }
            }

            @Override
            public void onFailure(Call<StadiumResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });

    }

    @Override
    public void getSearchStadium(final String searchText) {
        if (!searchText.isEmpty()){
            view.showProgress();
            Call<StadiumResponse> call = apiInterface.getAllTeams(Constants.S, Constants.C);
            call.enqueue(new Callback<StadiumResponse>() {
                @Override
                public void onResponse(Call<StadiumResponse> call, Response<StadiumResponse> response) {
                    view.hideProgress();
                    if (response.body() != null){
                        if (response.body().getTeams() != null){
                            List<StadiumItem> stadiumItemList = response.body().getTeams();
                            List<StadiumItem> mStadiumItemList = new ArrayList<>();

                            for (StadiumItem data: stadiumItemList){
                                String namaStd = data.getStrStadium().toLowerCase();
                                if (namaStd.contains(searchText)){
                                    mStadiumItemList.add(data);
                                }
                            }
                            view.showDataList(mStadiumItemList);
                        }else {
                            view.showFailureMessage("Stadium Tidak Ada");
                        }
                    }
                }

                @Override
                public void onFailure(Call<StadiumResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());
                }
            });
        }else {
            getDataListItem();
        }
    }
}
