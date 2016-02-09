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
    
    public UTF8EncodedFile(byte[] file) throws UnsupportedEncodingException {
        this.file = file;
        UTF8encoded = new String(file, "UTF-8");
        counter = 0;
    }

    @Override
    public int getNextCharacter() {
        if (counter >= UTF8encoded.length()) return -1;
        return UTF8encoded.charAt(counter++);
    }

    @Override
    public byte[] getBytes() {
        return file;
    }

    @Override
    public long getAmountOfBytes() {
        return file.length;
    }

    @Override
    public void resetReader() {
        counter = 0;
    }
    
}
