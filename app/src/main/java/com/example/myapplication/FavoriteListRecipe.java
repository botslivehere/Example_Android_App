package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.FavoriteRecipe;
import com.example.myapplication.db.Favorite_recipeDao;
import com.example.myapplication.db.Recipe;
import com.example.myapplication.db.RecipeDao;


public class FavoriteListRecipe extends Fragment {
    private Recipe[] recipe;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recipesRecyclerView;
    private SelectedRecipeViewModel selectedRecipeViewModel;
    private RecipeAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedRecipeViewModel = new ViewModelProvider(requireActivity()).get(SelectedRecipeViewModel.class);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.action_favorites, container, false);

        TextView problemTextView = view.findViewById(R.id.Problem2);

        new Thread(() -> {
            AppDatabase appDatabase = AppDatabase.getInstance(requireContext());
            Favorite_recipeDao favorite_recipeDao = appDatabase.getFavoriteRecipeDao();
            RecipeDao recipeDao = appDatabase.getRecipeDao();

            FavoriteRecipe[] favorites = favorite_recipeDao.getFavoriteList(selectedRecipeViewModel.getCurrentUserId()).toArray(new FavoriteRecipe[0]);

            recipe = new Recipe[favorites.length];
            for (int i = 0; favorites.length > i; i++) {
                recipe[i] = recipeDao.GetRecipeByID((int) favorites[i].getRecipeId());
            }
            if(favorites.length>0) selectedRecipeViewModel.setRecipesLoaded2(true);
            else selectedRecipeViewModel.setRecipesLoaded2(false);
        }).start();

        selectedRecipeViewModel.areRecipesLoaded2().observe(getViewLifecycleOwner(), isLoaded -> {
            if (isLoaded) {
                recipesRecyclerView = view.findViewById(R.id.favoriteRecipesRecyclerView);
                refreshLayout = view.findViewById(R.id.refreshLayout2);
                adapter = new RecipeAdapter(recipe);
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
            } else {
                //nothing...
            }

        });

        return view;
    }

    private void navigateToSelectedRecipeFragment(int position) {
        selectedRecipeViewModel.setSelectedRecipe(recipe[position]);
        selectedRecipeViewModel.setCurrentRecipeId(position);
        NavHostFragment.findNavController(this).navigate(R.id.action_favorites_to_selectedRecipeFragment);
    }

    public void loadMoreItems(RecipeAdapter adapter) {
        refreshLayout.setRefreshing(true);
        new Handler().postDelayed(() -> {
            adapter.currentItemCount = Math.min(adapter.currentItemCount + 10, recipe.length);
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        }, 1000);
    }
}
