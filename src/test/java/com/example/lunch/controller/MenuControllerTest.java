package com.example.lunch.controller;

import com.example.lunch.bean.receipt.Recipe;
import com.example.lunch.service.MenuService;
import com.example.lunch.setup.RecipeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuService menuService;

    private RecipeData recipeData = new RecipeData();

    @Test
    public void getAvailableMenu() throws Exception{

        Recipe recipe = recipeData.createRecipeData();
        when(menuService.getAvailableMenu()).thenReturn(recipe);
        mvc.perform(get("/lunch")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipes", hasSize(recipe.getRecipes().size())))
                .andExpect(jsonPath("$.recipes[0].title",
                        is(recipe.getRecipes().get(0).getTitle())));

    }
}