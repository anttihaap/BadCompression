package badcompression.huffman;

import badcompression.io.ByteEncodedFile;
import badcompression.io.EncodedFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void encodeDecode100RandomData() throws IOException {
        for (int i = 0; i < 1000; i++) {
            encodeDecodeRandomData(1000);
        }
    }
    
    public void encodeDecodeRandomData(int dataLengthInBytes) throws IOException {
        byte[] original = getRandomByteArray(dataLengthInBytes);
        EncodedFile testFile = new ByteEncodedFile(original);
        long[] freq = testFile.getFreq();
        HuffmanCoding hffCodining = new HuffmanCoding(freq, true);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        HuffmanCompression.encode(hffCodining, testFile, out);
        byte[] encoded = out.toByteArray();
        out = new ByteArrayOutputStream();
        HuffmanCompressionByte.decode(hffCodining.getHuffTree(), new ByteArrayInputStream(encoded), out);
        out.flush();
        out.close();
        byte[] decoded = out.toByteArray();
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