/*
    Author:         Azer Hojlas
    Copyright:      2021
    Description:    A generic binary search tree, transcribed from Algorithms 4th
                    ed. (Sedgewick, Wayne) with a main test function that counts
                    the frequency of the first N words using a slightly
                    altered version of the frequency counter from Algorithms 4th ed.
    Dependancies:   java.util and java.io
    Compilation:    javac Assignment7.java 
    Execution:      java Assignment7 
    Usage:          Replace file name with the file that should be read and
                    The result will be printed on the terminal
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment7 {

    private Node root;       // root of BST

    private class Node {
        private String key;           // sorted by key
        private int val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node (String key, int val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }


    /**
     * Returns the value associated with the given key.
     *
     * @param  key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    private int get(String key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");

        Node x = root;

        while(x != null) {

            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;

        }
        return 0;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(String key, int val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");

        root = put(root, key, val);
    }
    // explained in assignment 3
    private Node put(Node x, String key, int val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    // explained in assignment 3
    private static int run200words(Scanner sc, Assignment7 symbolTable)  {
        
		int wordCounter = 0;

		while (sc.hasNext() && wordCounter < 200) {

		     String word  = sc.next();

             if (symbolTable.get(word) != 0) {
                symbolTable.put(word, symbolTable.get(word) + 1);
                wordCounter++;
             } 
             if (symbolTable.get(word) == 0) {
                symbolTable.put(word, 1);
                wordCounter++;
             } 
		}
        sc.close();

        return wordCounter;
	}
    // Traverse postorder
	public void postOrder(Node node)   {

		if(node == null) return;
		
		postOrder(node.right);

		System.out.printf("%-15s%n", node.key);

		postOrder(node.left);
	}

    // Traverse in order
    public void inOrder(Node node) {

	    if(node == null) return;

	    inOrder(node.left);

	    System.out.printf("%-15s%n", node.key);

	    inOrder(node.right);
	}
	
	public void printReverseAlphabetic()    {

        System.out.println("\nPrint in reverse alphabetic order: \n");
		postOrder(root);
	}

    public void printAlphabetic()   {

        System.out.println("\nPrint in alphabetic order: \n");
		inOrder(root);
	}

    public static void main(String[] args) throws FileNotFoundException {

		File file = new File("cleaned.txt");
	    Scanner sc = new Scanner(file);	 
	    Assignment7 symbolTable = new Assignment7();

        long startTime = System.nanoTime();
	    int words = run200words(sc, symbolTable);
        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("Total execution time to order 1000 words with BST: " + elapsedTime/(1000000) + " milliseconds");

        symbolTable.printAlphabetic();
        symbolTable.printReverseAlphabetic();

        System.out.println("Printed words: " + words);
    }
}