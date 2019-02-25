package com.ziyata.stadiumfragmentmvp.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ziyata.stadiumfragmentmvp.model.StadiumItem;

import java.util.List;

@Dao
public interface StadiumDao {
    @Insert
    void insertItem(StadiumItem stadiumItem);

    @Query("SELECT * FROM teams WHERE idTeam = :id")
    StadiumItem selectedItem(String id);

    @Delete
    void delete(StadiumItem stadiumItem);

    @Query("SELECT * FROM teams ORDER BY strStadium ASC")
    List<StadiumItem> selectFavorite();

}
