/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.compression;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Interface for any compressor.
 * @author antti
 */
public interface Compressor {
    
    CompressionResults compress(URL original, URL target) throws Exception;
    CompressionResults uncompress(URI source, URI target) throws IOException;
    
    
}
