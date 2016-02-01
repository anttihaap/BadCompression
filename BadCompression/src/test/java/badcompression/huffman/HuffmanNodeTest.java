package badcompression.huffman;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class HuffmanNodeTest {
    
    public HuffmanNodeTest() {
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
    public void comparableTest() {
        HuffmanTreeNode a = new HuffmanTreeNode('A',1,null,null);
        HuffmanTreeNode b = new HuffmanTreeNode(0, 2, null, null);
        HuffmanTreeNode c = new HuffmanTreeNode('A', 1, null, null);
        HuffmanTreeNode d = new HuffmanTreeNode('B', 1, null, null);
        HuffmanTreeNode e = new HuffmanTreeNode('A', 2, null, null);
        assertEquals(-1, a.compareTo(b));
        assertEquals(1, b.compareTo(a));
        assertEquals(0, a.compareTo(c));
        assertEquals(0, c.compareTo(a));
        assertEquals(1,d.compareTo(a));
        assertEquals(-1, a.compareTo(d));
    }
    
    @Test
    public void leafTest() {
        HuffmanTreeNode a = new HuffmanTreeNode(0, 0, null, null);
        HuffmanTreeNode b = new HuffmanTreeNode(0, 0, null, null);
        assertTrue(a.isLeafNode());
        a.leftNode=b;
        assertFalse(a.isLeafNode());
        a.rightNode=b;
        assertFalse(a.isLeafNode());
    }
}
