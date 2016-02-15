/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.huffman;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Note: tested by simulating tree construction on paper.
 * @author antti
 */
public class HuffmanCodingTest {
    
    private long[] freq1;
    private HuffmanCoding coding1;
    
    public HuffmanCodingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    
    @Before
    public void setUp() {
        freq1 = new long[5];
        for (int i = 0; i < 5; i++) {
            freq1[i] = i+1;
        }
        coding1 = new HuffmanCoding(freq1,true);
    }
    
    @After
    public void tearDown() {
    }
    
    /*
    HuffmanTree construction simulated on paper.
    */
    @Test
    public void codings1Test() {
        String[] expected = {"11001","1101","111","10","0"};
        for (int i = 0;i< freq1.length;i++) {
            assertEquals(expected[i], coding1.getCharCode(i));
        }
        assertEquals("11000", coding1.getEofCode());
    }
    
    @Test
    public void rootNode1Test() {
        assertEquals(14,coding1.getHuffTree().weight);
    }
    
    @Test
    public void treeNodes1Test() {
        assertEquals(11,countNodes(coding1.getHuffTree()));
    }
    
    public int countNodes(HuffmanTreeNode node) {
        if (node.isLeafNode()) return 1;
        int counter = 0;
        if (node.leftNode != null) counter += countNodes(node.leftNode);
        if (node.rightNode != null) counter += countNodes(node.rightNode);
        return counter+1;
    }
  
}