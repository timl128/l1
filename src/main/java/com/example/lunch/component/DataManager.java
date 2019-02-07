package com.example.lunch.component;

import com.example.lunch.api.KitchenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataManager {

    @Autowired
    private KitchenClient kitchenClient;

    @Autowired
    private FileManager fileManager;

    /**
     * get data source
     * @param local
     * @return
     */
    public KitchenClient getDataProvider(boolean local){

        if(local){
            return fileManager;
        }
        else
        {
            return kitchenClient;
        }
    }
}
