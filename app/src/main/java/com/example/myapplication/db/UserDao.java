package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE login = :login AND password = :password")
    User authenticate(String login, String password);

    @Insert
    void insert(User user);
}
