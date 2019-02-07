package com.example.lunch.setup;

import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.bean.ingredient.IngredientDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class IngredientData {

    public Ingredient createData() throws ParseException {

        Ingredient ingredient = new Ingredient();

        IngredientDetails ingredientDetails =
                new IngredientDetails("a", createDateFromString("2018-12-01"), createDateFromString("2018-12-05"));

        IngredientDetails ingredientDetails1 =
                new IngredientDetails("b", createDateFromString("2018-12-31"), createDateFromString("2019-01-01"));

        IngredientDetails ingredientDetails2 =
                new IngredientDetails("c", createDateFromString("2019-01-01"), createDateFromString("2019-02-10"));

        IngredientDetails ingredientDetails3 =
                new IngredientDetails("d", createDateFromString("2019-04-01"), createDateFromString("2019-05-10"));


        ingredient.setIngredients(Arrays.asList(ingredientDetails,ingredientDetails1,ingredientDetails2,ingredientDetails3));
        return ingredient;
    }

    public Ingredient createNullData() throws ParseException {

        Ingredient ingredient = new Ingredient();

        IngredientDetails ingredientDetails =
                new IngredientDetails("a", null,null);

        IngredientDetails ingredientDetails1 =
                new IngredientDetails("b", null, createDateFromString("2019-01-01"));

        IngredientDetails ingredientDetails2 =
                new IngredientDetails("c", createDateFromString("2018-12-01"), null);


        ingredient.setIngredients(Arrays.asList(ingredientDetails,ingredientDetails1,ingredientDetails2));
        return ingredient;
    }

    public Date createDateFromString(String string) throws ParseException {

        return new SimpleDateFormat("yyyy-MM-dd").parse(string);
    }
}
