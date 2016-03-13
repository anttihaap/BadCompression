/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.compression.util;

import java.util.Random;

/**
 * Generates random strings.
 * @author antti
 */
public class StringGenerator {
    
    /**
     * Creates a random string.
     * @param length Length of string.
     * @return Random string.
     */
    public static String randomString(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(length);
        while (sb.length() < length) {
            char c = (char) r.nextInt(Character.MAX_VALUE);
            boolean isValid = Character.isLetter(c) || Character.isWhitespace(c)
                    || Character.isDigit(c);
            if (isValid) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
}
