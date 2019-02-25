package com.ziyata.stadiumfragmentmvp.ui.detail;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ziyata.stadiumfragmentmvp.R;
import com.ziyata.stadiumfragmentmvp.model.StadiumItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailStadiumContract.View {

    @BindView(R.id.img_logo_detail)
    ImageView imgLogoDetail;
    @BindView(R.id.txt_name_team_detail)
    TextView txtNameTeamDetail;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.card_view_detail)
    CardView cardViewDetail;
    @BindView(R.id.sv_detail)
    ScrollView svDetail;

    private Menu menu;
    private StadiumItem stadiumItem;

    private DetailStadiumPresenter detailStadiumPresenter = new DetailStadiumPresenter(this);
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Detail Stadium");
        Bundle bundle = getIntent().getExtras();
        detailStadiumPresenter.getDetailTeam(bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.favorite, menu);
        setFavorite();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_favorite:
                if (isFavorite){
                    detailStadiumPresenter.removeFavorite(this, stadiumItem);
                }else {
                    detailStadiumPresenter.addToFavorite(this, stadiumItem);
                }
                isFavorite = detailStadiumPresenter.checkFavorite(this, stadiumItem);
                setFavorite();
                break;
            case android.R.id.home:
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
        return true;
    }

    private void setFavorite() {
        if (isFavorite){
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_black));
        }else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_border));
        }
    }

    @Override
    public void showDetailTeam(StadiumItem stadiumItem) {
        this.stadiumItem = stadiumItem;
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(this).load(stadiumItem.getStrStadiumThumb()).apply(options).into(imgLogoDetail);
        txtNameTeamDetail.setText(stadiumItem.getStrStadium());
        txtDesc.setText(stadiumItem.getStrStadiumDescription());
        isFavorite = detailStadiumPresenter.checkFavorite(this, stadiumItem);
    }

    @Override
    public void showFailureMessage(String msg) {
        Snackbar.make(svDetail, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String msg) {
        Snackbar.make(svDetail, msg, Snackbar.LENGTH_SHORT).show();
    }
}
