/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.main;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.huffman.HuffmanCompressionByte;
import java.net.URI;


/**
 *
 * @author antti
 */
public class Main {
    
    public static void main(String[] args) throws Exception {     
    
        String fileForCompression = "/home/antti/random3.dat";
        String compressTo = "/home/antti/random3.dat.badComp";
        String uncompressTo = "/home/antti/random3_2.dat";

        Compressor comp = new HuffmanCompressionByte(); //or new HuffmanCompressionUTF8();
        CompressionResults results = comp.compress(new URI("file://" + fileForCompression), new URI("file://" + compressTo));
        if (results.isFailure()) {
            System.out.println("FILURE:");
            System.out.println(results.getFailureReason());
        } else {
            System.out.println("Compression successful.");
        }

        
        results = comp.uncompress(new URI("file://" + compressTo), new URI("file://" + uncompressTo));
        if (results.isFailure()) {
            System.out.println("FAILURE:");
            System.out.println(results.getFailureReason());
        } else {
            System.out.println("Uncompress successful.");
        }
    }
    
    
    

}