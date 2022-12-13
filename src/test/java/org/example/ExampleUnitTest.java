package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExampleUnitTest {

    @Test
    public void exampleTest(){
        int width = 10;
        int length = 10;

        int area = width * length;
        Assertions.assertEquals(100, area, "Ожидаемое значние площади не равно актуальному: ");
        Assertions.assertTrue(area == 100, "Ожидаемое значние площади не равно актуальному:");
        Assertions.assertFalse(area == 500, "Ожидаемое значние площади не равно актуальному:");
    }

    @Test
    public void assertionsNullTest(){
        Cat cat = null;
        Assertions.assertNull(cat, "Ожидаемый объект инициализирован: ");
        Assertions.assertTrue(cat == null, "Ожидаемый объект инициализирован: ");
        Assertions.assertEquals(cat, null, "Ожидаемый объект инициализирован: ");
        cat = new Cat();
        cat.age = 2;
        Assertions.assertNotNull(cat, "Ожидаемый объект не инициализирован: ");
    }

    @Test
    public void assertSameTest(){
        Cat cat = new Cat();
        Cat kitty = cat;

        Assertions.assertSame(cat, kitty, "Коты разные");
    }
}
