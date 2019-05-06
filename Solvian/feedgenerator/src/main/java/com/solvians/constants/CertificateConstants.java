package com.solvians.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CertificateConstants {


    public static Map<String, Integer> characterMap = Stream.of(new Object[][] {
            { "A", 10 },{ "B", 11 },{ "C", 12 },{ "D", 13 },{ "E", 14 },{ "F", 15 },{ "G", 16 },{ "H", 17 },{ "I", 18 },{ "J", 19 },
            { "K", 20 },{ "L", 21 },{ "M", 22 },{ "N", 23 },{ "O", 24 },{ "P", 25 },{ "Q", 26 },{ "R", 27 },{ "S", 28 },{ "T", 29 },
            { "U", 30 },{ "V", 32 },{ "W", 32 },{ "X", 33 },{ "Y", 34 },{ "Z", 35 },
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

}
