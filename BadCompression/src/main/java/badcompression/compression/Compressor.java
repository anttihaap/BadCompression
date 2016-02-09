package badcompression.compression;

import java.net.URI;

/**
 * Interface for any compressor.
 * @author antti
 */
public interface Compressor {
    
    CompressionResults compress(URI original, URI target);
    CompressionResults uncompress(URI source, URI target);
    
    
}
