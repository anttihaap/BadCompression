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
 * 
 * @author antti
 */
public class HuffmanCodingIO {

    //TODO comment
    public final static int byteSeparator = 255;

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
        return baos.toString();
    }

    public static void writeFreq(OutputStream outStream, HuffmanCoding huffCoding) {
        long[] char_freq = huffCoding.getCharFreq();
        try {
            for (int i = 0; i < huffCoding.getCharFreq().length; i++) {
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
                        str += char_freq[i];
                        has = true;
                    } else {
                        str = " " + char_freq[i];
                    }
                    outStream.write(str.getBytes("UTF-8"));
                }
            }
            outStream.write(255);
            outStream.flush();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println("exp");
        }
    }

}
