package com.example.myapplication.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class Favorite_recipeDao_Impl implements Favorite_recipeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FavoriteRecipe> __insertionAdapterOfFavoriteRecipe;

  public Favorite_recipeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavoriteRecipe = new EntityInsertionAdapter<FavoriteRecipe>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `favorite_recipes` (`id`,`recipe_id`,`user_id`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final FavoriteRecipe entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRecipeId());
        statement.bindLong(3, entity.getUserId());
      }
    };
  }

  @Override
  public void insert(final FavoriteRecipe favoriterecipe) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavoriteRecipe.insert(favoriterecipe);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<FavoriteRecipe> getFavoriteList(final int user_id) {
    final String _sql = "SELECT * FROM favorite_recipes WHERE user_id = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, user_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfRecipeId = CursorUtil.getColumnIndexOrThrow(_cursor, "recipe_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final List<FavoriteRecipe> _result = new ArrayList<FavoriteRecipe>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final FavoriteRecipe _item;
        _item = new FavoriteRecipe();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpRecipeId;
        _tmpRecipeId = _cursor.getLong(_cursorIndexOfRecipeId);
        _item.setRecipeId(_tmpRecipeId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<FavoriteRecipe> getFavoriteListByID(final int user_id, final int recipe_id) {
    final String _sql = "SELECT * FROM favorite_recipes WHERE user_id = ? and recipe_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, user_id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, recipe_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfRecipeId = CursorUtil.getColumnIndexOrThrow(_cursor, "recipe_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final List<FavoriteRecipe> _result = new ArrayList<FavoriteRecipe>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final FavoriteRecipe _item;
        _item = new FavoriteRecipe();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpRecipeId;
        _tmpRecipeId = _cursor.getLong(_cursorIndexOfRecipeId);
        _item.setRecipeId(_tmpRecipeId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
