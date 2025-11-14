package com.bastagruppen.springskolsystem.util;

import java.util.regex.Pattern;

public final class RegexUtil {

    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @SuppressWarnings("SameParameterValue")
    private static boolean patternMatcher(CharSequence input, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(input)
                .matches();
    }

    public static boolean emailMatcher(String email) {
        return patternMatcher(email, EMAIL_PATTERN);
    }
}