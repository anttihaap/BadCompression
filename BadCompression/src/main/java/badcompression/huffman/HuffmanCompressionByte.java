package badcompression.huffman;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.io.ByteEncodedFile;
import badcompression.io.FrequencyIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import badcompression.io.EncodedFile;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

/**
 * Compresses and decompresses any file using Huffman coding.
 *
 * @author antti
 */
public class HuffmanCompressionByte extends HuffmanCompression implements Compressor {

    /**
     * Compresses souceFile using Huffmancoding to outputFile URI location.
     *
     * @param sourceFile
     * @param outputFile
     * @return
     * @throws Exception
     */
    @Override
    public CompressionResults compress(URI sourceFile, URI outputFile) {
        EncodedFile file = null;
        try {
            file = new ByteEncodedFile(Files.readAllBytes(Paths.get(sourceFile)));
        } catch (IOException ex) {
            new CompressionResults("Input file.");
        }
        HuffmanCoding huff_coding = new HuffmanCoding(getFreq(file.getBytes()), true);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(outputFile));
        } catch (FileNotFoundException ex) {
            return new CompressionResults("Output file.");
        }

        FrequencyIO.writeFreq(out, huff_coding);
        byte[] output;
        try {
            output = encode(file, huff_coding);
        } catch (IOException ex) {
            return new CompressionResults("Encoding failure.");
        }
        try {
            out.write(output);
            out.flush();
            out.close();
        } catch (IOException e) {
            return new CompressionResults("Writing failure.");
        }
        //TODO: output for results.
        return new CompressionResults(0, 0);
    }

    /**
     * Uncompresses file's compressed to target URI.
     *
     * @param sourceURI
     * @param target
     * @return
     * @throws Exception
     */
    @Override
    public CompressionResults uncompress(URI sourceURI, URI target) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(sourceURI));
        } catch (FileNotFoundException ex) {
            new CompressionResults("File not found.");
        }
        long[] freq = null;
        try {
            freq = FrequencyIO.readFrequencies(in, 256);
        } catch (IOException ex) {
            new CompressionResults("Uncompression writing failure.");
        }
        HuffmanCoding huff_coding = new HuffmanCoding(freq, false);
        HuffmanTreeNode root = huff_coding.getHuffTree();
        byte[] decoded = null;
        try {
            decoded = decode(root, in);
        } catch (IOException ex) {
            new CompressionResults("Decode failure.");
        }
        try {
            FileOutputStream fileWriter = new FileOutputStream(new File(target));
            fileWriter.write(decoded);
        } catch (IOException e) {
            new CompressionResults("Write failure.");
        }
        //TODO: output for results.
        return new CompressionResults(0, 0);
    }

    /**
     * Returns frequency of single bytes.
     *
     * @param bytes Array of bytes
     * @return Frequency of bytes.
     */
    public static long[] getFreq(byte[] bytes) {
        long[] freq = new long[256];
        for (byte b : bytes) {
            freq[b & 0xFF]++;
        }
        return freq;
    }
}
