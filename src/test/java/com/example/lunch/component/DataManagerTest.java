package com.example.lunch.component;

import com.example.lunch.api.KitchenClient;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class DataManagerTest {

    @InjectMocks
    private DataManager dataManager;

    @Mock
    private KitchenClient kitchenClient;

    @Mock
    private FileManager fileManager;

    @Test
    public void getDataProvider() {

        KitchenClient kitchenClient = dataManager.getDataProvider(true);
        assertThat(kitchenClient, new IsInstanceOf(FileManager.class));

    }

    @Test
    public void getDataProviderWithApi() {

        KitchenClient kitchenClient = dataManager.getDataProvider(false);
        assertThat(kitchenClient, new IsInstanceOf(KitchenClient.class));

    }
}