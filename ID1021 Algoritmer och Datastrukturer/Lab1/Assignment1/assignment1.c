#include <stdio.h>
#include <stdlib.h>

// Iterative method

void iterative () {

    int fixedLimit = 10;        // Set amount of allowed elements to 10
    char reverse [fixedLimit];  // Create an array with a fixed amount of elements
    int reverseCounter = 0;     // Counter for amount of chars inputted, necessary when assigning chars in reverse order
    char character;             // the character in question

    while((character = getchar()) != '\n') {    // As long as you don't press enter, stdin will not be aborted and getchar will read chars

        reverse[reverseCounter] = character;    // assigns all characters inputted to an array element
        reverseCounter++;                       // increment the counter
    }

    printf("Reversed chars: ");

    
    while (reverseCounter >= 0) {           // As long as the amount of elements left to reverse is not 0, the reversing process will continue
        putchar(reverse[reverseCounter]);   // The characters are put back into the array in reverse order
        reverseCounter--;                   // The counter is decremented until it reaches 0, when there are no more chars to output
    }

    printf("\n");
}


// Recursive method 

void recursive() {

    char character = getchar(); // Retrieve a character from stdin


   if(character == '\n')        // If enter is pressed, the method will print out the chars in reversed order
    {
        printf("Reversed chars: ");
    }
    else
    {
        recursive();            // If enter is not pressed, the next character will be retrieved by calling the function again
        putchar(character);     // When all characters have been retrieved, they will be outputted in reverse order
    } 
    
}

// Main method that calls on both methods. Comment out the one that you do not wish to use

int main () { 

    // Comment out this line for iterative
    printf("Iterative method. Enter characters to reverse: "); iterative();
 
    
    // Comment out this to line for recursive
    // printf("Recursive method. Enter characters to reverse: "); recursive(); printf("\n");
   
}