package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SelectedRecipeViewModel extends ViewModel {

    private MutableLiveData<Boolean> recipesLoaded = new MutableLiveData<>();

    public LiveData<Boolean> areRecipesLoaded() {
        return recipesLoaded;
    }

    public void setRecipesLoaded(Boolean value) {
        recipesLoaded.postValue(value);
    }
    private Recipe selectedRecipe;
    private int currentRecipeId;
    public Recipe getSelectedRecipe() {
        return selectedRecipe;
    }
    public void setSelectedRecipe(Recipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }
    public int getCurrentRecipeId() {
        return currentRecipeId;
    }
    public void setCurrentRecipeId(int currentRecipeId) {
        this.currentRecipeId = currentRecipeId;
    }
}
