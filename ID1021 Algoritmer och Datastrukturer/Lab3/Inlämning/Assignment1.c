#include <stdio.h>
#include <ctype.h>

int main () {

    // Create a file that only reads in text
    FILE *input  = fopen("98-0.txt", "r"); 

    int input_size = 0;
    int output_size = 0;

    char character;

    // create a file to write the cleaned text to
    FILE *output = fopen("cleaned.txt", "w+"); 

    // Checks that the file exists and isn't empty
    if (input == NULL) 
        printf("File is not available \n");

    else {
        // Reads characters one by one
         while ((character = fgetc(input)) != EOF)   {

            // if the character is either alphabetical, blank space or new line it will be written to output file
            if (isalpha(character) || character == '\n' || character == ' ') {

                // write a accceptable character to output file
                fputc(character,output);
            }
            // Write a blank space to new file if unacceptable character
           else fputc(' ',output);
        }        
    }
    rewind(input);
    rewind(output);

    // Check if their sizes are equal
    while ((character = fgetc(input)) != EOF) 
        input_size++;

    while ((character = fgetc(output)) != EOF)
        output_size++;

    // Check if their sizes are equal
    printf("Input size: %d\n", input_size);
    printf("Output size: %d\n", output_size);

    return 0;
}