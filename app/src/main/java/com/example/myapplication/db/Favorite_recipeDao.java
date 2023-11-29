package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Favorite_recipeDao {
    @Query("SELECT * FROM favorite_recipes WHERE user_id = :user_id ")
    List<FavoriteRecipe> getFavoriteList(int user_id);

    @Query("SELECT * FROM favorite_recipes WHERE user_id = :user_id and recipe_id = :recipe_id")
    List<FavoriteRecipe> getFavoriteListByID(int user_id,int recipe_id);
    @Insert
    void insert(FavoriteRecipe favoriterecipe);
}