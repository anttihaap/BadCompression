/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Writes java characters in UTF-8 format to file.
 * @author antti
 */
public class UTF8Writer extends OutputStream {
    
    private OutputStream stream;
    
    /**
     * Creates writer for writing Java charaters to file in UTF-8.
     * @param file
     * @throws FileNotFoundException
     */
    public UTF8Writer(File file) throws FileNotFoundException {
        stream = new FileOutputStream(file);
    }

    @Override
    public void write(int b) throws IOException {
        char c = (char) b;
        byte[] bArr = ("" + c).getBytes("UTF-8");
        stream.write(bArr);
    }
    
    @Override
    public void flush() throws IOException {
        stream.flush();
    }
    
    @Override
    public void close() throws IOException {
        stream.close();
    }
    
}
