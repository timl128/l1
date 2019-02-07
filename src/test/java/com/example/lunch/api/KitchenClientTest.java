package com.example.lunch.api;

import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.bean.receipt.Recipe;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest()
@TestPropertySource(locations = "/application-test.properties")
public class KitchenClientTest {

    private WireMockServer wireMockServer;

    @Autowired
    private KitchenClient kitchenClient;

    @Before
    public void setUp()  {

        wireMockServer = new WireMockServer(wireMockConfig().port(9001));
        wireMockServer.start();
    }

    @After
    public void stop(){
        wireMockServer.stop();
    }

    @Test
    public void getRecipes() {

        wireMockServer.stubFor(get("/recipes.php")
                .willReturn(okJson("{\"recipes\": [{\n" +
                        "\"title\": \"Ham and Cheese Toastie\",\n" +
                        "\"ingredients\": [\"Ham\", \"Cheese\", \"Bread\", \"Butter\"]\n" +
                        "}]}")));

        Recipe recipe = kitchenClient.getRecipes();
        assertThat(recipe.getRecipes().size(),equalTo(1));
    }

    @Test
    public void getIngredient() {

        wireMockServer.stubFor(get("/ingredients.php")
                .willReturn(okJson("{\n" +
                        "\"ingredients\": [{\n" +
                        "\"title\": \"Ham\",\n" +
                        "\"best-before\": \"2018-11-14\",\n" +
                        "\"use-by\": \"2018-11-19\"\n" +
                        "}, {\n" +
                        "\"title\": \"Cheese\",\n" +
                        "\"best-before\": \"2018-11-14\",\n" +
                        "\"use-by\": \"2018-11-19\"\n" +
                        "}]}")));

       Ingredient ingredientDetailsList = kitchenClient.getIngredient();
        assertThat(ingredientDetailsList.getIngredients().size(),equalTo(2));
    }
}