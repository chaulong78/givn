package com.msp.givn.utility;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringFunction {

    public static String convertNameToUrl(String name) {
        String temp = Normalizer.normalize(name, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        name = pattern.matcher(temp).replaceAll("");
        name = name.toLowerCase();
        name = name.replace(" ", "-");
        name = name.replace("đ", "d");
        return name;
    }
}
