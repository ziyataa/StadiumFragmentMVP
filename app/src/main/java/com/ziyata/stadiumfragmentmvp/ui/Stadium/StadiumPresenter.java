package com.ziyata.stadiumfragmentmvp.ui.Stadium;

import com.ziyata.stadiumfragmentmvp.data.remote.ApiClient;
import com.ziyata.stadiumfragmentmvp.data.remote.ApiInterface;
import com.ziyata.stadiumfragmentmvp.model.StadiumResponse;
import com.ziyata.stadiumfragmentmvp.utils.Constants;

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
}
