package badcompression.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Reads single bits from InputStream.
 * @author antti
 */
public class BitReader {
    
    private byte buffer;
    private int bits_read;
    
    private InputStream in;
    
    /**
     * Creates a new ReadBit object for single bit reading form InputStream
     * @param in InputStream to be read from.
     */
    public BitReader(InputStream in) {
        //so that readBit() reads first byte to buffer
        bits_read = 8;
        this.in = in;
    }
    
    /**
     * Returns next bit from InputStream. If return value is -1 end of file as been reached.
     * @return bit
     * @throws IOException
     */
    public int readBit() throws IOException {
        if (bits_read == 8) { 
            int read = in.read();
            buffer = (byte) read;
            bits_read = 0;
            if (read == -1) return -1;
        }
        byte tmp = buffer;
        int n_th_bit = 7 -bits_read;
        int b = (tmp >> n_th_bit) & 1;
        bits_read++;
        return b;
    }
    
    /**
     * Closes reader.
     * @throws IOException
     */
    public void close() throws IOException {
        in.close();
    }
}