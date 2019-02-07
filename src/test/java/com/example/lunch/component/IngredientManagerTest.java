package com.example.lunch.component;

import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.setup.IngredientData;
import com.example.lunch.type.IngredientStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class IngredientManagerTest {

    @InjectMocks
    private IngredientManager ingredientManager;

    @Mock
    private DateManager dateManager;

    private Ingredient ingredient;

    private IngredientData ingredientData = new IngredientData();

    @Before
    public void setup() throws ParseException {
        ingredient = ingredientData.createData();
    }

    @Test
    public void testCheckQuality() throws ParseException {

        Date date = ingredientData.createDateFromString("2019-01-01");
        when(dateManager.getDate()).thenReturn(date);

        Map<String, IngredientStatus> map = ingredientManager.checkQuality(ingredient);
        assertThat(map.size(),equalTo(4));
        assertThat(map.get("a"),equalTo(IngredientStatus.EXPIRE));
        assertThat(map.get("b"),equalTo(IngredientStatus.NORMAL));
        assertThat(map.get("c"),equalTo(IngredientStatus.BEST));
        assertThat(map.get("d"),equalTo(IngredientStatus.BEST));
    }

    @Test
    public void testCheckQualityWithNull() throws ParseException {

        ingredient = ingredientData.createNullData();
        Date date = ingredientData.createDateFromString("2019-01-01");
        when(dateManager.getDate()).thenReturn(date);

        Map<String, IngredientStatus> map = ingredientManager.checkQuality(ingredient);
        assertThat(map.size(),equalTo(3));
        assertThat(map.get("a"),equalTo(IngredientStatus.EXPIRE));
        assertThat(map.get("b"),equalTo(IngredientStatus.NORMAL));
        assertThat(map.get("c"),equalTo(IngredientStatus.EXPIRE));
    }
}