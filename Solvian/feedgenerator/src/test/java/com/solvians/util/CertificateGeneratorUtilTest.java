package com.solvians.util;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CertificateGeneratorUtilTest {

    @Test
    public void doubleEveryOtherDigitFromRight() {
        System.out.println(CertificateGeneratorUtil.getRandomDoubleBetweenRange(100.00,200.00));
        System.out.println(CertificateGeneratorUtil.getRandomIntegerBetweenRange(1000,10000));
        System.out.println(CertificateGeneratorUtil.getRandomCharacter());
        System.out.println(CertificateGeneratorUtil.getRandomNineDigitNumber());

        String isin = "1314123456789";
        String expected = "2324226410614818";
        assertEquals(expected,CertificateGeneratorUtil.doubleEveryOtherDigitFromRight(isin));
    }

    @Test
    public void replaceAlphabetWithIntValue() {
        String isin = "DE123456789";
        String expected = "1314123456789";
        assertEquals(expected,CertificateGeneratorUtil.replaceAlphabetWithIntValue(isin));
    }
    @Test
    public void replaceAlphabetWithIntValueForEmpty() {
        String isin = "";
        assertEquals(isin,CertificateGeneratorUtil.replaceAlphabetWithIntValue(isin));
    }

    @Test
    public void replaceAlphabetWithIntValueWithoutAlphabetString() {
        String isin = "123456789";
        String expected = "123456789";
        assertEquals(expected,CertificateGeneratorUtil.replaceAlphabetWithIntValue(isin));
    }
    @Test
    public void replaceAlphabetWithIntValueWithRepeatAlpbabet() {
        String isin = "DD123456789";
        String expected = "1313123456789";
        assertEquals(expected,CertificateGeneratorUtil.replaceAlphabetWithIntValue(isin));
    }

    @Test
    public void addUpStringDigits() {
        String[] input = {"2","3","2","4","2","2","6","4","10","6","14","8","18"};
        int expected = 54;
        assertEquals(expected,CertificateGeneratorUtil.addUpStringDigits(input));
    }

    @Test
    public void addUpStringDigitsForEmptyArr() {
        String[] input = {};
        int expected = 0;
        assertEquals(expected,CertificateGeneratorUtil.addUpStringDigits(input));
    }

    @Test
    public void addUpStringDigitsForAlphabetArr() {
        String[] input = {"A"};
        int expected = 0;
        assertEquals(expected,CertificateGeneratorUtil.addUpStringDigits(input));
    }
    @Test
    public void addUpStringDigitsForNullImput() {
        int expected = 0;
        assertEquals(expected,CertificateGeneratorUtil.addUpStringDigits(null));
    }
}