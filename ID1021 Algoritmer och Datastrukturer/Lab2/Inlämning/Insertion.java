public class Insertion {

    private int swaps = 0;
    private int inversions = 0;


    // If an element in the inner loop is smaller than the index element in the outer, then they get swapped
    public void insertionSort (int [] array, int arrayLength) {
        int i = 0; 
        int j = 0;

        for(i = 1; i < arrayLength; i++) {

            for(j = i; j > 0 && less(array[j], array[j-1]); j--) {

                swap(array, j, j-1);
                swaps++;
                for (int num: array)
                    System.out.print(num + "\t");
                System.out.println();
        
            }
        }

        System.out.println();
    }

    // compare method
    public int compareTo (int compare, int compareTo) {
       
        if (compare > compareTo) return 1;
        if (compare < compareTo) return -1;
        return 0;
    }

    // Retrieves the amount of swaps  performed
    public int getSwaps() {

        return swaps;
    }

    // retrieves the amount of inversions
    public int getInversions() {

        return inversions;
    }

    // swaps out elements
    public void swap (int [] array, int swap, int swapWith) {

        int temp = array [swap];
        array [swap] = array [swapWith];
        array [swapWith] = temp;
    }

    // compares if an int is smaller than an other
    public boolean less (int compare, int compareTo) {

        return compareTo(compare, compareTo) < 0;
    }

    // similar to insertionSort, but only counts inversions
    public void countInversions(int[] array) {

		for(int i = 0; i < array.length; i++)  {

			for(int j = i + 1; j < array.length; j++) {	

				if(array[i] > array[j]) {

					System.out.println("[" + i + "," + array[i] + "]" + ", " + "[" + j + "," + array[j] + "]");
					this.inversions++;
				}
			}
		}
        System.out.println();
	}
}