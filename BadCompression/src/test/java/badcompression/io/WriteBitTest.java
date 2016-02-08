package badcompression.io;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
public class WriteBitTest {

    private WriteBit write;
    private ByteArrayOutputStream outStream;
    private String[] testStringOutput;
    private byte[] testByteOutput;

    public WriteBitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        outStream = new ByteArrayOutputStream();
        write = new WriteBit(outStream);

        testStringOutput = new String[]{"00000000", "00000001", "00000010",
            "00000011","00101000"};
        testByteOutput = new byte[]{0x0, 0x1, 0x2, 0x3,0x28};
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IOException.class)
    public void nonBinaryStrings() throws IOException {
        write.write("asd");
    }

    @Test(expected = IOException.class)
    public void nonBinaryStrings2() throws IOException {
        write.write("");
    }

    @Test(expected = IOException.class)
    public void nonBinaryStrings3() throws IOException {
        write.write(" ");
    }

    @Test
    public void writeString() throws IOException {
        for (String s : testStringOutput) {
            write.write(s);
        }
        assertEquals(testStringOutput.length, write.getBytesWriten());
        write.close();
        testOutputIsExpected();
    }

    @Test
    public void writeBitByBit() throws IOException {
        for (String s : testStringOutput) {
            for (char c : s.toCharArray()) {
                switch (c) {
                    case '0':
                        write.write(false);
                        break;
                    case '1':
                        write.write(true);
                        break;
                    default:
                        throw new IOException();
                }
            }
        }
        write.close();
        testOutputIsExpected();
    }
    
    @Test 
    public void writeNonByte() throws IOException {
        write.write(true);
        write.close();
        assertEquals(1, outStream.toByteArray().length);
        assertEquals(outStream.toByteArray()[0] & 0xFF,128);
    }
    
    @Test
    public void writeNonByteString() throws IOException {
        write.write("0101010");
        write.close();
        //0101010 should be after close 01010100 (0x54)
        assertEquals(0x54, outStream.toByteArray()[0] & 0xFF);
        assertEquals(1, outStream.toByteArray().length);
    }

    public void testOutputIsExpected() {
        byte[] byteArray = outStream.toByteArray();
        for (int i = 0; i < byteArray.length; i++) {
            assertEquals(testByteOutput[i], byteArray[i] & 0xFF);
        }
    }

    @Test
    public void writeNothing() throws IOException {
        write.close();
        assertEquals(0, outStream.toByteArray().length);
    }
}