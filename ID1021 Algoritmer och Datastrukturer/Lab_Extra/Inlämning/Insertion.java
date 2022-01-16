@SuppressWarnings("unchecked")

public class Insertion  {

    public static void sort(Comparable[] unsorted) { // Sort a[] into increasing order.

        int N = unsorted.length;

        for (int i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..

            for (int j = i; j > 0 && less(unsorted[j], unsorted[j-1]); j--) {
                exch(unsorted, j, j-1);
            }
        } 
    }

    // Used for quick sort cutoff
    public static void sort (Comparable[] a, int lo, int hi) {
        
        for (int i = lo + 1; i < hi; i++) {

            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }


    public static boolean isSorted(Comparable[] unsorted)  { // Test whether the array entries are in order.

        for (int i = 1; i < unsorted.length; i++)  {

            if (less(unsorted[i], unsorted[i-1])) return false;
        }

        return true;
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
            System.out.print(sorted[i] + " "); 


        System.out.println();
    }
}
