package com.cherentsov.animalback;

import com.cherentsov.animalback.Model.*;
import com.cherentsov.animalback.Service.DBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ExampleDataTest {

    @Test
    public void addExampleToDB() {
        //Проверка заполнения таблиц тестовыми данными
        ExampleData.AddExampleToDB();
        DBService dBService = DBService.getInstance();

        Region region = dBService.read(Region.class, 1L);
        assertEquals(region.getName(), "Новосибирск");

        List<Region> lRegion = dBService.getAll(Region.class);
        assertEquals(lRegion.size(),5);

        lRegion = dBService.findByName(Region.class, "Новосибирск");
        assertEquals((long)lRegion.get(0).getId(), 1L);

        List<Location> lLocation = dBService.findByName(Location.class, "Ермолинская птицефабрика");
        List<SkinColor> lSkinColor = dBService.findByName(SkinColor.class, "Чёрный");
        List<AnimalType> lAnimalType = dBService.findByName(AnimalType.class, "Утка");

        List<Pet> lPet = dBService.findPetByFK(lRegion, lAnimalType, lSkinColor);
        assertEquals((long)lPet.size(), 1);

    }
}