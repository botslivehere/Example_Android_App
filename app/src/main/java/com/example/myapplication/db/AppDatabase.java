package com.example.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.sql.SQLData;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Recipe.class,FavoriteRecipe.class}, version = 11)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RecipeDao recipeDao();
    public abstract Favorite_recipeDao favorite_recipeDao();
    private static volatile AppDatabase instance;
    public RecipeDao getRecipeDao() {
        return instance.recipeDao();
    }
    public Favorite_recipeDao getFavoriteRecipeDao() {
        return instance.favorite_recipeDao();
    }
    private static final Migration MIGRATION = new Migration(10, 11) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Migration steps for the 'users' table
            database.execSQL("CREATE TABLE IF NOT EXISTS users_new (id INTEGER PRIMARY KEY NOT NULL, login TEXT NOT NULL UNIQUE, password TEXT NOT NULL)");
            database.execSQL("INSERT INTO users_new (id, login, password) SELECT id, login, password FROM users");
            database.execSQL("DROP TABLE IF EXISTS users");
            database.execSQL("ALTER TABLE users_new RENAME TO users");
            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_users_login ON users (login)");

            // Migration steps for the 'favorite_recipes' table
            database.execSQL("CREATE TABLE IF NOT EXISTS favorite_recipes_new " + "(user_id INTEGER NOT NULL, " + "id INTEGER PRIMARY KEY NOT NULL, " + "recipe_id INTEGER NOT NULL, " + "FOREIGN KEY(user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE, " + "FOREIGN KEY(recipe_id) REFERENCES recipes(id) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO favorite_recipes_new (user_id, id, recipe_id) " + "SELECT user_id, id, recipe_id FROM favorite_recipes");
            database.execSQL("DROP TABLE IF EXISTS favorite_recipes");
            database.execSQL("ALTER TABLE favorite_recipes_new RENAME TO favorite_recipes");

            database.execSQL("DELETE FROM recipes");
            database.execSQL("DELETE FROM users");
            database.execSQL("DELETE FROM favorite_recipes");

            Executors.newSingleThreadExecutor().execute(() -> {
                UserDao userDao = instance.userDao();
                for (int i = 0; i < 5; i++) {
                    User user = new User();
                    user.login = "" + (i + 1)+"@mail.ru";
                    user.password = "" + (i + 1);
                    userDao.insert(user);
                }
            });
        }
    };

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "Secret_db").addMigrations(MIGRATION).build();
        }
        return instance;
    }
}
