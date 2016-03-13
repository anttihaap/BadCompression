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

//    @Test
//    public void test() throws IOException {
//        for (int i = 0; i < 5; i++) {
//            testRandomOutput(Character.MAX_CODE_POINT);
//        }
//    }

    private void testRandomOutput(int length) throws IOException {
        long[] freqExpected = generateFrequencies(length);
        File file = File.createTempFile("freq", ".txt");
        FrequencyIO.writeFreq(new FileOutputStream(file), new HuffmanCoding(freqExpected, true));
        long[] freqRead = FrequencyIO.readFrequencies(new FileInputStream(file), length);
        assertArrayEquals(freqExpected, freqRead);
    }

    private static long[] generateFrequencies(int length) {
        long[] freq = new long[length];
        Random r = new Random();
        for (int i = 0; i < freq.length; i++) {
            long value;
            
            //Find positive long value.
            while (true) {
                long tmp = r.nextLong();
                if (tmp >= 0) {
                    value = tmp;
                    break;
                }
            }
            freq[i] = value;
        }
        return freq;
    }
}
