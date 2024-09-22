package com.ed;

import com.ibm.icu.text.RuleBasedNumberFormat;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ScriptSecond {

    private static final String[] digit = {
            "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять"
    };

    private static final String[] digitAfterTenToTwenty = {
            "", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};


    private static final String[] tens = {
            "", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    };

    private static final String[] hundreds = {
            "", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"
    };

    static RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);


    public static String convertPriceToWords(BigDecimal price) {

        if (price.compareTo(new BigDecimal("0")) == 0) return "ноль";

        BigInteger leftPartOfNumber = price.setScale(0, BigDecimal.ROUND_DOWN).toBigInteger();
        int rightPartOfNumber = price.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)).intValue();

        StringBuilder result = new StringBuilder();
        result.append(convertLeftPart(leftPartOfNumber)).append(". ").append(convertRightPart(rightPartOfNumber));
        return result.toString().trim();
    }

    private static String convertLeftPart(BigInteger rubles) {

        if (rubles.equals(BigInteger.ZERO)) return "ноль";

        StringBuilder result = new StringBuilder();

        BigInteger[] thousandsPart = rubles.divideAndRemainder(BigInteger.valueOf(1000));
        if (thousandsPart[0].compareTo(BigInteger.ZERO) > 0) {
            result.append(convertBelowThousand(thousandsPart[0].intValue(), true)).append(" ").append(getThousandWordForm(thousandsPart[0].intValue())).append(" ");
        }

        result.append(convertBelowThousand(thousandsPart[1].intValue(), false)).append(" ");

        return result.toString().trim();
    }

    private static String convertRightPart(int rightPart) {
        if (rightPart == 0) return "ноль";

        return convertBelowHundred(rightPart);
    }

    private static String convertBelowThousand(int number, boolean isThousand) {

        StringBuilder result = new StringBuilder();

        result.append(hundreds[number / 100]).append(" ");

        int ostatok = number % 100;

        if (ostatok > 10 && ostatok < 20) {
            result.append(digitAfterTenToTwenty[ostatok - 10]).append(" ");
        } else {
            result.append(tens[ostatok / 10]).append(" ");
            if (isThousand) {
                result.append(convertUnitForThousands(ostatok % 10)).append(" ");
            } else {
                result.append(digit[ostatok % 10]).append(" ");
            }
        }

        return result.toString().trim();
    }

    private static String convertBelowHundred(int number) {

        if (number <= 10) {
            return digit[number];
        } else if (number < 20) {
            return digitAfterTenToTwenty[number - 10];
        } else {
            return tens[number / 10] + " " + digit[number % 10];
        }
    }

    private static String getThousandWordForm(int thousands) {

        if (thousands % 10 == 1 && thousands % 100 != 11) {
            return "тысяча";
        } else if (thousands % 10 >= 2 && thousands % 10 <= 4 && (thousands % 100 < 10 || thousands % 100 >= 20)) {
            return "тысячи";
        } else {
            return "тысяч";
        }
    }

    private static String convertUnitForThousands(int number) {

        switch (number) {
            case 1: return "одна";
            case 2: return "две";
            default: return digit[number];
        }
    }

}
