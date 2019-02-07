package com.example.lunch.component;

import com.example.lunch.bean.receipt.Recipe;
import com.example.lunch.setup.RecipeData;
import com.example.lunch.type.IngredientStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FoodManagerTest {

    @InjectMocks
    private FoodManager foodManager;

    private Map<String, IngredientStatus> ingredientStatusMap;

    private Recipe recipe;

    private RecipeData recipeData = new RecipeData();

    @Before
    public void setup(){
        ingredientStatusMap = recipeData.ingredientStatusMapData();
        recipe = recipeData.createRecipeData();
    }


    @Test
    public void checkFoodQuality() {

        foodManager.checkFoodQuality(ingredientStatusMap,recipe);
        assertThat(recipe.getRecipes().size(),equalTo(2));
        assertThat(recipe.getRecipes().get(0).getTitle(),equalTo("f2"));
        assertThat(recipe.getRecipes().get(1).getTitle(),equalTo("f1"));
    }

    @Test
    public void checkFoodQualityWithNull() {

        recipe = recipeData.createRecipeNullData();
        foodManager.checkFoodQuality(ingredientStatusMap,recipe);

        //no required ingredient means no restrictions
        assertThat(recipe.getRecipes().size(),equalTo(1));
        assertThat(recipe.getRecipes().get(0).getTitle(),equalTo("f"));
    }
}