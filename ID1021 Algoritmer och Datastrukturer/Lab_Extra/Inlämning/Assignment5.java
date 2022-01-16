/******************************************************************************
 *  @author:      Azer Hojlas
 *  @Date:        04/10-2021 
 *  Compilation:  javac -cp .:algs4.jar SeparateChainingHashST.java
 *  Execution:    java -cp .:algs4.jar SeparateChainingHashST text
 *  Dependencies: SequentialSearchST.java Queue.java
 *  Data files:   
 *  Usage:        Execute the lines written above, where text is any text that the client wishes to correct
 * 
 *
 *  A symbol table implemented with a separate-chaining hash table. Used for correcting common misspellings in a text. Outputs
 *  a corrected version of the inputted text.
 * 
 ******************************************************************************/

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;

@SuppressWarnings("unchecked")


public class Assignment5<Key, Value> {
    private static final int INIT_CAPACITY = 9679;

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

    public int size() {
        return n;
    } 

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    } 

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    } 

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
     * Builds a hashTable that contains the list of misspelled words and their corresponding corrected counterparts
     * @param scanner that reads in words from the corrections file
     * @param symbolTable Where the correct and not correct pairs are placed
     */
    public static void buildTable (Scanner scanner, Assignment5<String, String> symbolTable) {

        String notSplitted;

        String [] splitted = null;

        while (scanner.hasNextLine()) {

            notSplitted = scanner.nextLine();

            splitted = notSplitted.split("->|, ");

            if(!symbolTable.contains(splitted[0]))
                symbolTable.put(splitted[0], splitted[1]);

       }
       scanner.close();

    }

    public static void main(String[] args) throws IOException { 

        Assignment5<String, String> st = new Assignment5<String, String>();

        File file = new File("Corrections.txt");
        Scanner sc = new Scanner(file);

        String text = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.US_ASCII);

        buildTable(sc, st);

       // prova String[] words = text.replaceAll("\p{Punct}", "").split(" ")

        String [] textSplitted = text.split(" ");
        String [] furtherSplitted;

        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        
        for (int i = 0; i < textSplitted.length; i++) {

            Matcher matcher = pattern.matcher(textSplitted[i]);

            if (matcher.find()){

                furtherSplitted = textSplitted[i].split("((?=[^A-Za-z0-9])|(?<=[^A-Za-z0-9]))"); // (?=[^A-Za-z0-9])

                if  (st.contains(furtherSplitted[0])) {
                        furtherSplitted [0] = st.get(furtherSplitted[0]);
                        textSplitted[i] = furtherSplitted[0].concat(furtherSplitted[1]);
                    }
                if  (furtherSplitted.length > 1 && st.contains(furtherSplitted[1])) {
                    furtherSplitted [1] = st.get(furtherSplitted[1]);
                    textSplitted[i] = furtherSplitted[0].concat(furtherSplitted[1]);
                }

                // if  (furtherSplitted.length > 2 && st.contains(furtherSplitted[3])) {
                //     furtherSplitted [1] = st.get(furtherSplitted[1]);
                //     textSplitted[i] = furtherSplitted[0].concat(furtherSplitted[1]);
                // }


            }    else if  (st.contains(textSplitted[i])) 
                            textSplitted [i] = st.get(textSplitted[i]);
       }

       text = String.join(" ", textSplitted);
    
        File output = new File ("output.txt");
       
        Files.write(Paths.get("output.txt"), text.getBytes());
       
    }
}
