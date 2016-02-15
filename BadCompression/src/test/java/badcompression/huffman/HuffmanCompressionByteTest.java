/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author antti
 */
public class HuffmanCompressionByteTest {

    public HuffmanCompressionByteTest() {
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
    public void testUTF8Files() throws Exception {
        CompressorTests.testUTF8Files(new HuffmanCompressionByte());
    }

    @Test
    public void randomData() throws Exception {
        compressUncompressTest("randomData/random1.dat");
        //uncomment to loose more time
        //compressUncompressTest("randomData/random2.dat");
        // compressUncompressFromResourceTest("randomData/random3.dat");
    }

    public void compressUncompressTest(String filePath) throws Exception {
        CompressorTests.compressUncompressFromResourceTest(new HuffmanCompressionByte(), filePath);
    }

}
