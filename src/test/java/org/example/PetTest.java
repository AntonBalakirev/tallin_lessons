package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PetTest {

    @Test
    public void catActionTest(){
        Cat cat = new Cat();
        Assertions.assertEquals("Meow", cat.voice(), "Unexpected cat voice:");
    }

    @Test
    public void dogActionTest(){
        Dog dog = new Dog();
        Assertions.assertEquals("Wouth", dog.voice(), "Unexpected dog voice:");
    }
}
