/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template testFIle, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
public class ReadBitTest {

    private InputStream inStream;
    private ReadBit read;
    private final String[] testFileExpected = {"01000001", "01010011", "01000100", "00100000", "01001100", "01001111", "01001100", "00001010"};

    public ReadBitTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("testfile.txt");
        File testFIle = new File(url.getPath());
        inStream = new FileInputStream(testFIle);
        read = new ReadBit(inStream);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void readsExpectedBits() throws IOException {
        for (String string : testFileExpected) {
            for (char c : string.toCharArray()) {
                int r = read.readBit();
                if (c == '0') {
                    assertEquals(0, r);
                } else if (c == '1') {
                    assertEquals(1, r);
                } else {
                    throw new IOException();
                }
            }
        }
        assertEquals(read.readBit(), -1);
    }
}