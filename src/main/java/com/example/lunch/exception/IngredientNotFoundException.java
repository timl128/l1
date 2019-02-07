package com.example.lunch.exception;

public class IngredientNotFoundException extends RuntimeException{

    public IngredientNotFoundException(){
        super ("Ingredient not found. Please buy new ingredients.");
    }
}
