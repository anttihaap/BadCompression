/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.io.ByteEncodedFile;
import badcompression.io.HuffmanCodingIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import badcompression.io.EncodedFile;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Compresses any file using Huffman coding.
 * @author antti
 */
public class HuffmanCompressionByte extends HuffmanCompression implements Compressor {

    @Override
    public CompressionResults compress(URL sourceFile, URL outputFile) throws Exception {
        EncodedFile file = null;
        file = new ByteEncodedFile(Files.readAllBytes(Paths.get(sourceFile.toURI())));
        HuffmanCoding huff_coding = new HuffmanCoding(getFreq(file.getBytes()), true);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(outputFile.toURI()));
        } catch (FileNotFoundException ex) {
            return new CompressionResults("Output file.");
        }

        HuffmanCodingIO.writeFreq(out, huff_coding);
        byte[] output = encode(file, huff_coding);
        out.write(output);
        out.flush();
        out.close();
        return null;
    }

    public CompressionResults uncompress(URI sourceURI, URI target) throws IOException {
        InputStream in = new FileInputStream(new File(sourceURI));
        long[] freq = HuffmanCodingIO.readFrequencies(in, 256);
        HuffmanCoding huff_coding = new HuffmanCoding(freq, false);
        HuffmanTreeNode root = huff_coding.getHuffTree();
        byte[] decoded = decode(root, in);
        FileOutputStream fileWriter = new FileOutputStream(new File(target));
        fileWriter.write(decoded);
        return null;
    }

    public static long[] getFreq(byte[] bytes) {
        long[] freq = new long[256];
        for (byte b : bytes) {
            freq[b & 0xFF]++;
        }
        return freq;
    }
}