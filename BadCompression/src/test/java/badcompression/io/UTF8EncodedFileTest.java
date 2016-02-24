/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

import java.io.UnsupportedEncodingException;
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
public class UTF8EncodedFileTest {
    
    
    public UTF8EncodedFileTest() {
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
    public void test() throws UnsupportedEncodingException {
        String expected = new String(generateTestInput(10000));
        UTF8EncodedFile encoded = new UTF8EncodedFile(expected.getBytes("UTF-8"));
        for (int i = 0; i < expected.length(); i++) {
            char c = (char) encoded.getNextCharacter();
            if ((int) c != (int) expected.charAt(i)) {
            System.out.println(expected.charAt(i) + " - " + c + " - " + (int) c + " - " + (int) expected.charAt(i));
            }
            assertArrayEquals((expected.charAt(i) + "").getBytes("UTF-8"), (c + "").getBytes("UTF-8"));
        }
        assertEquals(-1, encoded.getNextCharacter());
    }

    private char[] generateTestInput(int length) {
        char[] testInput = new char[length];
        
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            testInput[i] = (char) r.nextInt(Character.MAX_CODE_POINT);
        }
        return testInput;
    }
}
