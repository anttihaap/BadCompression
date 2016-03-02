/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.performanceTesting;

import badcompression.compression.Compressor;
import badcompression.huffman.HuffmanCompressionByte;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.stream.FileImageOutputStream;

/**
 * TODO:
 * @author antti
 */
public class CompressionUncompressionTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10000; i+=100) {
            int bytes = 1000*i;
            System.out.println(bytes/1000 + "K bytes in random file - time: " + compressionTime(createRandomFile(bytes)));
        }
    }
    
    public static File createRandomFile(int length) throws IOException {
        File file = File.createTempFile("tmp", ".txt");
        FileImageOutputStream outStream = new FileImageOutputStream(file);
        byte[] randomBytes = new byte[length];
        Random r = new Random();
        r.nextBytes(randomBytes);
        outStream.write(randomBytes);
        outStream.flush();
        outStream.close();
        return file;
    }
    
    public static float compressionTime(File random) throws IOException {
        long startTimeMS = System.currentTimeMillis();
        Compressor comp = new HuffmanCompressionByte();
        comp.compress(random, File.createTempFile("tmp", ".txt"));
        return System.currentTimeMillis() - startTimeMS;
    }
    
}
