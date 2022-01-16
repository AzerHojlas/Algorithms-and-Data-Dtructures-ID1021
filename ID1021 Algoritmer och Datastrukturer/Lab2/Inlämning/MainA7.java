import java.util.*;

public class MainA7 {

    public static void main(String[] args) {

        Insertion sort = new Insertion();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements you want to store: "); 
        int n = scanner.nextInt();
        int [] test = new int [n];


        System.out.println("Enter the elements of the array: "); 

        //reading array elements from the user 
        for(int i = 0; i < n; i++)  {    
            test [i] = scanner.nextInt();  
        }  

        scanner.close();

        System.out.println();

        System.out.println("Array elements: ");

        for (int b = 0; b < n; b++) {
            test [b] = (test [b]) * -1;
        }

        for (int a: test) 
            System.out.print(a + "\t");

        System.out.println("\n");

        System.out.println("Inversions: ");
        sort.countInversions(test);

        System.out.println("Sorting steps:");

        sort.insertionSort (test, n);

        for (int a = 0; a < n; a++) {
            test [a] = (test [a]) * -1;
        }

        System.out.println("Final sorted array:");

        for (int b: test) 
            System.out.print(b + "\t");

            System.out.println("\n");

        System.out.println("Amount of swaps performed: " + sort.getSwaps());
        System.out.println("Amount of inversions: " + sort.getInversions());
        System.out.println();

    }
}