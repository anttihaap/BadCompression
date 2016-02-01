package badcompression.huffman;

/**
 * Represents a single node in a Huffman coding tree.
 * @author antti
 */
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
    
    /**
     * Character.
     */
    public int c;
    public long weight;
    public HuffmanTreeNode leftNode;
    public HuffmanTreeNode rightNode;
    
    /**
     * Creates a new Huffman tree node.
     * @param c character codepoint
     * @param weight weight
     * @param left leftNode node
     * @param right rightNode node
     */
    public HuffmanTreeNode(int c, long weight, HuffmanTreeNode left, HuffmanTreeNode right) {
        this.c = c;
        this.weight = weight;
        this.leftNode = left;
        this.rightNode = right;
    }
    
    /**
     * Checks if node is a leaf node.
     * @return
     */
    public boolean isLeafNode() {
        return (leftNode == null && rightNode == null);
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        if (o.weight == this.weight && o.c == this.c) return 0;
        if (this.weight > o.weight) {
            return 1; 
        } else if (this.weight < o.weight) { 
            return -1;
        }
        
        if (this.c > o.c) {
            return 1;
        } else {
            return -1;
        }    
    }
}
