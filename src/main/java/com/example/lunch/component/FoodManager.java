package com.example.lunch.component;

import com.example.lunch.bean.FoodCheckResult;
import com.example.lunch.bean.receipt.Recipe;
import com.example.lunch.bean.receipt.RecipeDetails;
import com.example.lunch.type.IngredientStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class FoodManager {

    /**
     * check food quality
     * if one of ingredient is expired, the recipe is on available
     * @param statusMap
     * @param recipe
     * @return
     */
    public void checkFoodQuality(Map<String, IngredientStatus> statusMap , Recipe recipe){

        List<RecipeDetails> normalQuality = new ArrayList<>();
        List<RecipeDetails> details = recipe.getRecipes();
        Iterator<RecipeDetails> iterator = details.iterator();

        //delete recipe with expired ingredient
        //add normal quality in a list
        while (iterator.hasNext()) {

            FoodCheckResult result = new FoodCheckResult();
            RecipeDetails recipeDetails = iterator.next();
            List<String> ingredientDetails = recipeDetails.getIngredients();

            int size = ingredientDetails.size();

            for(int i = 0 ; i < size ; i++){
                checkStatus(statusMap,ingredientDetails.get(i),result);

                if(result.isContainExpired())
                    break;
            }


            if(result.isContainNormal() || result.isContainExpired()){
                //add into the normal list
                if(result.isContainNormal())
                    normalQuality.add(recipeDetails);

                iterator.remove();
            }

        }

        //put the normal quality at end
        recipe.getRecipes().addAll(normalQuality);

    }

    /**
     * check status
     * @param statusMap
     * @param ingredient
     * @param result
     */
    private void checkStatus(Map<String, IngredientStatus> statusMap ,
                                        String ingredient,
                                        FoodCheckResult result){

        //assign expire status to non-exist ingredient
        IngredientStatus status = statusMap.getOrDefault(ingredient,IngredientStatus.EMPTY);

        switch(status){
            case BEST:
                break;
            case NORMAL :
                result.setContainNormal(true);
                break;

            //make sure other status should be removed
            case EXPIRE:
            default:
                result.setContainExpired(true);

        }


    }
}
