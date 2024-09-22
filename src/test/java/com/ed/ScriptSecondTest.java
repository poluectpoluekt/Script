package com.ed;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ScriptSecondTest {


    @Test
    void test() {

        BigDecimal sourcePrice = new BigDecimal("99999.99");

        String expectPriceString = "девяносто девять тысяч девятьсот девяносто девять. девяносто девять";
        String resultPriceString = ScriptSecond.convertPriceToWords(sourcePrice);

        assertEquals(expectPriceString, resultPriceString);
    }


    @Test
    void testZeroCheck() {

        BigDecimal sourcePrice = new BigDecimal("0");

        String expectPriceString = "ноль";
        String resultPriceString = ScriptSecond.convertPriceToWords(sourcePrice);

        assertEquals(expectPriceString, resultPriceString);
    }

    @Test
    void testZeroCheckLeftPart() {

        BigDecimal sourcePrice = new BigDecimal("0.99");

        String expectPriceString = "ноль. девяносто девять";
        String resultPriceString = ScriptSecond.convertPriceToWords(sourcePrice);

        assertEquals(expectPriceString, resultPriceString);
    }

    @Test
    void testZeroCheckRightPart() {

        BigDecimal sourcePrice = new BigDecimal("99999.0");

        String expectPriceString = "девяносто девять тысяч девятьсот девяносто девять. ноль";
        String resultPriceString = ScriptSecond.convertPriceToWords(sourcePrice);

        assertEquals(expectPriceString, resultPriceString);
    }

}