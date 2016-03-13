/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.performanceTesting;

import badcompression.datastructures.MinPriorityQueue;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Own priorityqueue vs. java's
 * @author antti
 */
public class HeapTesting {

    public static void main(String[] args) {
        int times = 10;
        
        System.out.println("Results for testing java's PriorityQue and own PriorityQue implementation:");
        for (int i = 0; i <= 10; i++) {
            int[] randomArray = randomArray((int) Math.pow(10,i));
            long[] javaResults = new long[times];
            for (int j = 0; j < times; j++) {
                javaResults[i] = calculateRunTimeJava(randomArray);
            }
            long javaResult = avg(javaResults);
            
            long[] ownResults = new long[times];
            for (int j = 0; j < times; j++) {
                ownResults[i] = calculateRunTimeOwn(randomArray);
            }
            long ownResult = avg(ownResults);
            
            System.out.println("n: 10^" + i + " java: " + javaResult + "ms - own: " + ownResult + "ms.");
        }
    }
    
    private static long avg(long[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum/array.length;
    }
    
    private static int[] randomArray(int n) {
        int[] array = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = r.nextInt();
        }
        return array;
   
    }

    
    private static long calculateRunTimeJava(int[] array) {
        PriorityQueue<Integer> pq_java = new PriorityQueue<>();
        long startTimeMS = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            pq_java.add(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            pq_java.remove();
        }
        return System.currentTimeMillis()-startTimeMS;
        
    }
    
    private static long calculateRunTimeOwn(int[] array) {
        MinPriorityQueue<Integer> pq = new MinPriorityQueue<>();
        long startTimeMS = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            pq.add(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            pq.remove();
        }
        return System.currentTimeMillis()-startTimeMS;
    }
     
}