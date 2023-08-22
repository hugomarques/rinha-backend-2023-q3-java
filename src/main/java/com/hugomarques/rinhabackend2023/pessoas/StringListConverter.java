package com.hugomarques.rinhabackend2023.pessoas;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public class StringListConverter {
    private static final String SPLIT_CHAR = ";";

    public static String convert(List<String> stringList) {
        return stringList != null ? String.join(SPLIT_CHAR, stringList) : null;
    }

    public static List<String> convert(String string) {
        return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();
    }
}
