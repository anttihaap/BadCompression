/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.datastructures;

/**
 * MinPriority queue implemented using a Binary heap.
 * @author antti
 * @param <E>
 */
public class MinPriorityQueue <E extends Comparable<E>> {
    
    private MinBinaryHeap<E> heap;
    
    /**
     * Create a new priority queue.
     */
    public MinPriorityQueue() {
        heap = new MinBinaryHeap<>();
    }
    
    /**
     * Adds value to priority que.
     * @param value
     */
    public void add(E value) {
        heap.insert(value);
    }
    
    /**
     * Returns the smallest value.
     * @return
     */
    public E peek() {
        return heap.peekMin();
    }
    
    /**
     * Returns and removes the smalles value.
     * @return
     */
    public E remove() {
        E value = heap.removeMin();
        return value;
    }
    
    /**
     * Returns the amount of elements in queue.
     * @return
     */
    public int size() {
        return heap.size();
    }   
}