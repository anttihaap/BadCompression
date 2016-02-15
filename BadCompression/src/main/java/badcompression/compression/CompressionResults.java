package badcompression.compression;

/**
 * Contains result information about compression.
 * @author antti
 */
public class CompressionResults {
    
    private boolean failure;
    private String failureReason;
    private long originalBytes;
    private long compressedBytes;
    
    /**
     * Constructor for failed compression. 
     * @param failureReason Reason.
     */
    public CompressionResults(String failureReason) {
        this.failure = true;
        this.failureReason = failureReason;
    }
    
    /**
     * Constructor for successful compression.
     * @param original Bytes in original file.
     * @param compressed Bytes in compressed file.
     */
    public CompressionResults(long original, long compressed) {
        this.failure = false;
        this.originalBytes = original;
        this.compressedBytes = compressed;
    }  

    /**
     * Returns true if compression failed.
     * @return
     */
    public boolean isFailure() {
        return failure;
    }

    public long getOriginalBytes() {
        return originalBytes;
    }

    public long getCompressedBytes() {
        return compressedBytes;
    }
    
    public String getFailureReason() {
        return failureReason;
    }
}
