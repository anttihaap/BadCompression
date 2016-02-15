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
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        HuffmanCoding huff_coding = new HuffmanCoding(file.getFreq(), true);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(outputFile));
        } catch (FileNotFoundException ex) {
            return new CompressionResults("Output file.");
        }

        try {
            FrequencyIO.writeFreq(out, huff_coding);
        } catch (IOException ex) {
            return new CompressionResults("Frequency writing failure.");
        }
        try {
            //        byte[] output;
            encode(huff_coding, file, out);
        } catch (IOException ex) {
            Logger.getLogger(HuffmanCompressionByte.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//            out.write(output);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            return new CompressionResults("Writing failure.");
//        }
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
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(target));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuffmanCompressionByte.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            decode(root, in, out);
        } catch (IOException ex) {
            new CompressionResults("Decode failure.");
        }
        try {
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(HuffmanCompressionByte.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(HuffmanCompressionByte.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TODO: output for results.
        return new CompressionResults(0, 0);
    }
}