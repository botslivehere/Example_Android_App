package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RecipeListFragment extends Fragment {
    private SelectedRecipeViewModel selectedRecipeViewModel;
    private RecyclerView recipesRecyclerView;
    private RecipeAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private Recipe[] recipe;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_recipe, container, false);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedRecipeViewModel = new ViewModelProvider(requireActivity()).get(SelectedRecipeViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView problemTextView = getView().findViewById(R.id.Problem);

        loadRecipesFromApi();

        selectedRecipeViewModel.areRecipesLoaded().observe(getViewLifecycleOwner(), isLoaded -> {
            if (isLoaded) {
                problemTextView.setVisibility(View.GONE);
                recipesRecyclerView = view.findViewById(R.id.recipesRecyclerView);
                refreshLayout = view.findViewById(R.id.refreshLayout);

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
                getActivity().runOnUiThread(() -> {
                    problemTextView.setVisibility(View.VISIBLE);
                    problemTextView.setText("Ошибка при получении рецептов. Проверьте интернет-соединение и попробуйте снова.");
                });
               }
        });
    }


    public void loadMoreItems(RecipeAdapter adapter) {
        refreshLayout.setRefreshing(true);
        new Handler().postDelayed(() -> {
            adapter.currentItemCount = Math.min(adapter.currentItemCount + 10, recipe.length);
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        }, 1000);
    }

    private void navigateToSelectedRecipeFragment(int position) {
        selectedRecipeViewModel.setSelectedRecipe(recipe[position]);
        selectedRecipeViewModel.setCurrentRecipeId(position);
        NavHostFragment.findNavController(this).navigate(R.id.action_recipeListFragment_to_selectedRecipeFragment);
    }

    private void loadRecipesFromApi() {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/recipes2022.json");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(2000);
                connection.setReadTimeout(3000);
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    JSONArray recipesArray = new JSONArray(result.toString());
                    recipe = new Recipe[recipesArray.length()];
                    for (int i = 0; i < recipesArray.length(); i++) {
                        try {
                            JSONObject recipeObject = recipesArray.getJSONObject(i);

                            Recipe recipe_tmp = new Recipe();
                            recipe_tmp.setCalorie(recipeObject.getInt("Calorie"));
                            recipe_tmp.setTime(recipeObject.getInt("Time"));
                            recipe_tmp.setName(recipeObject.getString("Name"));
                            recipe_tmp.setIngredients(recipeObject.getString("Ingredients"));
                            recipe_tmp.setDifficulty(recipeObject.getInt("Difficulty"));

                            recipe[i]= recipe_tmp;

                            Log.d("RecipeInfo", "Name: " + recipe[i].getName());
                            Log.d("RecipeInfo", "Calorie: " + recipe[i].getCalorie());
                            Log.d("RecipeInfo", "Time: " + recipe[i].getTime());
                            Log.d("RecipeInfo", "Ingredients: " + recipe[i].getIngredients());
                            Log.d("RecipeInfo", "Difficulty: " + recipe[i].getDifficulty());
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    selectedRecipeViewModel.setRecipesLoaded(true);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                selectedRecipeViewModel.setRecipesLoaded(false);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();

    }
}