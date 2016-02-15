
package badcompression.io;

/**
 * Interface for reading single character at a time from an encoded file.
 * @author antti
 */
public interface EncodedFile {
    
    /**
     * Returns next character of encoded file.
     * @return
     */
    int getNextCharacter();
    byte[] getBytes();
    long getAmountOfBytes();
    void resetReader();
    long[] getFreq();
       
}