package com.example.myapplication;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.navigation.fragment.NavHostFragment;

public class RecipeListFragment extends Fragment {

    private RecyclerView recipesRecyclerView;
    private RecipeAdapter adapter;
    private String[] recipes;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipesRecyclerView = view.findViewById(R.id.recipesRecyclerView);
        refreshLayout = view.findViewById(R.id.refreshLayout);

        recipes = getResources().getStringArray(R.array.recipe_names);

        TypedArray imagesArray = getResources().obtainTypedArray(R.array.recipe_images);
        int[] images = new int[imagesArray.length()];
        for (int i = 0; i < imagesArray.length(); i++) {
            images[i] = imagesArray.getResourceId(i, -1);
        }
        imagesArray.recycle();

        adapter = new RecipeAdapter(recipes,images);
        adapter.setOnItemClickListener(position -> navigateToSelectedRecipeFragment(position));
        recipesRecyclerView.setAdapter(adapter);

        recipesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recipesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    loadMoreItems(adapter);
                }
            }
        });
    }

    public void loadMoreItems(RecipeAdapter adapter) {
        refreshLayout.setRefreshing(true);
        new Handler().postDelayed(() -> {
            adapter.currentItemCount = Math.min(adapter.currentItemCount + 10, recipes.length);
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        }, 1000);
    }

    private void navigateToSelectedRecipeFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("recipeId", position);
        NavHostFragment.findNavController(this).navigate(R.id.action_recipeListFragment_to_selectedRecipeFragment, bundle);
    }
}
