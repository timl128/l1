package com.example.lunch.component;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateManager {

    public Date getDate(){
        return new Date();
    }
}
