/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

import badcompression.huffman.CompressorTestsUtil;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
public class UTF8WriterTest {
    
    private final String testOutput1 = "a0s80=aÅäsdf=APSAUHd997*ä^^âsdÅÅÅÄÄÄAAAsdå";
    private final String testOutput2 = "地球係太陽系嗰一隻行星，搦太陽嗰遠近來話嗰話，佢排第三。搦大細來話嗰話，就排第五。佢係今日已知唯一有生物嗰星球。地球到四萬五千七百萬年前形成。地球有隻天然衞星月球，佢到四萬五千三百三十三萬年前開始繞到地球轉。地球哈係人類居住嗰所在。";
    
    public UTF8WriterTest() {
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
    public void testOutput1() throws Exception {
        File file = writeStrintToFile(testOutput1);
        URL expected = Thread.currentThread().getContextClassLoader().getResource("utf8writertest/testOutput1Expected.txt");
        CompressorTestsUtil.filesAreIdentical(file.toURI(), expected.toURI());
    }
    
    @Test
    public void testOutput2() throws Exception {
        File file = writeStrintToFile(testOutput2);
        URL expected = Thread.currentThread().getContextClassLoader().getResource("utf8writertest/testOutput2Expected.txt");
        CompressorTestsUtil.filesAreIdentical(file.toURI(), expected.toURI());
    }
    
    private File writeStrintToFile(String s) throws IOException {
        File tmpFile = File.createTempFile("temp", ".txt");
        System.out.println(tmpFile.getPath());
        UTF8Writer writer = new UTF8Writer(tmpFile);
        for (char c : s.toCharArray()) {
            writer.write(c);
        }
        
        //To simulate texteditor, which writes LN end of every file.
        writer.write(0x0a);
        
        writer.flush();
        writer.close();
        return tmpFile;
    }

    
}
