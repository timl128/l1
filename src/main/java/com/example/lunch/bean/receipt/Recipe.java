package com.example.lunch.bean.receipt;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Recipe {

    List<RecipeDetails> recipes = new LinkedList<>();
}
