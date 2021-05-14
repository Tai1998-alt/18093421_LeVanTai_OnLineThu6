package com.example.sqlitedatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoUser {
    @Insert
    void InsertUser(User user);
    @Query("SELECT * FROM user")
    public List<User> getList();
    @Delete
    public void DeleteUser(User user);
}
