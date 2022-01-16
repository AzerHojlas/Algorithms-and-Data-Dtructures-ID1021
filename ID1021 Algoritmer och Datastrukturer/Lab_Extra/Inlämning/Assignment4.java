/******************************************************************************
 *  @author:      Azer Hojlas
 *  @Date:        04/10-2021 
 *  Compilation:  javac -cp .:algs4.jar Assignment4.java
 *  Execution:    java -cp .:algs4.jar Assignment4 text
 *  Dependencies: Insertion.java >>>>>> Quick3way.java >>>>>> KeyValue.java
 *  Data files:   none
 *  Usage:        Run the programe with the execution statement above, where text is the file that you want to search through
 *               
 *                
 *  Description:  Program that finds the frequency count of all words in a text
 ******************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;


public class Assignment4 <Key extends Comparable<Key>, Value> {

    private Node root;             

    private int Index = 0;

    private KeyValue [] pairs;

    private class Node {

        private Key key;           
        private Value val;         
        private Node left, right;  
        private int size;         

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    /**
     * Method to increment the private index variable
     */
    public void incrementIndex () {

        Index++;
    }
    /**
     * Method that puts key-value pairs in an array. Key-value pairs are defined in another class
     * @param key in key-value pair
     * @param val in key-value pair
     */
    public void assignPairs (Key key, Value val) {

        pairs [Index] = new KeyValue((String) key, (Integer) val);

        incrementIndex();
    }

    // Creates an empty keyValue array with the same size as the BST
    public void initPairs () {

        pairs = new KeyValue [size()];

    }

    public int size()   {  

        return size(root); 
    }

    private int size(Node x)    {

        if (x == null) return 0;

        else return x.size; 
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key)   {  

        return get(root, key);  
    }
    /**
     * Get retrieves the value of a key that is sought after
     * @param x is at first the root, after which we will traverse the tree to the proper node (if found, otherwise get returns null)
     * @param key of the corresponding key-value pair which we wish to return
     * @return the value of the associated key if it exists
     */
    private Value get(Node x, Key key)  { 

        // return null if key not present in subtree rooted at x.
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key); 
        else if (cmp > 0) return get(x.right, key); 
        else return x.val;
    }

  
    public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.

     root = put(root, key, val);
    }

    /**
     * Puts the key-value pair in their appropriate position in the tree
     * @param x is in cases where the key doesn't exist either the root or an empty branch. If the BST is empty then the root gets created
     * @param key is the key that one wants to put in the BST
     * @param val is the value that is to be associated with the key
     * @return the node which assumes it's appropriate position in the tree
     */
    private Node put(Node x, Key key, Value val)    {

        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val. 

        if (x == null) return new Node(key, val, 1);
        
        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = put(x.left, key, val);

        else if (cmp > 0) x.right = put(x.right, key, val);

        else x.val = val;

        x.size = size(x.left) + size(x.right) + 1;

        return x;
    }
    /**
     * Traverses the tree from highest key pair to lowest during which all the key-value pairs getput into an array
     * @param node initially the root, from which we traverse the tree from highest key value to lowest
     */
    public void postOrder(Node node) {

	    if(node == null) return;

	    postOrder(node.right);

        assignPairs(node.key, node.val);

	    postOrder(node.left);
	}

    // Initializes a key-value array, traverses through a tree and assigns the key-value pairs to said array, 
    // after which the array gets sorted
    public void buildOrdered()   {

        initPairs();

		postOrder(root);

        Quick3way.sort(pairs);
	}
    /**
     * Prints out the n:th most frequent word
     * @param n is the n:th most frequent word
     */
    public void ranked (int n) {

        if (n == 0) throw new IllegalArgumentException("cannot search for 0th most frequent word");

        int position = size() - n;

        System.out.print(n + " ");

        System.out.println(pairs[position]);

    }
    /**
     * Prints out the n:th to k:th most frequent numbers in said range
     * @param from lower bound of range
     * @param to upper bound of range
     */
    public void rankedRange (int from, int to) {

        for (int i = 0; (from + i) <= to ; i++)
            ranked(from + i);

    }
    /**
     * Build index counts the frequency of each word that the program recieves. If a word that has been read does not exists, 
     * it gets put into the BST with value 1. If italready exists, it gets put into the BST with value = (old value + 1)  
     * @param scanner scanner that reads input from 
     * @param symbolTable that stores the input and it's corresponding frequency
     */
    public static void buildIndex(Scanner scanner, Assignment4 <String, Integer> symbolTable)  {
        
		while (scanner.hasNext()) {

            String word  = scanner.next();

            if (symbolTable.get(word) != null) {
                symbolTable.put(word, symbolTable.get(word) + 1);
            } 
            if (symbolTable.get(word) == null) {
                symbolTable.put(word, 1);
            } 
		}
        scanner.close();
	}
    // The user is given the options to either ask for then:th most frequent word, or a range of most frequent words, upon which
    // the client inputs either the specific rank or range.
    public void questions () {

        while (true) {

            System.out.print("\nWould you like to find the frequency of a specified word or a range of words? \nType 1 for a specific word and 2 for a range of words: ");

            int option = StdIn.readInt();

            if (option == 1) {

                System.out.print("Type in the index of the specified word: ");

                int rankNumber = StdIn.readInt();

                System.out.println("\nMost frequent word at index: " + rankNumber);
                System.out.println("Column 1 represents the rank, 2 the word and 3 the frequency of the word\n");

                ranked(rankNumber);

                System.out.println("_________________________________________________________________________");

            }

            if (option == 2) {

                System.out.println("Type in the inclusive bounds of the ranges: ");

                System.out.print("Lower bound: ");

                int rangeLower = StdIn.readInt();

                System.out.print("Upper bound: ");

                int rangeUpper = StdIn.readInt();

                System.out.printf("%n Most frequent words from %d to %d%n", rangeLower, rangeUpper);
                System.out.println("Column 1 represents the rank, 2 the word and 3 the frequency of the word\n");

                rankedRange(rangeLower, rangeUpper);

                System.out.println("_________________________________________________________________________");

            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {

        String input = args[0];
		File file = new File(input);
	    Scanner sc = new Scanner(file);	 
	    Assignment4 <String, Integer> symbolTable = new Assignment4 <> ();

        long startTime = System.nanoTime();
	    buildIndex(sc, symbolTable);
        long elapsedTime = System.nanoTime() - startTime;
        

        System.out.println("\nTime to order leipzig1m.txt with BST with regards to key size: " + elapsedTime/(1000000000) + " seconds");

	    symbolTable.buildOrdered();

        symbolTable.questions();
    }
}