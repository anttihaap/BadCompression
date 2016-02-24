/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import badcompression.compression.Compressor;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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
public class CompressorTests {
    
    public CompressorTests() {
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
    
    public static void testUTF8Files(Compressor comp) throws Exception {
        compressUncompressFromResourceTest(comp,"utf8testfiles/wikipedia_chinese_title:中华人民共和国.txt");
        compressUncompressFromResourceTest(comp,"utf8testfiles/wikipedia_english_title:Template_talk:Euro_convergence_criteria.txt");
        compressUncompressFromResourceTest(comp,"utf8testfiles/wikipedia_english_title:Wikipedia_talk:Arbitration_Committee_Elections_December_2013.txt");
        compressUncompressFromResourceTest(comp,"utf8testfiles/wikipedia_japanese_title:_日本語.txt");
    }

    public static void filesAreIdentical(URI file1, URI file2) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] file1Bytes = Files.readAllBytes(Paths.get(file1));
        byte[] file2Bytes = Files.readAllBytes(Paths.get(file2));
        byte[] file1Hash = md.digest(file1Bytes);
        byte[] file2Hash = md.digest(file2Bytes);
        assertArrayEquals(file1Hash, file2Hash);     
    }
    
    public static void compressUncompressTest(Compressor comp, String filePath) throws Exception {
        File encodeTo = File.createTempFile("temp", ".txt");
        File decodeTo = File.createTempFile("temp2", ".txt");
        URI original = new URI("file://" + filePath);
        comp.compress(original, encodeTo.toURI());
        comp.uncompress(encodeTo.toURI(), decodeTo.toURI());
        System.out.println("encoded and decoded files:");
        System.out.println(original.getPath() + " - " + decodeTo.getPath());
        filesAreIdentical(decodeTo.toURI(), original);
    }
    
     public static void compressUncompressFromResourceTest(Compressor comp, String filePath) throws Exception {
        URL sourceFile = Thread.currentThread().getContextClassLoader().getResource(filePath);
         compressUncompressTest(comp, sourceFile.getPath());
    }
}
