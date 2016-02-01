/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

import java.io.IOException;
import java.io.OutputStream;



/**
 * Writes single bits to OutputStream.
 * @author antti
 */
public class WriteBit {
    
    private byte buff;
    private int bits_in_buff;
    private OutputStream out;
    private long bytesWriten;
    
    /**
     * 
     * @param out
     */
    public WriteBit(OutputStream out) {
        bits_in_buff = 0;
        this.out = out;
        bytesWriten = 0;
    }
    
    /**
     * Writes a single bit to the byte buffer and eventually to the outputstream.
     * @param bit
     * @throws IOException
     */
    public void write(boolean bit) throws IOException {
        buff = (byte) (buff << 1);
        if (bit) buff++;
        bits_in_buff++;
        if (bits_in_buff == 8) {
            bits_in_buff = 0;
            out.write(buff);
            buff = 0;
            bytesWriten++;
        }
    }
    
    /**
     * Write
     * @param s String representing binary queue.
     * @throws IOException
     */
    public void write(String s) throws IOException {
        if (s.isEmpty()) throw new IOException();
        for (char c : s.toCharArray()) {
            if (c != '0' && c != '1') throw new IOException();
            if (c == '0') {
                write(false);
            } else {
                write(true);
            }
        }
    }
    
    /**
     * Closes writer.
     * @throws IOException
     */
    public void close() throws IOException {
        flush();
        out.close();
    }
    
    /**
     * Flushes writer.
     * @throws IOException
     */
    public void flush() throws IOException {
        if (bits_in_buff == 0) return;
        bytesWriten++;
        int remaining_bits = 8 - bits_in_buff;
        buff = (byte) (buff << remaining_bits);
        out.write(buff);
        out.flush();
        bits_in_buff = 0;
        buff = 0;
    }
    
    /**
     * 
     * @return bytes writen
     */
    public long getBytesWriten() {
        return bytesWriten;
    }
    
}
