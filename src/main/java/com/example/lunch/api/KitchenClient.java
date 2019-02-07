package com.example.lunch.api;

import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.bean.receipt.Recipe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "kitchen", url = "${kitchen.url}")
@Service
public interface KitchenClient {

    @RequestMapping(method = RequestMethod.GET, value = "/recipes.php")
    Recipe getRecipes();

    @RequestMapping(method = RequestMethod.GET, value = "/ingredients.php")
    Ingredient getIngredient();
}
