package com.example.lunch.type;

public enum IngredientStatus {

    EXPIRE(0),
    NORMAL(1),
    BEST(2),
    EMPTY(3);

    private int value;

    IngredientStatus(int id){
        this.value =id;
    }

    public int getValue(){
        return this.value;
    }
}
