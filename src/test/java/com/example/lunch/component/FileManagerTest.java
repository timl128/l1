package com.example.lunch.component;

import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.bean.receipt.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FileManagerTest {

    @InjectMocks
    private FileManager fileManager;

    @Test
    public void getRecipes() {

        Recipe recipe = fileManager.getRecipes();
        assertThat(recipe.getRecipes().size(),equalTo(5));
    }

    @Test
    public void getIngredient() {
        Ingredient ingredient = fileManager.getIngredient();
        assertThat(ingredient.getIngredients().size(),equalTo(16));
    }
}