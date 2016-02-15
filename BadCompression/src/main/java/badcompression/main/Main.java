/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.main;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.huffman.HuffmanCompressionByte;
import badcompression.huffman.HuffmanCompressionUTF8;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antti
 */
public class Main {
    
    public static void main(String[] args) throws Exception {         
//                 
//        String fileForCompression = "";
//        String compressTo = "";
//        String uncompressTo = "";
//
//        Compressor comp = new HuffmanCompressionByte(); //or new HuffmanCompressionUTF-8();
//        CompressionResults results = comp.compress(new URI("file://" + fileForCompression), new URI("file://" + compressTo));
//        if (results.isFailure()) {
//            System.out.println("FILURE:");
//            System.out.println(results.getFailureReason());
//        } else {
//            System.out.println("Compression successful.");
//        }
//
//        
//        results = comp.uncompress(new URI("file://" + compressTo), new URI("file://" + uncompressTo));
//        if (results.isFailure()) {
//            System.out.println("FAILURE:");
//            System.out.println(results.getFailureReason());
//        } else {
//            System.out.println("Uncompress successful.");
//        }
    }

}