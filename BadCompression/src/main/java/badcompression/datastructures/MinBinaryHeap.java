/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.datastructures;

import badcompression.compression.util.ArrayCopy;

/**
 * Implementation of MinBinaryHeap using arrays.
 *
 * @author antti
 * @param <E>
 */
public class MinBinaryHeap<E extends Comparable<E>> {

    private int currMaxIndex;
    private E[] array;

    /**
     * Creat a new MinBinaryHeap.
     */
    public MinBinaryHeap() {
        array = (E[]) (new Comparable[100]);
        currMaxIndex = -1;
    }

    /**
     * Returns the smalles element. 
     * @return smallest element.
     */
    public E peekMin() {
        if (currMaxIndex < 0) {
            return null;
        }
        return array[currMaxIndex];
    }

    /**
     * Returns the amount of elements in heap.
     * @return
     */
    public int size() {
        return currMaxIndex + 1;
    }

    /**
     * Removes the smalles element from the heap. 
     * @return smallest element.
     */
    public E removeMin() {
        //If nothing in heap, return null and dont modify size.
        if (currMaxIndex < 0) {
            return null;
        }
        E returnValue = array[0];
        array[0] = array[currMaxIndex];
        currMaxIndex--;   
        heapifyDown(0);
        return returnValue;
    }

    private void heapifyDown(int index) {
        //if there is no left child, node at array[index] is leaf.
        if (!hasLeftChild(index)) {
            return;
        }

        int smallestChildIndex = getLeftChildIndex(index);
        
        //find the smalles child: if right child excist compare it to the left
        if (hasRightChild(index) && array[smallestChildIndex].compareTo(array[getRightChildIndex(index)]) > 0) {
            smallestChildIndex = getRightChildIndex(index);
        }

        //if current is greater than smallest child, swap, otherwise stop.
        if (array[index].compareTo(array[smallestChildIndex]) > 0) {
            swap(index, smallestChildIndex);
            heapifyDown(smallestChildIndex);
        }
    }

    /**
     * Inserts an element to the heap.
     * @param value Element.
     */
    public void insert(E value) {
        int i = currMaxIndex + 1;
        currMaxIndex++;
        if (i > array.length - 1) {
            expandArray();
        }
        array[i] = value;
        heapifyUp(i);
    }

    private void heapifyUp(int index) {
        if (!hasParent(index)) {
            return;
        }
        int parentIndex = getParentIndex(index);
        //If parent is less than current (index) stop.
        if (array[parentIndex].compareTo(array[index]) < 0) {
            return;
        }
        swap(index, parentIndex);
        heapifyUp(parentIndex);
    }

    private void swap(int aIndex, int bIndex) {
        E tmp = array[aIndex];
        array[aIndex] = array[bIndex];
        array[bIndex] = tmp;
    }

    private int getParentIndex(int index) {
        return ((index - 1) / 2);
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private boolean hasParent(int index) {
        return (index != 0);
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) <= currMaxIndex;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) <= currMaxIndex;
    }

    private void expandArray() {
        array = ArrayCopy.copyComparableArray(array, array.length * 2);
    }

}