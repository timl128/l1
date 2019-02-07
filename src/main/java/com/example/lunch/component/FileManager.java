package com.example.lunch.component;

import com.example.lunch.api.KitchenClient;
import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.bean.receipt.Recipe;
import com.example.lunch.exception.InvalidFileContentException;
import com.example.lunch.exception.InvalidFileException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class FileManager implements KitchenClient {

    private static final String RECIPE_JSON_PATH = "recipes.json";
    private static final String INGREDIENTS_JSON_PATH = "ingredients.json";

    @Override
    public Recipe getRecipes() {


        String content = readFile(RECIPE_JSON_PATH);
        try {
            return new ObjectMapper().readValue(content, Recipe.class);
        } catch (IOException e) {
            throw new InvalidFileContentException(RECIPE_JSON_PATH);
        }

    }

    @Override
    public Ingredient getIngredient() {
        String content = readFile(INGREDIENTS_JSON_PATH);
        try {
            return new ObjectMapper().readValue(content, Ingredient.class);
        } catch (IOException e) {
            throw new InvalidFileContentException(INGREDIENTS_JSON_PATH);
        }
    }

    /**
     * read file
     * @param filename
     * @return
     */
    private String readFile(String filename){

        ClassPathResource cpr = new ClassPathResource(filename);
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
            return  new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InvalidFileException(filename);
        }
    }
}
