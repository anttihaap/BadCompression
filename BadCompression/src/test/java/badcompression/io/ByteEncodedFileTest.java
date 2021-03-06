
package badcompression.io;

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
public class ByteEncodedFileTest {
    
    private final byte[] testBytes = {1,12,-1,12,51,-10,99};
    private final int[] expectedUnsignedBytes = {1,12,255,12,51,246,99};
    
    private ByteEncodedFile encoded;
    
    
    public ByteEncodedFileTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.encoded = new ByteEncodedFile(testBytes);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void bytesReadInCorrectOrder() {
        for (int i = 0; i < expectedUnsignedBytes.length;i++) {
            assertEquals(expectedUnsignedBytes[i],encoded.getNextCharacter());
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(-1, encoded.getNextCharacter());
        }
    }
    
    @Test
    public void length() {
        assertEquals(testBytes.length, encoded.getAmountOfBytes());
    }
    
    @Test
    public void reset() {
        for (int i = 1; i < 10; i++) {
        for (int j = 0; j < expectedUnsignedBytes.length / i;j++) {
            assertEquals(expectedUnsignedBytes[j], encoded.getNextCharacter());
        }
        encoded.resetReader();
        }
    }
    
    @Test
    public void returnBytesArray() {
        assertEquals(testBytes, encoded.getBytes());
    }
}
