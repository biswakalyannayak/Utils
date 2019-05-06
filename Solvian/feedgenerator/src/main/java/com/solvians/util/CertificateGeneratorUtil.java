package com.solvians.util;

import com.solvians.constants.CertificateConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CertificateGeneratorUtil {

    public static String doubleEveryOtherDigitFromRight(String integerStringValue){
        List<String> expectedReversedList = new ArrayList<>();
        String [] individualStringArr = integerStringValue.split("");
        for (int i = (individualStringArr.length-1); i< individualStringArr.length; i--){
            System.out.printf(individualStringArr[i]);
        }
        return "";
    }

    public static String replaceAlphabetWithIntValue(String ISIN){
        String localIsin = ISIN;
        if(StringUtils.isNotEmpty(localIsin)){
            String alphabets = localIsin.replaceAll("\\d+","");
            if(StringUtils.isNotEmpty(alphabets)){
                String [] alphabetsArr = alphabets.split("");
                for (String alpha: alphabetsArr) {
                    localIsin = localIsin.replace(alpha, String.valueOf(CertificateConstants.characterMap.get(alpha)));
                }
            }
        }
        return localIsin;
    }

    public static int addUpStringDigits(String[] inputArr){
        int defaultValue = 0;
        if(inputArr != null){
            for (String digitValue : inputArr) {
                if(digitValue.matches("\\d+")){
                    defaultValue = defaultValue+Arrays.stream(digitValue.split("")).mapToInt(Integer::parseInt).sum();
                }
            }
        }
        return defaultValue;
    }

    public static String generateIsin(){
        String baseIsin = String.valueOf(getRandomCharacter())+ String.valueOf(getRandomCharacter())+
                getRandomNineDigitNumber();
        int checkpoint = addUpStringDigits(replaceAlphabetWithIntValue(baseIsin).split(""));
        return baseIsin+checkpoint;
    }

    public static int getRandomIntegerBetweenRange(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }

    public static int getRandomNineDigitNumber(){
        Random random = new Random();
        return random.ints(000000001,999999999).findFirst().getAsInt();
    }

    public static double getRandomDoubleBetweenRange(double min, double max){
        Random random = new Random();
        return random.doubles(min,(max+1)).findFirst().getAsDouble();
    }

    public static char getRandomCharacter(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        return chars.charAt(rnd.nextInt(chars.length()));
    }
}
