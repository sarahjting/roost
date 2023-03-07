package com.sarahjting.roost.common.util;

import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class StringFormatter {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    // https://stackoverflow.com/a/1657250
    public static String toSlug(String input) {
        String noWhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static String fileNameToSlug(String input) {
        int i = input.lastIndexOf('.');
        String[] originalFileNameParts = {input.substring(0, i), input.substring(i + 1)};
        String output = toSlug(originalFileNameParts[0]);
        if (originalFileNameParts[1] != null) {
            output = String.format("%s.%s", output, toSlug(originalFileNameParts[1]));
        }
        return output;
    }
}
