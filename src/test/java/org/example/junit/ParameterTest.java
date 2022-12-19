package org.example.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class ParameterTest {

    @ParameterizedTest
    @ValueSource(ints = {0,1,3,5,-14,Integer.MAX_VALUE})
    void valueSourceTest(int number){
        Assertions.assertTrue(Numbers.isOdd(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void isBlankValueSourceTest(String input) {
        Assertions.assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullSource
    void nullValueSourceTest(String input) {
        Assertions.assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @EmptySource
    void emptyValueSourceTest(String input) {
        Assertions.assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void emptyOrNullValueSourceTest(String input) {
        Assertions.assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @EnumSource(Month.class) // passing all 12 months
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        Assertions.assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }

    @ParameterizedTest
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    void toUpperCaseShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        Assertions.assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    void toLowerCaseShouldGenerateTheExpectedLowercaseValue(String input, String expected) {
        String actualValue = input.toLowerCase();
        Assertions.assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", useHeadersInDisplayName = true)
    void csvFileTestSource(String country, String city){
        Assertions.assertNotNull(country);
        Assertions.assertFalse(city.isBlank());
    }

}
