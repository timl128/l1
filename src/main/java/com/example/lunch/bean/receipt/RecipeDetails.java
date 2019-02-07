package com.example.lunch.bean.receipt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeDetails {

    private String title;
    private List<String> ingredients = new ArrayList<>();
}
