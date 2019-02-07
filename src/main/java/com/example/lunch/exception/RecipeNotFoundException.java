package com.example.lunch.exception;

public class RecipeNotFoundException extends RuntimeException{

    public RecipeNotFoundException() {
        super ("Recipes not found. Please purchase new recipes.");
    }
}
