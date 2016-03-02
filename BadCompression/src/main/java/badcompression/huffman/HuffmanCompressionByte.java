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
     * @param source
     * @param target
     * @return
     * @throws java.io.IOException
     * @throws Exception
     */
    @Override
    public CompressionResults compress(File source, File target) throws IOException {
        EncodedFile file = new ByteEncodedFile(Files.readAllBytes(source.toPath()));
        HuffmanCoding huff_coding = new HuffmanCoding(file.getFreq(), true);
        FileOutputStream out = new FileOutputStream(target);
        FrequencyIO.writeFreq(out, huff_coding);
        encode(huff_coding, file, out);
        return new CompressionResults(0, 0);
    }

    /**
     * Uncompresses file's compressed to target URI.
     *
     * @param source
     * @param target
     * @return
     * @throws java.io.FileNotFoundException
     * @throws Exception
     */
    @Override
    public CompressionResults uncompress(File source, File target) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(source);
        long[] freq = FrequencyIO.readFrequencies(in, 256);
        HuffmanCoding huff_coding = new HuffmanCoding(freq, false);
        HuffmanTreeNode root = huff_coding.getHuffTree();
        OutputStream out = new FileOutputStream(target);
        decode(root, in, out);
        out.flush();
        out.close();
        //TODO: output for results.
        return new CompressionResults(source.length(), target.length());
    }
}
