package badcompression.huffman;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.io.EncodedFile;
import badcompression.io.FrequencyIO;
import badcompression.io.UTF8EncodedFile;
import badcompression.io.UTF8Writer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Compresses UTF-8 encoded files.
 *
 * @author antti
 */
public class HuffmanCompressionUTF8 extends HuffmanCompression implements Compressor {

    /**
     * Compress file using UTF-8 recognizing Huffman coding.
     * @param original File for compression.
     * @param target Target file for compressed file.
     * @return Results. (TODO)
     * @throws FileNotFoundException File not found.
     * @throws IOException File writing/reading exception.
     */
    @Override
    public CompressionResults compress(File original, File target) throws FileNotFoundException, IOException {
        //Read files and create a huffmancoding from file.getFreq()
        EncodedFile file = new UTF8EncodedFile(Files.readAllBytes(original.toPath()));
        HuffmanCoding huff_coding = new HuffmanCoding(file.getFreq(), true);
        
        FileOutputStream out = out = new FileOutputStream(target);
        
        //Write frequencies to file:
        FrequencyIO.writeFreq(out, huff_coding);
        
        //Encode file with huffman coding:
        encode(huff_coding, file, out);
        
        out.flush();
        out.close();

        //TODO: output for results.
        return new CompressionResults(0, 0);
    }

    /**
     * Uncompress file using UTF-8 recognizing Huffman coding.
     * @param source File for uncompression.
     * @param target Target file for decofing.
     * @return Results (TODO).
     * @throws FileNotFoundException File not found exception.
     * @throws IOException File read/write exception.
     */
    @Override
    public CompressionResults uncompress(File source, File target) throws FileNotFoundException, IOException {
        //Read frequencies from file:
        FileInputStream in = new FileInputStream(source);
        long[] freq = FrequencyIO.readFrequencies(in, Character.MAX_CODE_POINT);

        //Create huffman coding from frequencies
        HuffmanCoding huff_coding = new HuffmanCoding(freq, false);
        HuffmanTreeNode root = huff_coding.getHuffTree();

        //UTF8 writer for target dir:
        UTF8Writer out = new UTF8Writer(target);
        
        decode(root, in, out);

        //TODO: output for results.
        return new CompressionResults(0, 0);
    }
}
