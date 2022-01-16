/******************************************************************************
 *  @author:      Azer Hojlas
 *  @Date:        04/10-2021 
 *  Compilation:  javac -cp .:algs4.jar Assignment2.java
 *  Execution:    java -cp .:algs4.jar Assignment2 N
 *  Dependencies: Insertion.java >>>>>> Quickshuffling.java >>>>>> Quick3way.java
 *  Data files:   none
 *  Usage:        Run the programe with the execution statement above, where N is the amount of integers 
 *                and the upper limit of a range of distributed values.
 ******************************************************************************/

import java.util.Random;

public class Assignment2    {

    /**
     * 
     * @param alg i.e the algorithm to use
     * @param a which is a uniformly distributed sequence of numbers to test the algorithms with
     * @return the execution time of a specific algorithm in milliseconds
     */
    public static double time(String alg, Integer[] a) { 

        long startTime = System.nanoTime();

        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("QuickShuffling")) QuickShuffling.sort(a);
        if (alg.equals("Quick3way")) Quick3way.sort(a);

        long endTime = System.nanoTime();
        long timeElapsed = (endTime - startTime);

        return timeElapsed/1000000.0;
    }

    /**
     * Computes a randomly distributed sequence of numbers that is then passed on to a time computing method
     * @param alg , i.e the algorithm to use
     * @param N upper limit of the interval from 0 to N, where N represents both amount of numbers and range of outcomes
     * @return the execution time of the algorithm
     */
    public static double timeRandomInput(String alg, int N) { 

        long seed = 3;

        Random uniform = new Random(seed);

        double total = 0;
        Integer[] a = new Integer[N];

        for (int i = 0; i < N; i++) 
            a[i] = uniform.nextInt(N + 1);

        total = time(alg, a);

        return total;
    }
    public static void main(String[] args)  {


        String insertion = "Insertion";
        String quickShuffling = "QuickShuffling";
        String quick3way = "Quick3way";

        int upperLimit = Integer.parseInt(args[0]);

        double timeInsertion = timeRandomInput(insertion, upperLimit);
        double timeQuickShuffle = timeRandomInput(quickShuffling, upperLimit);
        double timeQuick3way = timeRandomInput(quick3way, upperLimit);

        System.out.printf("%n%s takes %.3f milliseconds to complete for %d integers %n", quickShuffling, timeQuickShuffle, upperLimit);
        System.out.printf("%n%s takes %.3f milliseconds to complete for %d integers %n", quick3way, timeQuick3way, upperLimit);
        System.out.printf("%n%s takes %.3f milliseconds to complete for %d integers %n%n", insertion, timeInsertion, upperLimit);
       

        System.out.printf("For %d random uniformly distributed Integers%n %s is", upperLimit, quickShuffling); 
        System.out.printf(" %.1f times faster than %s%n%n", timeInsertion/timeQuickShuffle, insertion);


        System.out.printf("For %d random uniformly distributed Integers%n %s is", upperLimit, quick3way); 
        System.out.printf(" %.1f times faster than %s%n%n", timeInsertion/timeQuick3way, insertion);
    } 
}
