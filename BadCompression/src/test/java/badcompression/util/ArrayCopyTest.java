/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.util;

import badcompression.compression.util.ArrayCopy;
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
public class ArrayCopyTest {
    
    public ArrayCopyTest() {
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
    public void arrayCopyCopiesOnlyLengthAmount() {
        int length = 1000;
        Comparable[] randomArray = randomArray(10000);
        Comparable[] copyArray = ArrayCopy.copyComparableArray(randomArray, length);
        for (int i = 0; i < length; i++) {
            assertEquals(0, copyArray[i].compareTo(randomArray[i]));
        }
        assertEquals(length, copyArray.length);
    }
    
    @Test
    public void arrayCopyNullIfLengthGreater() {
        Comparable[] array = new Comparable[2];
        array[0] = 2;
        array[1] = 1000;
        Comparable[] copyArray = ArrayCopy.copyComparableArray(array, 100);
        assertEquals(0, array[0].compareTo(copyArray[0]));
        assertEquals(0, array[1].compareTo(copyArray[1]));
        for (int i = 2; i < 100; i++) {
            assertNull(copyArray[i]);
        }
    }
    
    @Test
    public void arraySameIfLengthSame() {
        Comparable[] array = randomArray(100);
        Comparable[] copyArray = ArrayCopy.copyComparableArray(array, 100);
        assertArrayEquals(array, copyArray);
    }
     


    public Integer[] randomArray(int length) {
        Integer[] arr = new Integer[length];
        
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt();
        }
        return arr;
    }
}
