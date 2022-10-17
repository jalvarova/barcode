package org.walavo.bar.generate.util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public final class Util {

    public static String generateValue() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String randomString() {
        return RandomStringUtils.randomAlphabetic(8).toUpperCase();
    }
}
