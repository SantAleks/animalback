package com.cherentsov.animalback.Model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

class AnimalTypeTest {

    //Тест по сущности со 100% покрытием
    @Test
    void main() {
        AnimalType chicken = new AnimalType((long) -1,"Курица");
        assertEquals((long)chicken.getId(), -1L);
        assertEquals(chicken.getName(), "Курица");

        AnimalType animalType = new AnimalType();
        assertEquals(animalType.toString().getClass(), String.class);

        //проверка сетеров
        animalType.setId(-1L);
        animalType.setName("Курица");

        //проверка икуалса и хеша
        assertEquals(animalType, chicken);
        assertFalse(animalType.equals(null));
        assertEquals(animalType.hashCode(),chicken.hashCode());
    }
}