/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.performanceTesting;

import badcompression.compression.Compressor;
import badcompression.compression.util.StringGenerator;
import badcompression.huffman.HuffmanCompressionByte;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Testing HuffmanCodingByte and HuffmanCodingUTF8 performance.
 *
 * @author antti
 */
public class CompressionUncompressionTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Byte compression times: ");
        for (int i = 1; i <= 100; i++) {
            int bytes = 1000 * i * 100;

            long[] results = compUncompTimes(createRandomFile(bytes), new HuffmanCompressionByte());
            System.out.println(bytes + " " + (results[0] / 1000.0) + " " + (results[1] / 1000.0));
        }

        System.out.println("UTF8 comp/uncomp times: ");
        for (int i = 1; i <= 100; i++) {
            int length = 1000 * i * 100;
            File random = createRandomUTF8File(length);
            long bytes = random.length();

            long[] results = compUncompTimes(random, new HuffmanCompressionByte());
            System.out.println(bytes + " " + (results[0] / 1000.0) + " " + (results[1] / 1000.0));
        }
    }

    public static File createRandomFile(int length) throws IOException {
        File file = File.createTempFile("tmp", ".txt");
        FileOutputStream outStream = new FileOutputStream(file);
        byte[] randomBytes = new byte[length];
        Random r = new Random();
        r.nextBytes(randomBytes);
        outStream.write(randomBytes);
        outStream.flush();
        outStream.close();
        return file;
    }

    public static File createRandomUTF8File(int length) throws IOException {
        File file = File.createTempFile("tmp", ".txt");
        String randomString = StringGenerator.randomString(length);
        FileOutputStream out = new FileOutputStream(file);
        out.write(randomString.getBytes("UTF-8"));
        out.flush();
        out.close();
        return file;
    }

    public static long[] compUncompTimes(File random, Compressor comp) throws IOException {
        long[] results = new long[2];
        long startTimeMS = System.currentTimeMillis();
        File compressedTo = File.createTempFile("tmp", ".txt");
        comp.compress(random, compressedTo);
        results[0] = System.currentTimeMillis() - startTimeMS;
        startTimeMS = System.currentTimeMillis();
        comp.uncompress(compressedTo, File.createTempFile("tmp", ".txt"));
        results[1] = System.currentTimeMillis() - startTimeMS;
        return results;
    }

}