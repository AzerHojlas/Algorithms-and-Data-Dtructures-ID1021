#include <stdio.h>

int main () {

    // instantiate the array. Here you can input your own array elements
    int array [5] = {7, -2, 3, 4, -5};

    // Print the array
    printf("Array elements: ");
    for (int a = 0; a < (sizeof(array)/sizeof(array[0])); a++) {

        printf("%d\t", array[a]);
    }
    
    printf("\n");

    // temp variable for swapping
    int temp;

    // Sort the array. An element that is smaller than 0 will replace an element to the left of it
    for(int i = 0; i < (sizeof(array)/sizeof(array[0])); i++) {

        for(int j = i + 1; j < (sizeof(array)/sizeof(array[0])); j++) {

            if(array[j] < 0) {

                temp = array [i];
                array [i] = array [j];
                array [j] = temp;
                break;
            }
        }
    }

    // print the array
    printf("New array: ");
    for (int b = 0; b < (sizeof(array)/sizeof(array[0])); b++) {

        printf("%d\t", array[b]);
    }

    printf("\n");


    return 0;
}