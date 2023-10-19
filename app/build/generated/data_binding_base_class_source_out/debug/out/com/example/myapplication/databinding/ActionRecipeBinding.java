// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActionRecipeBinding implements ViewBinding {
  @NonNull
  private final SwipeRefreshLayout rootView;

  @NonNull
  public final RecyclerView recipesRecyclerView;

  @NonNull
  public final SwipeRefreshLayout refreshLayout;

  private ActionRecipeBinding(@NonNull SwipeRefreshLayout rootView,
      @NonNull RecyclerView recipesRecyclerView, @NonNull SwipeRefreshLayout refreshLayout) {
    this.rootView = rootView;
    this.recipesRecyclerView = recipesRecyclerView;
    this.refreshLayout = refreshLayout;
  }

  @Override
  @NonNull
  public SwipeRefreshLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActionRecipeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActionRecipeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.action_recipe, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActionRecipeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recipesRecyclerView;
      RecyclerView recipesRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recipesRecyclerView == null) {
        break missingId;
      }

      SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) rootView;

      return new ActionRecipeBinding((SwipeRefreshLayout) rootView, recipesRecyclerView,
          refreshLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}