/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private byte[] testBytes = {1,12,-1,12,51,-10,99};
    private int[] expectedUnsignedBytes = {1,12,255,12,51,246,99};
    
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
        assertEquals(testBytes.length, encoded.getByesInFile());
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
