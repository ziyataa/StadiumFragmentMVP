package com.ziyata.stadiumfragmentmvp.ui.favorite;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ziyata.stadiumfragmentmvp.R;
import com.ziyata.stadiumfragmentmvp.model.StadiumItem;
import com.ziyata.stadiumfragmentmvp.ui.Stadium.StadiumAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteContract.View {

    @BindView(R.id.rv_favorite)
    RecyclerView rvFavorite;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    private FavoritePresenter favoritePresenter = new FavoritePresenter(this);

    public FavoriteFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite2, container, false);
        unbinder = ButterKnife.bind(this, view);
        favoritePresenter.getDataListTeams(getContext());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                favoritePresenter.getDataListTeams(getContext());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        favoritePresenter.getDataListTeams(getContext());
    }

    @Override
    public void showDataList(List<StadiumItem> teamsItemList) {
        rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavorite.setAdapter(new StadiumAdapter(teamsItemList,getContext()));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideRefresh() {
            swipeRefresh.setRefreshing(false);
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
