package com.solvians.constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CharacterMappingTest {

    private String input;
    private int expected;
    public CharacterMappingTest(String input, int expected ){
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: intval({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "A", 10 },{ "B", 11 },{ "C", 12 },{ "D", 13 },{ "E", 14 },{ "F", 15 },{ "G", 16 },{ "H", 17 },{ "I", 18 },{ "J", 19 },
                { "K", 20 },{ "L", 21 },{ "M", 22 },{ "N", 23 },{ "O", 24 },{ "P", 25 },{ "Q", 26 },{ "R", 27 },{ "S", 28 },{ "T", 29 },
                { "U", 30 },{ "V", 32 },{ "W", 32 },{ "X", 33 },{ "Y", 34 },{ "Z", 35 }
        });
    }


    @Test
    public void evaluateValue()
    {
        assertEquals((Integer) expected, (Integer) CertificateConstants.characterMap.get(input));
    }



}