package badcompression.huffman;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.io.ByteEncodedFile;
import badcompression.io.FrequencyIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import badcompression.io.EncodedFile;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Compresses and decompresses any file using Huffman coding.
 *
 * @author antti
 */
public class HuffmanCompressionByte extends HuffmanCompression implements Compressor {

    /**
     * Compresses souceFile using Huffmancoding to outputFile URI location.
     *
     * @param source File for compression.
     * @param target Target file for compressed file.
     * @return Compression results. (TODO)
     * @throws java.io.IOException File reading exception.
     */
    @Override
    public CompressionResults compress(File source, File target) throws IOException {
        //Read file and frequencies:
        EncodedFile file = new ByteEncodedFile(Files.readAllBytes(source.toPath()));
        HuffmanCoding huff_coding = new HuffmanCoding(file.getFreq(), true);
        
        //Write frequencies to file
        FileOutputStream out = new FileOutputStream(target);
        FrequencyIO.writeFreq(out, huff_coding);
        
        //Write compressed file with huffman coding:
        encode(huff_coding, file, out);
        
        return new CompressionResults(0, 0);
    }

    /**
     * Uncompresses file's compressed to target URI.
     *
     * @param source File for uncompression.
     * @param target File to decode to.
     * @return Results (TODO)
     * @throws java.io.FileNotFoundException
     */
    @Override
    public CompressionResults uncompress(File source, File target) throws FileNotFoundException, IOException {
        //Read freq from file:
        FileInputStream in = new FileInputStream(source);
        long[] freq = FrequencyIO.readFrequencies(in, 256);
        
        //Create huffman coding from frequencies
        HuffmanCoding huff_coding = new HuffmanCoding(freq, false);
        HuffmanTreeNode root = huff_coding.getHuffTree();
        
        //Decode file with huffman coding:
        OutputStream out = new FileOutputStream(target);
        decode(root, in, out);
        
        out.flush();
        out.close();
        //TODO: output for results.
        return new CompressionResults(source.length(), target.length());
    }
}
