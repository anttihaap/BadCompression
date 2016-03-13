package badcompression.compression.util;

/**
 * Own implementation of array copying.
 * @author antti
 */
public class ArrayCopy {
    
    /**
     * Copies and creates a new array from an excisting array. 
     * @param <E> Element type.
     * @param arr Array to copy.
     * @param length Length of new array.
     * @return Copied array.
     */
    public static <E> E[] copyComparableArray(E[] arr, int length) {
        E[] copy =  (E[])new Comparable[length];
        for (int i = 0; i < Math.min(length,arr.length); i++) {
            copy[i] = arr[i];
        }
        return copy;
    }
    
}
