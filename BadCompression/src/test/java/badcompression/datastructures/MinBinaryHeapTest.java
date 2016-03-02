package badcompression.datastructures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import badcompression.datastructures.MinBinaryHeap;
import badcompression.huffman.HuffmanTreeNode;
import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antti
 */
public class MinBinaryHeapTest {
    
    public MinBinaryHeapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sizeTest() {
        MinBinaryHeap<Integer> heap = new MinBinaryHeap<>();
        assertEquals(0, heap.size());
        int n = 1000;
        for (int i = 1; i <= n; i++) {
            //Peeking should not affect anything.
            heap.peekMin();
            heap.insert(i);
            assertEquals(i,heap.size());
        }
        
        for (int i = n-1; i >= 0; i--) {
            heap.removeMin();
            //Peeking should not affect anything.
            heap.peekMin();
            assertEquals(i, heap.size());
        }
        assertEquals(0, heap.size());
    }
    
    @Test
    public void nullreturnTest() {
        MinBinaryHeap<Integer> heap = new MinBinaryHeap<>();
        assertNull(heap.peekMin());
        assertNull(heap.removeMin());
        assertEquals(0, heap.size());
    }
    
    @Test
    public void testHeapWithHuffmanTreeNodes() {
        HuffmanTreeNode[] nodes = getRandomHuffmanTreeNodeArray(10000);
        MinBinaryHeap<HuffmanTreeNode> heap = new MinBinaryHeap<>();
        for (HuffmanTreeNode node : nodes) {
            heap.insert(node);
        }
        HuffmanTreeNode[] copy = Arrays.copyOf(nodes, nodes.length);
        Arrays.sort(copy);
        for (int i = 0; i < nodes.length; i++) {
            assertEquals(copy[i], heap.removeMin());
        }
    }
    
    @Test
    public void testHeapWithArray() {
        int[] arr = getRandomIntArray(10000);
        MinBinaryHeap<Integer> heap = new MinBinaryHeap<>();
        for (int i : arr) {
            heap.insert(i);
        }
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        for (int i = 0; i < arr.length; i++) {
            int actual = heap.removeMin();
            assertEquals(copy[i], actual);
        }
    }

    private int[] getRandomIntArray(int length) {
        int[] array = new int[length];
        
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = r.nextInt();
        }
        
        return array;
    }
    
    private HuffmanTreeNode[] getRandomHuffmanTreeNodeArray(int length) {
        Random r = new Random();
        HuffmanTreeNode[] nodes = new HuffmanTreeNode[length];
        for (int i = 0; i < length; i++) {
            HuffmanTreeNode randomNode = new HuffmanTreeNode(r.nextInt(), r.nextLong(), null, null);
            nodes[i] = randomNode;
        }
        return nodes;
    }
}
