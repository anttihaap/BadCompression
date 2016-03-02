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
public class CompressorTestsUtil {
    
    private static final String testFileDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    
    public CompressorTestsUtil() {
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
        File directory = new File(testFileDir + "utf8testfiles/");
        for (File file  : directory.listFiles()) {
            if (file.isFile()) {
                compressUncompressTest(comp, file);
            }
        }
    }

    public static void filesAreIdentical(URI file1, URI file2) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] file1Bytes = Files.readAllBytes(Paths.get(file1));
        byte[] file2Bytes = Files.readAllBytes(Paths.get(file2));
        byte[] file1Hash = md.digest(file1Bytes);
        byte[] file2Hash = md.digest(file2Bytes);
        assertArrayEquals(file1Hash, file2Hash);     
    }
    
    public static void compressUncompressTest(Compressor comp, File testFile) throws Exception {
        File encodeTo = File.createTempFile("temp", ".txt");
        File decodeTo = File.createTempFile("temp2", ".txt");     
        comp.compress(testFile, encodeTo);
        comp.uncompress(encodeTo, decodeTo);
        System.out.println("\nFILE: " + testFile.toPath());
        System.out.println("original length: " + testFile.length() + " compressed length: " + encodeTo.length());
        filesAreIdentical(decodeTo.toURI(), testFile.toURI());
    }
    
    public static void compressUncompressTest(Compressor comp, String pathInTestFolder) throws Exception {
        compressUncompressTest(comp, new File(testFileDir + pathInTestFolder));
    }
}
