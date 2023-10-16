package com.example.myapplication;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;



public class SelectedRecipeFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_recipe, container, false);


        TextView recipeTitleTextView = view.findViewById(R.id.recipeTitle);
        TextView recipeDescriptionTextView = view.findViewById(R.id.recipeDescription);
        ImageView recipeImageView = view.findViewById(R.id.recipeImage);


        String[] recipeTitles = getResources().getStringArray(R.array.food_name);
        String[] recipeDescriptions = getResources().getStringArray(R.array.food_descriptions);
        TypedArray imagesArray = getResources().obtainTypedArray(R.array.recipe_images);
        int[] images = new int[imagesArray.length()];
        for (int i = 0; i < imagesArray.length(); i++) {
            images[i] = imagesArray.getResourceId(i, -1);
        }
        imagesArray.recycle();

        if (getArguments() != null) {
            int recipeId = getArguments().getInt("recipeId");

            recipeTitleTextView.setText(recipeTitles[recipeId%9]);
            recipeDescriptionTextView.setText(recipeDescriptions[recipeId%9]);
            recipeImageView.setImageResource(images[recipeId%9]);
        }


        return view;
    }
}
