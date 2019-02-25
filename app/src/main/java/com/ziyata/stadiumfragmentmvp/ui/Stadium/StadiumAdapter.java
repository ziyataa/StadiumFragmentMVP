package com.ziyata.stadiumfragmentmvp.ui.Stadium;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ziyata.stadiumfragmentmvp.R;
import com.ziyata.stadiumfragmentmvp.model.StadiumItem;
import com.ziyata.stadiumfragmentmvp.ui.detail.DetailActivity;
import com.ziyata.stadiumfragmentmvp.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StadiumAdapter extends RecyclerView.Adapter<StadiumAdapter.ViewHolder> {

    private final List<StadiumItem> stadiumItemList;
    private final Context context;


    public StadiumAdapter(List<StadiumItem> stadiumItemList, Context context) {
        this.stadiumItemList = stadiumItemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_stadium, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final StadiumItem stadiumItem = stadiumItemList.get(i);

        viewHolder.txtNameTeam.setText(stadiumItem.getStrStadium());
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(context).load(stadiumItem.getStrStadiumThumb()).apply(options).into(viewHolder.imgLogoTeam);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DetailActivity.class).putExtra(Constants.KEY_DATA,stadiumItem));
            }
        });

    }

    @Override
    public int getItemCount() {
        return stadiumItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo_team)
        ImageView imgLogoTeam;
        @BindView(R.id.txt_name_team)
        TextView txtNameTeam;
        @BindView(R.id.card_view)
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
