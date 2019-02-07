package com.example.lunch.component;

import com.example.lunch.bean.ingredient.Ingredient;
import com.example.lunch.bean.ingredient.IngredientDetails;
import com.example.lunch.type.IngredientStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IngredientManager {

    @Autowired
    private DateManager dateManager;

    /**
     * check ingredient quality and assign status
     * assumption bestBefore must be before useBy
     * @param ingredient
     * @return
     */
    public Map<String, IngredientStatus> checkQuality(Ingredient ingredient){

        List<IngredientDetails> detailsList = ingredient.getIngredients();
        Map<String,IngredientStatus> statusMap = new HashMap<>();
        Date today = dateManager.getDate();
        detailsList.forEach(
                i -> {
                    if(checkDate(today,i.getBestBefore())){
                        statusMap.put(i.getTitle(),IngredientStatus.BEST);
                    }
                    else if(checkDate(today,i.getUseBy())){
                        statusMap.put(i.getTitle(),IngredientStatus.NORMAL);
                    }
                    else{
                        statusMap.put(i.getTitle(),IngredientStatus.EXPIRE);
                    }

                }
        );

        return statusMap;
    }

    /**
     * compare date
     * return true if ingredientDate come after
     * @param date
     * @param ingredientDate
     * @return
     */
    private boolean checkDate(Date date, Date ingredientDate){

        if(  ingredientDate != null  &&  (date.before(ingredientDate) || date.equals(ingredientDate))   )
            return true;

        return false;
    }

}
