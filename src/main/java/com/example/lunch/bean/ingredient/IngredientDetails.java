package com.example.lunch.bean.ingredient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class IngredientDetails {

    private String title;

    @JsonProperty( value = "best-before" )
    @JsonFormat( pattern = "yyyy-MM-dd")
    private Date bestBefore;

    @JsonProperty( value = "use-by" )
    @JsonFormat( pattern = "yyyy-MM-dd")
    private Date useBy;

    public IngredientDetails(String title, Date bestBefore, Date useBy) {
        this.title = title;
        this.bestBefore = bestBefore;
        this.useBy = useBy;
    }

    public IngredientDetails() {
    }
}
