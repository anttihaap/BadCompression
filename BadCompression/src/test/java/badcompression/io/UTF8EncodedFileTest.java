/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

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
   

    private char[] generateTestInput(int length) {
        char[] testInput = new char[length];
        
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            testInput[i] = (char) r.nextInt(Character.MAX_CODE_POINT);
        }
        return testInput;
    }
}
