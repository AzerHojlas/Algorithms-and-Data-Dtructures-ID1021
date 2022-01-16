import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")

public class QuickShuffling  {

    private static final int M = 14;

    public static void sort(Comparable[] a)    {

        StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1); 
    }

    private static void sort(Comparable[] a, int lo, int hi)    {

        if (hi <= lo + M) { 

            Insertion.sort(a, lo, hi + 1); // Why does it work with plus 1?
            return; 
        }
        
        // int n = hi - lo + 1;
        // if (n <= M) {
        //     Insertion.sort(a, lo, hi + 1);
        //     return;
        // }

        // if (hi <= lo) return;

        int j = partition (a, lo, hi); // Partition (see page 291). 
        sort(a, lo, j-1); // Sort left part a[lo .. j-1]. 
        sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    } 

    private static int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].

        // left and right scan indices 
        int i = lo;
        int j = hi+1; 
        
        Comparable v = a[lo]; // partitioning item

        while (true)    { // Scan right, scan left, check for scan complete, and exchange.

            while (less(a[++i], v))     if (i == hi) break; 
                
            while (less(v, a[--j]))     if (j == lo) break; 
                
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    } 

    private static void exch(Comparable[] unsorted, int swap, int swapWith)  {  

        Comparable temporary = unsorted[swap]; 
        
        unsorted[swap] = unsorted[swapWith]; 
        unsorted[swapWith] = temporary;  
    }

    private static boolean less(Comparable compare, Comparable isLess) { 
        
        return compare.compareTo(isLess) < 0; 
    }

    static void show (Comparable[] sorted) { // Print the array, on a single line. 
        
        for (int i = 0; i < sorted.length; i++)
            StdOut.print(sorted[i] + " "); 

        StdOut.println();
    }
}
