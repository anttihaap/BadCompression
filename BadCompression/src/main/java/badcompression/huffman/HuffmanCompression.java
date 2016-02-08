/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import badcompression.io.ReadBit;
import badcompression.io.WriteBit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import badcompression.io.EncodedFile;

/**
 * Compression using Huffman coding.
 * @author antti
 */
public class HuffmanCompression {

    /**
     * Decodes compressed files, that where compressed with Huffman coding.
     * @param huffTree
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] decode(HuffmanTreeNode huffTree, InputStream in) throws IOException {
        HuffmanTreeNode curr = huffTree;
        ReadBit r = new ReadBit(in);
        ByteArrayOutputStream outbuff = new ByteArrayOutputStream();
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
                    System.out.println("the END");
                    break;
                }
                outbuff.write(curr.c);
                curr = huffTree;
            }
        }

        outbuff.flush();
        outbuff.close();
        return outbuff.toByteArray();
    }
    
    /**
     * Encodes files with huffman coding.
     * @param input
     * @param huffCoding
     * @return
     * @throws IOException
     */
    public static byte[] encode(EncodedFile input, HuffmanCoding huffCoding) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        WriteBit write = new WriteBit(output);
        int in; 
        while((in = input.getNextCharacter()) != -1) {
            String binaryStr = huffCoding.getCharCode(in);
            write.write(binaryStr);
        }
        write.write(huffCoding.getEofCode());
        write.flush();
        write.close();
        return output.toByteArray();
    }
}