package com.example.myapplication;

import java.io.Serializable;

public class Recipe implements Serializable {
    private int Calorie;
    private int Time;
    private String Name;
    private String Ingredients;
    private int Difficulty;
    public int getCalorie() { return Calorie; }
    public int getTime() { return Time; }
    public String getName() { return Name; }
    public String getIngredients() { return Ingredients; }
    public int getDifficulty() { return Difficulty; }
    public void setCalorie(int calorie) { Calorie = calorie; }
    public void setTime(int time) { Time = time; }
    public void setName(String name) { Name = name; }
    public void setIngredients(String ingredients) { Ingredients = ingredients; }
    public void setDifficulty(int difficulty) { Difficulty = difficulty; }
}
