package com.ziyata.stadiumfragmentmvp.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ziyata.stadiumfragmentmvp.model.StadiumItem;

@Database(entities = StadiumItem.class, version = 1)
public abstract class StadiumDatabase extends RoomDatabase {

    public abstract StadiumDao stadiumDao();
    public static StadiumDatabase stadiumDatabase;
    public static StadiumDatabase getStadiumDatabase(Context context){
        synchronized (StadiumDatabase.class){
            if (stadiumDatabase == null){
                stadiumDatabase = Room.databaseBuilder(context, StadiumDatabase.class, "db_stadium")
                        .allowMainThreadQueries().build();
            }
        }return stadiumDatabase;
    }

}
