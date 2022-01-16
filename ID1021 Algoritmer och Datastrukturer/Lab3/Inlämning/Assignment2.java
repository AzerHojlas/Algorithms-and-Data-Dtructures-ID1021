/*
    Author:         Azer Hojlas
    Date:           02/10-2021
    Description:    Binary search that takes in N amount of words, orders them, counts their frequencies and outputs runtime.

    Dependancies:   java.io.File ----- java.io.FileNotFoundException ----- java.util.Scanner
    Compilation:    javac BinarySearchST.java
    Execution:      java BinarySearchST
    Usage:          Replace file name in the main method with the file that should be ordered, the N with the 
                    amount of words to be read, the array capacity with N and result will be printed out on the terminal. 
                    Default set to 1000 words
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment2 {

    public static int N = 1000;
    public String[] keys = new String[N];
	public int[] values = new int[N];
	public int elements = 0;            // key value pairs



    /** Adds a unique word or increments a value if the word already exists
     * @param 
     */
    public void put(String key)
	{		
        if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 

		int index = bSearch(key);
		
        // If the ST isn't empty, if it isn't the last element and if it matches the result from the binary search
        // then we increment the value at that position
		if(!isEmpty()  && (index < elements) && keys[index].compareTo(key) == 0) {
            values[index] += 1;
            return;
        } 
        
            // A unique element is added and we start from the last element
            // Element always represents a null value
            // This null value asumes the value of the one below it
            // All elements are moved one step up or to the right in this way until we reach our desired index
            // This index will have a duplicate value with the one to it's right
            for(int i = elements; i > index; i--) {

        	    keys[i] = keys[i-1];
        	    values[i] = values[i-1];
            }
        
        
            // This index value is changed to the proper one
            keys[index] = key;
            values[index] = 1;
            elements++;
	}

    /** Checks if the ST is empty.  
     * @return true if the symbol table is empty and false if it is not.
    */
    public boolean isEmpty() {
        return elements == 0;
    }

    /** Searches if the provided key matches a index, if not, then it provides the location for where a new word is to be put.
     * @return the index at which a key is supposed to be
     * @param 
    */
    // Will keep searching until mid matches key or else it outputs where a key should be
    public int bSearch(String key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null"); 

        int lo = 0;
        int hi = elements-1; 

        while (lo <= hi) {                      
            int mid = lo + (hi - lo) / 2; 
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1; 
            else if (cmp > 0) lo = mid + 1; 
            else return mid; 
        } 
        return lo;
    } 
    /** Returns amount of unique words 
     * @return amount of unique words
    */
    public int getSize() { 

        return elements;
	}

    /**Prints out all the keys and their frequencies */
    public void printWords() {

        for(int i = 0; i < getSize(); i++)
	    {
            System.out.println(keys[i] + "\t\t" + values[i]);
	    }

        System.out.println();
        System.out.println("Amount of unique words: " + getSize() + "\n");
    }

    public void mostFrequent () {

        int max = 0;
        int key = 0;

        for (int i = 0; i < elements; i++) {

            if(values [i] > max) {
                max = values [i];
                key = i;
            }
        }

        System.out.println("The most frequent word is " + "\'" + keys[key] + "\'" +  " with frequency " + max + "\n");
    }

    public static void main(String[] args) throws FileNotFoundException {

        // Help code to read in the words
		File file = new File("cleaned.txt");
	    Scanner sc = new Scanner(file);	 

        // instantiation of class object
	    Assignment2 symbolTable = new Assignment2();

        // Measurement oftime
        long startTime = System.nanoTime();
	    runwords(sc, symbolTable);
        long elapsedTime = System.nanoTime() - startTime;

        // prints out all keys and their words
		symbolTable.printWords();

        System.out.println("Total execution time to order " + symbolTable.N + " words with Binary Search: " + elapsedTime/(1000000) + " milliseconds \n");

        // print most frequent key
        symbolTable.mostFrequent();

    }
	
	private static void runwords(Scanner sc, Assignment2 symbolTable)
	{

        int wordCounter = 0;

		while (sc.hasNext() && wordCounter < Assignment2.N) {

		     String word  = sc.next().toLowerCase();
		     symbolTable.put(word);
             wordCounter++;
		}
        sc.close();
	}
}