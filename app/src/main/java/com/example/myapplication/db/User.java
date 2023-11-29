package com.example.myapplication.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "users",indices = {@Index(value = "login", unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "login")
    @NonNull
    public String login;
    @ColumnInfo(name = "password")
    @NonNull
    public String password;
}
