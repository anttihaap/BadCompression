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

    @Override
    public CompressionResults compress(File original, File target) throws FileNotFoundException, IOException {
        EncodedFile file = new UTF8EncodedFile(Files.readAllBytes(original.toPath()));
        HuffmanCoding huff_coding = new HuffmanCoding(file.getFreq(), true);
        FileOutputStream out = out = new FileOutputStream(target);
        FrequencyIO.writeFreq(out, huff_coding);
        encode(huff_coding, file, out);
        out.flush();
        out.close();

        //TODO: output for results.
        return new CompressionResults(0, 0);
    }

    @Override
    public CompressionResults uncompress(File source, File target) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(source);
        long[] freq = FrequencyIO.readFrequencies(in, Character.MAX_CODE_POINT);

        HuffmanCoding huff_coding = new HuffmanCoding(freq, false);
        HuffmanTreeNode root = huff_coding.getHuffTree();

        UTF8Writer out = new UTF8Writer(target);
        decode(root, in, out);

        //TODO: output for results.
        return new CompressionResults(0, 0);
    }
}
