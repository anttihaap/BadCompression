/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antti
 */
public class HuffmanCompressionUTF8Test {
    
    public HuffmanCompressionUTF8Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void utf8FileAreIdentical() throws Exception {
        CompressorTests.testUTF8Files(new HuffmanCompressionUTF8());
    }
    
// TODO: fix code for test.
//    @Test
//    public void randomOutput100Times() throws Exception {
//        for (int i = 0; i < 1000; i++) {
//            randomOutput(1000);
//        }
//    }
    
    public void randomOutput(int length) throws IOException, Exception {
        File rmp = File.createTempFile("temp3", ".txt");
        System.out.println("created random to: " + rmp.toPath());
        FileOutputStream stream = new FileOutputStream(rmp);
        stream.write(randomString(length).getBytes("UTF-8"));
        stream.flush();
        stream.close();
        CompressorTests.compressUncompressTest(new HuffmanCompressionUTF8(), rmp.getPath());
    }
    
    private String randomString(int length) {
        Random r = new Random();
        
        char[] charArr = new char[length];
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = (char) r.nextInt(Character.MAX_VALUE+1);
        }
        return new String(charArr);
    }
}
