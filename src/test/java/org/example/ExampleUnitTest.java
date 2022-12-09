package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExampleUnitTest {

    @Test
    public void exampleTest(){
        int width = 10;
        int length = 10;

        int area = width * length;
        Assertions.assertEquals(50, area, "Ожидаемое значние площади не равно актуальному: ");
    }
}
