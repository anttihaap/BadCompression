/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.io;

/**
 * Interface for reading characters from encoded file.
 * @author antti
 */
public interface EncodedFile {
    
    int getNextCharacter();
    byte[] getBytes();
    long getByesInFile();
    void resetReader();
       
}