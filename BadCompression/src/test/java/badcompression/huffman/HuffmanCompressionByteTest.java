/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import badcompression.compression.Compressor;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
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
public class HuffmanCompressionByteTest {
    
    public HuffmanCompressionByteTest() {
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
    public void wikipediaCompressUncompressTest() throws Exception {
        File encodeTO = File.createTempFile("temp", "txt");
        File decodeTo = File.createTempFile("temp2", "txt");
        Compressor comp = new HuffmanCompressionByte();
        URL lol = Thread.currentThread().getContextClassLoader().getResource("Wikipedia_Arbitration_Committee_Elections_December_2013.txt");
        comp.compress(lol, encodeTO.toURL());
        comp.uncompress(encodeTO.toURI(), decodeTo.toURI());
        filesAreIdentical(decodeTo.toURL(), lol);
    }

    private void filesAreIdentical(URL file1, URL file2) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] file1Bytes = Files.readAllBytes(Paths.get(file1.toURI()));
        byte[] file2Bytes = Files.readAllBytes(Paths.get(file2.toURI()));
        byte[] file1Hash = md.digest(file1Bytes);
        byte[] file2Hash = md.digest(file2Bytes);
        assertArrayEquals(file1Hash, file2Hash);     
    }
}
