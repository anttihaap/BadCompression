/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

import badcompression.huffman.HuffmanCoding;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Writes and read frequencies from/to file.
 *
 * @author antti
 */
public class FrequencyIO {

    /**
     * Uses 255 byte for separation.
     */
    public final static int byteSeparator = 255;

    /**
     * Reads frequencies from the begining of a file.
     *
     * @param in
     * @param characters
     * @return
     * @throws IOException
     */
    public static long[] readFrequencies(InputStream in, int characters) throws IOException {
        String s1 = returnStringUntillSeparator(in);
        String s2 = returnStringUntillSeparator(in);

        long[] freq = new long[characters];
        String[] character_values = s2.split(" ");
        for (int i = 0; i < s1.length(); i++) {
            long value = Long.parseLong(character_values[i]);
            freq[s1.charAt(i)] = value;
        }
        return freq;
    }

    private static String returnStringUntillSeparator(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int read;
        while ((read = in.read()) != 255) {
            baos.write(read);
        }
        return new String(baos.toByteArray(), "UTF-8");
    }

    /**
     * Writes frequencies to file.
     *
     * @param outStream
     * @param huffCoding
     */
    public static void writeFreq(OutputStream outStream, HuffmanCoding huffCoding) throws IOException {
        long[] char_freq = huffCoding.getCharFreq();

        for (int i = 0; i < char_freq.length; i++) {
            if (huffCoding.getCharCode(i) != null) {
                String out = "" + (char) i;
                outStream.write(out.getBytes("UTF-8"));
            }
        }
        outStream.write(255);
        boolean has = false;
        for (int i = 0; i < char_freq.length; i++) {
            if (char_freq[i] != 0) {
                String str = "";
                if (!has) {
                    str += String.valueOf(char_freq[i]);
                    has = true;
                } else {
                    str = " " + String.valueOf(char_freq[i]);
                }
                outStream.write(str.getBytes("UTF-8"));
            }
        }
        outStream.write(255);
        outStream.flush();
    }
}