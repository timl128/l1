package com.example.lunch.service;

import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.bean.receipt.Recipe;
import com.example.lunch.component.DataManager;
import com.example.lunch.component.FileManager;
import com.example.lunch.component.FoodManager;
import com.example.lunch.component.IngredientManager;
import com.example.lunch.exception.IngredientNotFoundException;
import com.example.lunch.exception.RecipeNotFoundException;
import com.example.lunch.setup.IngredientData;
import com.example.lunch.setup.RecipeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;

@RunWith(SpringRunner.class)
public class MenuServiceTest {

    @InjectMocks
    private MenuService menuService;

    @Mock
    private IngredientManager ingredientManager;

    @Mock
    private FoodManager foodManager;

    @Mock
    private FileManager fileManager;

    @Mock
    private DataManager dataManager;

    private RecipeData recipeData = new RecipeData();
    private IngredientData ingredientData = new IngredientData();

    @Test
    public void getAvailableMenu() throws ParseException {

        Recipe recipe = recipeData.createRecipeData();
        when(dataManager.getDataProvider(false)).thenReturn(fileManager);
        when(fileManager.getRecipes()).thenReturn(recipe);
        when(fileManager.getIngredient()).thenReturn(ingredientData.createData());
        when(ingredientManager.checkQuality(any())).thenReturn(new HashMap<>());

        Recipe result = menuService.getAvailableMenu();

        verify(fileManager, atLeast(1)).getRecipes();
        assertThat(result,equalTo(recipe));
    }

    @Test(expected = RecipeNotFoundException.class)
    public void getAvailableMenuWithEmptyRecipe() throws ParseException {

        when(dataManager.getDataProvider(false)).thenReturn(fileManager);
        when(fileManager.getRecipes()).thenReturn(new Recipe());
        when(fileManager.getIngredient()).thenReturn(ingredientData.createData());
        when(ingredientManager.checkQuality(any())).thenReturn(new HashMap<>());

        menuService.getAvailableMenu();
    }

    @Test(expected = IngredientNotFoundException.class)
    public void getAvailableMenuWithEmptyIngredient() throws ParseException {

        Recipe recipe = recipeData.createRecipeData();
        when(dataManager.getDataProvider(false)).thenReturn(fileManager);
        when(fileManager.getRecipes()).thenReturn(recipe);
        when(fileManager.getIngredient()).thenReturn(new Ingredient());
        when(ingredientManager.checkQuality(any())).thenReturn(new HashMap<>());

        menuService.getAvailableMenu();
    }
}