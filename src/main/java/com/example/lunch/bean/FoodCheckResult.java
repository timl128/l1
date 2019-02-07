package com.example.lunch.bean;

import lombok.Data;

@Data
public class FoodCheckResult {

    private boolean containExpired;
    private boolean containNormal;

    public FoodCheckResult() {
        this.containExpired = false;
        this.containNormal = false;
    }
}
