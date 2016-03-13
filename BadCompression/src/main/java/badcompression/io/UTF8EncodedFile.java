/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

import java.io.UnsupportedEncodingException;

/**
 * Returns one character at a time from a utf-8 encoded file.
 * @author antti
 */
public class UTF8EncodedFile implements EncodedFile {
    
    private byte[] file;
    private String UTF8encoded;
    private int counter;
    private long[] freq;
    
    /**
     * Processes UTF-8 encoded file for reading.
     * @param file File in bytes.
     * @throws UnsupportedEncodingException Thrown when file is not UTF-8 encoded.
     */
    public UTF8EncodedFile(byte[] file) throws UnsupportedEncodingException {
        this.file = file;
        UTF8encoded = new String(this.file, "UTF-8");
        counter = 0;
        freq = new long[Character.MAX_VALUE+1];
        calculateFreq();
    }
    
    private void calculateFreq() {
        for (int i = 0; i < UTF8encoded.length(); i++) {
            freq[UTF8encoded.charAt(i)]++;
        }
    }

    @Override
    public int getNextCharacter() {
        if (counter >= UTF8encoded.length()) return -1;
        return UTF8encoded.charAt(counter++);
    }

    /**
     * Returns original bytes of file.
     * @return
     */
    @Override
    public byte[] getBytes() {
        return file;
    }

    /**
     * Returns amount of bytes in file.
     * @return
     */
    @Override
    public long getAmountOfBytes() {
        return file.length;
    }

    /**
     * Resets reader.
     */
    @Override
    public void resetReader() {
        counter = 0;
    }

    /**
     * Returns frequencies of characters in file.
     * @return
     */
    @Override
    public long[] getFreq() {
        return freq;
    }
    
}
