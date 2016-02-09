/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

/**
 * Reads byte by byte any file.
 * @author antti
 */
public class ByteEncodedFile implements EncodedFile {

    private byte[] file;
    private int counter;
    
    public ByteEncodedFile(byte[] file) {
        this.file = file;
        counter = 0;
    }
    @Override
    public int getNextCharacter() {
        if (counter >= file.length) return -1;
        return file[counter++] & 0xFF;
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