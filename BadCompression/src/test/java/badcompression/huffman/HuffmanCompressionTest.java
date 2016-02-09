/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import badcompression.io.ByteEncodedFile;
import badcompression.io.EncodedFile;
import java.io.ByteArrayInputStream;
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
public class HuffmanCompressionTest {

    private Random r;

    public HuffmanCompressionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        r = new Random();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void encodeDecodeComparison() throws IOException {
        byte[] original = getRandomByteArray(1000);
        long[] freq = HuffmanCompressionByte.getFreq(original);
        HuffmanCoding hffCodining = new HuffmanCoding(freq, true);
        EncodedFile testFile = new ByteEncodedFile(original);
        byte[] encoded = HuffmanCompression.encode(testFile, hffCodining);
        byte[] decoded = HuffmanCompressionByte.decode(hffCodining.getHuffTree(), new ByteArrayInputStream(encoded));
        compareByteArrayIsIdentical(original, decoded);
    }

    private void compareByteArrayIsIdentical(byte[] expected, byte[] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    public byte[] getRandomByteArray(int arrLength) {
        byte[] arr = new byte[arrLength];
        r.nextBytes(arr);
        return arr;
    }

}
