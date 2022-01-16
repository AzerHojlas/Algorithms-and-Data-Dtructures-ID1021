/*
    Author:         Azer Hojlas

    Copyright:      2021

    Description:    Binary search tree that orders keys in a symbol table

    Dependancies:   java.util.Scanner ------- java.io.File -------- java.io.FileNotFoundException ------ java.util.NoSuchElementException

    Compilation:    javac Assignment3.java 

    Execution:      java Assignment3 N 

    Usage:          Replace file name with the file that should be read and
                    N with how many words should be read. The result will be printed on the terminal
 */

import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment3 {

    private Node root;             // root of BST

    private class Node {
        private String key;           // sorted by key
        private int val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(String key, int val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
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

    // If node is null, then it creates a new node
    // If key is smaller than current node it goes left and if bigger it goes right
    // If key matches then value gets updated
    private Node put(Node x, String key, int val) {
        
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);  // size gets incremented
        return x;
    }

    public static void main(String[] args) throws FileNotFoundException {

        // how many words to read from file
        int N = Integer.parseInt(args[0]);

        // help code to read in file
		File file = new File("cleaned.txt");
	    Scanner sc = new Scanner(file);	
        
        // Instantiate object of BST class
	    Assignment3 symbolTable = new Assignment3();

        long startTime = System.nanoTime();
	    runNwords(sc, symbolTable, N);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("\nTotal execution time to order " + N + " words with BST: " + elapsedTime/(1000000) + " milliseconds\n");

        // Test
        System.out.println("Test: \"the\" has value: " + symbolTable.get("the") + "\n");

    }

    private static void runNwords(Scanner sc, Assignment3 symbolTable, int N)  {
        
		int wordCounter = 0;

		while (sc.hasNext() && wordCounter < N) {

		     String word  = sc.next().toLowerCase();

             // If the key already exists then the frequency gets incremented
             if (symbolTable.get(word) != 0) {
                symbolTable.put(word, symbolTable.get(word) + 1);
                wordCounter++;
             } 
            // if not then the key gets put in with value 1
             if (symbolTable.get(word) == 0) {
                symbolTable.put(word, 1);
                wordCounter++;
             } 
		}
        sc.close();
	}
}