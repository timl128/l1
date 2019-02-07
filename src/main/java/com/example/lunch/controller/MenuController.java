package com.example.lunch.controller;

import com.example.lunch.bean.receipt.Recipe;
import com.example.lunch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * get recipe endpoint
     * @return
     */
    @GetMapping(value = "/lunch", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe getAvailableMenu(){
        return menuService.getAvailableMenu();
    }
}
