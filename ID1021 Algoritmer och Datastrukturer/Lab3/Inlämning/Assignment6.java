/******************************************************************************
 *  @author:      Azer Hojlas
 *  @Date:        04/10-2021 
 *  Compilation:  javac -cp .:algs4.jar Assignment6.java
 *  Execution:    java -cp .:algs4.jar Assignment6 98-0.txt
 *  Dependencies: SequentialSearchST.java Queue.java
 *  Data files:   98-0.txt
 *
 *  A symbol table implemented with a separate-chaining hash table.
 * 
 ******************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;

@SuppressWarnings("unchecked")


public class Assignment6<Key, Value> {
    private static final int INIT_CAPACITY = 10000;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


    /**
     * Initializes an empty symbol table.
     */
    public Assignment6() {
        this(INIT_CAPACITY);
    } 

    /**
     * Initializes an empty symbol table with {@code m} chains.
     * @param m the initial number of chains
     */
    public Assignment6(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    } 


    // hash function for keys - returns value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    } 



    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    } 

    /**
     * Returns the value associated with the specified key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with {@code key} in the symbol table;
     *         {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    } 

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, val);
    } 

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    } 


    /**
     * Unit tests the {@code Assignment6} data type.
     *
     * @param args the command-line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException { 

        Assignment6<String, Integer> st = new Assignment6<String, Integer>();
       
        File file = new File(args[0]);
	    Scanner sc = new Scanner(file);	 

        Scanner questions = new Scanner(System.in);
        
        runwords(sc, st);

        askQuestions(questions, st);


    }

    private static void runwords(Scanner sc, Assignment6<String, Integer> st)  {
        

		while (sc.hasNext()) {

		     String word  = sc.next();

             if (st.get(word) != null) {
                st.put(word, st.get(word) + 1);
             } 
             if (st.get(word) == null) {
                st.put(word, 1);
             } 
		}
        sc.close();
	}

    private static void askQuestions (Scanner sc, Assignment6<String, Integer> st) {

            while(true) {

                System.out.print("\nType in a word which occurs in the text and its corresponding frequency will be retrieved: ");
                String input = sc.next();
                System.out.println("\nFrequency of " + input + " is " + st.get(input));
            }
    }
}
