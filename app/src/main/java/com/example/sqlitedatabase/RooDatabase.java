package com.example.sqlitedatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {User.class}, version = 1)
public abstract class RooDatabase extends RoomDatabase {
    private static final String DATABASE_NAME="user.db";
    private static RooDatabase instance;
    public static synchronized RooDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),RooDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();

        }
        return instance;
    }

    public abstract DaoUser daoUser();
}
