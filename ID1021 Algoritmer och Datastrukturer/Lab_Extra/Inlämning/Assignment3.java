/******************************************************************************
 *  @author:      Azer Hojlas
 *  @Date:        04/10-2021 
 *  Compilation:  javac -cp .:algs4.jar Assignment3.java
 *  Execution:    java -cp .:algs4.jar Assignment3 seed tuplesAmount
 *  Dependencies: Insertion.java >>>>>> Tuple.java
 *  Data files:   none
 *  Usage:        Run the programe with the execution statement above, where seed is the seed for the sequence of numbers 
 *                in the PRNG and tuplesAmount is the amount of tuples the client wishes to create. If the client wishes to create their
 *                own tuples then please comment out the appropriate code in the main method
 *                
 *  Description:  Program to sort tuples.
 ******************************************************************************/

import java.util.Random;
import edu.princeton.cs.algs4.StdIn;

public class Assignment3 {

    /**
     * 
     * Create tuples with user input as opposed to randomly generated where the highest number can be no larger than three digits and where the first character must be alphabetical
     * @return tuples that the client has inputted
     */
    public static Tuple [] userInput () {

        System.out.print("\nAmount of tuples: ");

		int tuplesAmount = StdIn.readInt();

		Tuple [] tuples = new Tuple[tuplesAmount];

        System.out.println("\nPlease enter tuples below: \n");

        int i = 0;
        String s;
        int v;

        while (i < tuples.length) {

            s = StdIn.readString();
            v = StdIn.readInt();

            if (v > 999 || v < 0) throw new IllegalArgumentException("negative integers and those exceeding three digits are not allowed");

            tuples [i] = new Tuple (s, v);

            i++;
        }

        return tuples;
    }
    /**
     * Generates tuples using a PRNG where the highest number can be no larger than three digits and where the first character must be alphabetical
     * @param seed for the pseudo random number generator
     * @param tuplesAmount the amount of tuples to sort
     * @return
     */
    public static Tuple [] generatedInput (long seed, int tuplesAmount) {

        Random random = new Random (seed);

        Tuple [] tuples = new Tuple[tuplesAmount];

        for (int i = 0; i < tuples.length; i++)
            tuples [i] = new Tuple ((char)(random.nextInt(26) + 'A'), random.nextInt(1000));

       
        return tuples;
    }

    public static void main(String[] args) {

        long seed = Long.parseLong(args[0]);
        int tuplesAmount = Integer.parseInt(args[1]);

        // De-comment this line if you wish to generate the input randomly using a seed and a pseudo-random number generator
        Tuple [] tuples = generatedInput(seed, tuplesAmount);

        // De-comment this line if you wish to create the input manually
        // Tuple [] tuples = userInput();

        Insertion.sort(tuples);
        System.out.println("\nSorted list of tuples below: ");
        Insertion.show(tuples);
        System.out.println();
	}    
}
