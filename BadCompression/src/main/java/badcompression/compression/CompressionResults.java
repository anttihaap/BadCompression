/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.compression;

public class CompressionResults {
    
    private boolean failure;
    private String failureReason;
    private long originalBytes;
    private long compressedBytes;
    
    /**
     *
     * @param failureReason
     */
    public CompressionResults(String failureReason) {
        this.failure = true;
        this.failureReason = failureReason;
    }

    public String getFailureReason() {
        return failureReason;
    }

    /**
     *
     * @param failureReason
     */
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
    
    /**
     *
     * @param original
     * @param compressed
     */
    public CompressionResults(long original, long compressed) {
        this.failure = false;
        this.originalBytes = originalBytes;
        this.compressedBytes = compressedBytes;
    }

    /**
     *
     * @return
     */
    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }

    public long getOriginalBytes() {
        return originalBytes;
    }

    /**
     *
     * @param originalBytes
     */
    public void setOriginalBytes(long originalBytes) {
        this.originalBytes = originalBytes;
    }

    public long getCompressedBytes() {
        return compressedBytes;
    }

    /**
     *
     * @param compressedBytes
     */
    public void setCompressedBytes(long compressedBytes) {
        this.compressedBytes = compressedBytes;
    }
}
