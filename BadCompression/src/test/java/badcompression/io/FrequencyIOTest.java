/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

import badcompression.huffman.HuffmanCoding;
import java.io.File;
import java.io.FileInputStream;
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
public class FrequencyIOTest {
    
    public FrequencyIOTest() {
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
    public void test() throws IOException {
        long[] freqExpected = generateFrequencies(256);
        File file = File.createTempFile("freq", ".txt");
        FrequencyIO.writeFreq(new FileOutputStream(file), new HuffmanCoding(freqExpected, true));
        long[] freqRead = FrequencyIO.readFrequencies(new FileInputStream(file), 256);
        assertArrayEquals(freqExpected, freqRead);
    }

    private static long[] generateFrequencies(int length) {
        long[] freq = new long[length];
        Random r = new Random();
        for (int i = 0; i < freq.length; i++) {
            freq[i] = r.nextInt(1000000000);
        }
        return freq;
    }
}
