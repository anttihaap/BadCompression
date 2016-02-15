/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import badcompression.io.ReadBit;
import badcompression.io.WriteBit;
import java.io.IOException;
import java.io.InputStream;
import badcompression.io.EncodedFile;
import java.io.OutputStream;

/**
 * Compression using Huffman coding.
 * @author antti
 */
public class HuffmanCompression {
    
    /**
     * Decodes compressed files, that where compressed with Huffman coding.
     * @param huffTree HuffCoding for characters.
     * @param in Input for decoder
     * @param out Output for decoded file.
     * @throws IOException
     */
    public static void decode(HuffmanTreeNode huffTree, InputStream in, OutputStream out) throws IOException {
        HuffmanTreeNode curr = huffTree;
        ReadBit r = new ReadBit(in);
        while (true) {
            int bitValue = r.readBit();
            if (bitValue == -1) {
                //EOF should be read, before outputstream reaches end of file.
                throw new IOException();
            }
            if (bitValue == 0) {
                curr = curr.leftNode;
            } else {
                curr = curr.rightNode;
            }
            if (curr.isLeafNode()) {
                if (curr.c == -1) {
                    break;
                }
                out.write(curr.c);     
                curr = huffTree;
            }
        }
    }
    
    /**
     * Encodes files with huffman coding.
     * @param input File input.
     * @param huffCoding HuffmanCoding used for encoding.
     * @throws IOException
     */
    public static void encode(HuffmanCoding huffCoding, EncodedFile input, OutputStream out) throws IOException {
        WriteBit write = new WriteBit(out);
        int in; 
        while((in = input.getNextCharacter()) != -1) {
            String binaryStr = huffCoding.getCharCode(in);
            write.write(binaryStr);
        }
        write.write(huffCoding.getEofCode());
        write.flush();
        write.close();
    }
}