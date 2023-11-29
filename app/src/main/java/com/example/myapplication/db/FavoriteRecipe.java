package com.example.myapplication.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "favorite_recipes",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Recipe.class,
                        parentColumns = "id",
                        childColumns = "recipe_id",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class FavoriteRecipe {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "recipe_id")
    private long recipeId;

    @ColumnInfo(name = "user_id")
    private long userId;

    public long getId() {
        return id;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public long getUserId() {
        return userId;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

}
