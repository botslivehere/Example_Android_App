package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


public class SelectedRecipeFragment extends Fragment {

    private Recipe selectedRecipe;
    private int currentRecipeId;

    private SelectedRecipeViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SelectedRecipeViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_recipe, container, false);

            TextView recipeTitleTextView = view.findViewById(R.id.recipeTitle);
            TextView recipeCalorie = view.findViewById(R.id.Calorie);
            TextView recipeTime = view.findViewById(R.id.Time);
            TextView recipeDifficulty = view.findViewById(R.id.Difficulty);
            TextView recipeDescriptionTextView = view.findViewById(R.id.recipeDescription);

        selectedRecipe = viewModel.getSelectedRecipe();
        currentRecipeId = viewModel.getCurrentRecipeId();


        if (selectedRecipe != null) {
                recipeTitleTextView.setText(selectedRecipe.getName());

                SpannableString content = new SpannableString("Калорийность:");
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                content.setSpan(new StyleSpan(Typeface.BOLD), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                recipeCalorie.setText(TextUtils.concat(content, " ", Integer.toString(selectedRecipe.getCalorie())));

                content = new SpannableString("Время приготовления:");
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                content.setSpan(new StyleSpan(Typeface.BOLD), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                recipeTime.setText(TextUtils.concat(content, " ", Integer.toString(selectedRecipe.getTime())));

                content = new SpannableString("Ингредиенты:");
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                content.setSpan(new StyleSpan(Typeface.BOLD), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                recipeDescriptionTextView.setText(TextUtils.concat(content, " ", selectedRecipe.getIngredients()));

                content = new SpannableString("Уровень сложности:");
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                content.setSpan(new StyleSpan(Typeface.BOLD), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                recipeDifficulty.setText(TextUtils.concat(content, " ", Integer.toString(selectedRecipe.getDifficulty())));
            } else {
                recipeTitleTextView.setText("Вы ничего не выбрали!");
            }

        return view;
    }
}
