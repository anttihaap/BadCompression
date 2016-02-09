/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.main;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.huffman.HuffmanCompressionByte;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antti
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        String fileForCompression = "/home/antti/test.txt";
        String compressTo = "/home/antti/test.badCompress";
        String uncompressTo = "/home/antti/test2.txt";

        Compressor comp = new HuffmanCompressionByte();
        CompressionResults results = comp.compress(new URI("file://" + fileForCompression), new URI("file://" + compressTo));
        if (results.isFailure()) {
            System.out.println("FILURE:");
            System.out.println(results.getFailureReason());
        } else {
            System.out.println("Compression successful.");
        }
        //m.out.println(results.getOriginalBytes() + " - " + results.getCompressedBytes());
        
        results = comp.uncompress(new URI("file://" + compressTo), new URI("file://" + uncompressTo));
        if (results.isFailure()) {
            System.out.println("FAILURE:");
            System.out.println(results.getFailureReason());
        } else {
            System.out.println("Uncompress successful.");
        }
    }
}
