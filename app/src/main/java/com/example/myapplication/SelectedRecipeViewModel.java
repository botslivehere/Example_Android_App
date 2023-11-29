package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.db.Recipe;

public class SelectedRecipeViewModel extends ViewModel {

    private MutableLiveData<Boolean> recipesLoaded = new MutableLiveData<>();

    public LiveData<Boolean> areRecipesLoaded() {
        return recipesLoaded;
    }

    public void setRecipesLoaded(Boolean value) {
        recipesLoaded.postValue(value);
    }


    private MutableLiveData<Boolean> recipesLoaded2 = new MutableLiveData<>();

    public LiveData<Boolean> areRecipesLoaded2() {return recipesLoaded2;}

    public void setRecipesLoaded2(Boolean value) {recipesLoaded2.postValue(value);}
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
    private int user_id;
    public int getCurrentUserId() {
        return user_id;
    }
    public void setCurrentUserId(int user_id) {
        this.user_id = user_id;
    }
}
