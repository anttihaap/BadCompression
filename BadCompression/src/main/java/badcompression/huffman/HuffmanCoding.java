package badcompression.huffman;

import java.util.PriorityQueue;

/**
 * Creates Huffman coding based on character frequencies.
 *
 * @author antti
 */
public class HuffmanCoding {

    private final HuffmanTreeNode huff_tree;
    private String[] charCodes;
    private String EOFcharCode;
    private final long[] charFreq;

    /**
     * Creates a Huffman coding tree based on character frequencies.
     * If createCharCodes is true, the tree is read for individual
     * binary codings for each character.
     * @param charFreq
     * @param createCharCodes
     */
    public HuffmanCoding(long[] charFreq, boolean createCharCodes) {
        this.charFreq = charFreq;
        huff_tree = createHuffmanTree(charFreq);
        if (createCharCodes) {
            charCodes = new String[charFreq.length];
            setCharacterCodes(huff_tree, "");
        }
    }

    private HuffmanTreeNode createHuffmanTree(long[] charFreq) {
        PriorityQueue<HuffmanTreeNode> pq = populateHeap(charFreq);
        while (pq.size() > 1) {
            HuffmanTreeNode smallest = pq.poll();
            HuffmanTreeNode largest = pq.poll();
            HuffmanTreeNode newNode = new HuffmanTreeNode(-1, largest.weight + smallest.weight, smallest, largest);
            pq.add(newNode);
        }
        return pq.poll();
    }

    private PriorityQueue<HuffmanTreeNode> populateHeap(long[] charFreq) {
        PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<>();
        for (int i = 0; i < charFreq.length; i++) {
            if (charFreq[i] > 0) {
                pq.add(new HuffmanTreeNode(i, charFreq[i], null, null));
            }
        }
        //Adds EOF-character
        pq.add(new HuffmanTreeNode(-1, -1, null, null));
        return pq;
    }

    private void setCharacterCodes(HuffmanTreeNode node, String s) {
        if (node.isLeafNode()) {
            if (node.c != -1) {
                charCodes[node.c] = s;
            } else {
                EOFcharCode = s;
            }
            return;
        }
        if (node.leftNode != null) {
            setCharacterCodes(node.leftNode, s + "0");
        }
        if (node.rightNode != null) {
            setCharacterCodes(node.rightNode, s + "1");
        }
    }

    /**
     * Returns characters new coding.
     * @param c char
     * @return  coding
     */
    public String getCharCode(int c) {
        return charCodes[c];
    }

    /**
     * Returns EOF-characters new coding.
     * @return EOF coding.
     */
    public String getEofCode() {
        return EOFcharCode;
    }

    /**
     * Returns the root of the Huffman coding tree.
     * @return root node
     */
    public HuffmanTreeNode getHuffTree() {
        return huff_tree;
    }
    
    /**
     * Returns the frequencies of original input.
     * @return
     */
    public long[] getCharFreq() {
        return charFreq;
    }

}