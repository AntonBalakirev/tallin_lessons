package org.example.junit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static void outOfBounds() {
        int[] nums = new int[4];

        //Блок try
        try {
            System.out.println("Message before exception");

            //Попытка обратиться за границы массива
            nums[7] = Integer.MAX_VALUE;
            System.out.println("This is not to be displayed");
        }
        //Перехват исключения при обращения за границы массива
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bound. Exception caught");
        }
        //Этот блок выполняется в любом случае
        finally {
            System.out.println("Message from finally block");
        }
        System.out.println("Message after exception");
    }

    //метод считывает строку с клавиатуры
    public static String input() {
        //Задание чтения строки из консоли
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        //в блок try указываем код, который может выбросить исключение
        try {
            s = reader.readLine();
            //в блок catch указываем код, который сработает при возникновении исключения
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //Закрываем поток чтения
        } finally {
            //При закрытии потока, также может возникнуть исключение
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return s;
    }

    public static String inputThrows() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        s = reader.readLine();
        reader.close();
        return s;
    }
}
