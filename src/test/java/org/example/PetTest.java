package org.example;

import org.junit.jupiter.api.*;

public class PetTest {

    static Cat cat;
    static Dog dog;

    @BeforeAll
    public static void startUp(){
        cat = new Cat();
        dog = new Dog();
        System.out.println("Выполняется до запуска всех тестов");
    }

    @BeforeEach
    public void startUpEach(){
        System.out.println("Выполняется до запуска каждого теста");
    }

    @AfterEach
    public void shotDownEach(){
        System.out.println("Выполняется после выполнения каждого теста");
    }

    @AfterAll
    public static void shotDown(){
        cat = null;
        dog = null;
        System.out.println("Выполняется после выполнения всех тестов");
    }


    @Test
    public void catActionTest(){
        Cat cat = new Cat();
        Assertions.assertEquals("Meow", cat.voice(), "Unexpected cat voice:");
    }

    @Test
    public void dogActionTest(){
        Assertions.assertEquals("Wouth", dog.voice(), "Unexpected dog voice:");
    }
}
