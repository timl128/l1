package com.example.lunch.service;

import com.example.lunch.api.KitchenClient;
import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.bean.receipt.Recipe;
import com.example.lunch.component.DataManager;
import com.example.lunch.component.FoodManager;
import com.example.lunch.component.IngredientManager;
import com.example.lunch.exception.IngredientNotFoundException;
import com.example.lunch.exception.RecipeNotFoundException;
import com.example.lunch.type.IngredientStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private DataManager dataManager;

    @Autowired
    private IngredientManager ingredientManager;

    @Autowired
    private FoodManager foodManager;

    @Value("${data.local:true}")
    private boolean loadLocalData;

    /**
     * get and sort available menu
     * food with fresh ingredients will be at the top of list
     * @return
     */
    public Recipe getAvailableMenu(){

        //use local file
        KitchenClient client = dataManager.getDataProvider(loadLocalData);

        Recipe recipe = getRecipe(client);
        Ingredient ingredient = getIngredient(client);

        Map<String, IngredientStatus> ingredientStatusMap = ingredientManager.checkQuality(ingredient);
        foodManager.checkFoodQuality(ingredientStatusMap,recipe);
        return recipe;
    }

    /**
     * get recipe from api / local file
     * @return
     */
    private Recipe getRecipe(KitchenClient kitchenClient){

        Recipe recipe = kitchenClient.getRecipes();
        if(recipe.getRecipes().isEmpty())
            throw new RecipeNotFoundException();

        return recipe;
    }

    /**
     * get ingredient from api
     * @return
     */
    private Ingredient getIngredient(KitchenClient kitchenClient){

        Ingredient ingredient = kitchenClient.getIngredient();

        if(ingredient.getIngredients().isEmpty())
            throw new IngredientNotFoundException();

        return ingredient;
    }

}
