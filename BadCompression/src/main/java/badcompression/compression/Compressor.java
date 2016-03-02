package badcompression.compression;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for any compressor.
 * @author antti
 */
public interface Compressor {
    
    CompressionResults compress(File original, File target) throws IOException;
    CompressionResults uncompress(File source, File target) throws FileNotFoundException, IOException;
    
    
}
