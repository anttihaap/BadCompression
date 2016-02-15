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
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Compresses UTF-8 encoded files.
 *
 * @author antti
 */
public class HuffmanCompressionUTF8 extends HuffmanCompression implements Compressor {

    @Override
    public CompressionResults compress(URI original, URI target) {
        EncodedFile file = null;
        try {
            file = new UTF8EncodedFile(Files.readAllBytes(Paths.get(original)));
        } catch (IOException ex) {
            new CompressionResults("Input file.");
        }
        HuffmanCoding huff_coding = new HuffmanCoding(file.getFreq(), true);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(target));
        } catch (FileNotFoundException ex) {
            return new CompressionResults("Output file.");
        }

        try {
            FrequencyIO.writeFreq(out, huff_coding);
        } catch (IOException ex) {
            return new CompressionResults("Frequency writing failure.");
        }
        try {
            encode(huff_coding, file, out);
        } catch (IOException ex) {
            Logger.getLogger(HuffmanCompressionUTF8.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(HuffmanCompressionUTF8.class.getName()).log(Level.SEVERE, null, ex);
        }

        //TODO: output for results.
        return new CompressionResults(0, 0);
    }

    @Override
    public CompressionResults uncompress(URI source, URI target) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(source));
        } catch (FileNotFoundException ex) {
            new CompressionResults("File not found.");
        }
        long[] freq = null;
        try {
            freq = FrequencyIO.readFrequencies(in, Character.MAX_CODE_POINT);
        } catch (IOException ex) {
            new CompressionResults("Uncompression writing failure.");
        }
        HuffmanCoding huff_coding = new HuffmanCoding(freq, false);
        HuffmanTreeNode root = huff_coding.getHuffTree();
        
        UTF8Writer out = null;
        try {
            out = new UTF8Writer(new File(target));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuffmanCompressionUTF8.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            decode(root, in, out);
        } catch (IOException ex) {
            new CompressionResults("Decode failure.");
        }

        //TODO: output for results.
        return new CompressionResults(0, 0);
    }
}