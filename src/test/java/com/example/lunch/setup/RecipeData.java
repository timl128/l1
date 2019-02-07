package com.example.lunch.setup;

import com.example.lunch.bean.receipt.Recipe;
import com.example.lunch.bean.receipt.RecipeDetails;
import com.example.lunch.type.IngredientStatus;

import java.util.*;

public class RecipeData {


    public Map<String, IngredientStatus> ingredientStatusMapData(){

        Map<String, IngredientStatus> map = new HashMap<>();

        map.put("a",IngredientStatus.EXPIRE);
        map.put("b",IngredientStatus.NORMAL);
        map.put("c",IngredientStatus.BEST);

        return map;
    }

    public Recipe createRecipeData(){

        Recipe recipe = new Recipe();


        RecipeDetails recipeDetails = new RecipeDetails();
        recipeDetails.setIngredients(Arrays.asList("a","b"));
        recipeDetails.setTitle("f");

        RecipeDetails recipeDetails1 = new RecipeDetails();
        recipeDetails1.setIngredients(Arrays.asList("b","c"));
        recipeDetails1.setTitle("f1");

        RecipeDetails recipeDetails2 = new RecipeDetails();
        recipeDetails2.setIngredients(Arrays.asList("c"));
        recipeDetails2.setTitle("f2");



        List<RecipeDetails> recipeDetailsList = new LinkedList<>
                (Arrays.asList(recipeDetails,recipeDetails1,recipeDetails2));
        recipe.setRecipes(recipeDetailsList);


        return recipe;
    }

    public Recipe createRecipeNullData(){

        Recipe recipe = new Recipe();


        RecipeDetails recipeDetails = new RecipeDetails();
        recipeDetails.setTitle("f");

        RecipeDetails recipeDetails1 = new RecipeDetails();
        recipeDetails1.setIngredients(Arrays.asList("k","c"));
        recipeDetails1.setTitle("f1");


        List<RecipeDetails> recipeDetailsList = new LinkedList<>
                (Arrays.asList(recipeDetails,recipeDetails1));
        recipe.setRecipes(recipeDetailsList);


        return recipe;
    }
}
