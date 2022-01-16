/******************************************************************************
 *  Compilation:  javac -cp .:algs4.jar Assignment5.java
 *  Execution:    java -cp .:algs4.jar Assignment5 < "INPUT FILE"
 *  Dependencies: Princeton java library: edu.princeton.cs.algs4.StdIn ------ edu.princeton.cs.algs4.StdOut ----- edu.princeton.cs.algs4.Queue
 *  
 *
 *  A symbol table implemented with a separate-chaining hash table. instead of value frequencies it holds key hashcodes
 *  This way all the keys in the chains can be compared to eachpther and we can calculate collisions
 * 
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

import java.util.Objects; // for comparisons


@SuppressWarnings("unchecked")

public class Assignment5<Key, Value> {

    private static final int INIT_CAPACITY = 10000;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


    /**
     * Initializes an empty symbol table.
     */
    public Assignment5() {
        this(INIT_CAPACITY);
    } 

    /**
     * Initializes an empty symbol table with {@code m} chains.
     * @param m the initial number of chains
     */
    public Assignment5(int m) {
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
    public int amountOfChains() {
        return INIT_CAPACITY;
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
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
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
        return st[i].get(key);  // passes on get to sequential search which accesses symbol table  and symbol table retrieves from treeMap
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
        if (!st[i].contains(key)) n++;  // if a chain doesn't contain a key then the key value pair will be incremented
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
    // Iterates through the chains one by one
    // All the keys and hashcodes get compared to eachother twice on account of the two for each statements
    public static double getCollisions (Assignment5<String, Integer> st) {

        int collisions = 0;
        
        for (int i = 0; i < st.amountOfChains(); i++) {

            for (String iterate : st.st[i].keys()) {
                
                for (String iterate2 : st.st[i].keys()) {

                        if ((Objects.equals(st.get(iterate), st.get(iterate2))) && !(iterate.equals(iterate2))) 
                            collisions++;
                }
            }
        }
        collisions = collisions/2; // The collisions get counted twice, that is why we must divide by two

        return collisions;
    }

    // prints out the collisions per unique words
    public static void printDistribution (double collisions, double uniqueWords) {

        double collperwords = collisions/uniqueWords;

        System.out.println("\nCollisions: " + (int) collisions + "\n");

        System.out.println("Unique words: " + (int) uniqueWords + "\n");

        System.out.println("Collisions per unique word: Circa " + String.format("%.5g%n", collperwords) + "\n");

    }


    /**
     * Unit tests the {@code SeparateChainingHashST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) { 


        // instantiate a separate chaininh hash table
        Assignment5<String, Integer> st = new Assignment5<>();


        // Help code to read in words
        while (!StdIn.isEmpty()) {

            String key = StdIn.readString();
            
            st.put(key, key.hashCode());
         }

         // get collisions
        double collisions = getCollisions(st);

        double uniqueWords = (double) st.size();

        // prints how well the hashcode is distributed
        printDistribution(collisions, uniqueWords);
    }
}
