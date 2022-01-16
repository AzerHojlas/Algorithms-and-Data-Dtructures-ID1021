import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")

public class Quick3way {

    private final static int M = 15;

    public static void sort(Comparable[] a)    {

        sort(a, 0, a.length - 1); 
    }


    private static void sort(Comparable[] a, int lo, int hi) { 

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

        int lt = lo; 
        int gt = hi;

        Comparable v = a[lo];
        int i = lo + 1;

        while (i <= gt) {

            int cmp = a[i].compareTo(v);

            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else    i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
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
