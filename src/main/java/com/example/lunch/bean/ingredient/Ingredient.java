package com.example.lunch.bean.ingredient;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Ingredient {

    List<IngredientDetails> ingredients = new LinkedList<>();
}
