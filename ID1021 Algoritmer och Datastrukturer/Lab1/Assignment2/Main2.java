import java.util.Scanner;

public class Main2 {

	// Create a stack
	private static LinkedStackOfChars stack = new LinkedStackOfChars();
	
	public static void main(String[] args)
	{		
		System.out.print("Input characters to reverse:\t");
		
		// Chars are entered from the user
		Scanner scanner	= new Scanner(System.in);				
		String input = scanner.next();							
		scanner.close();								
		
		// CharacherCounter is necessary for the recursive method
		int characterCounter = 0;

		// The input is converted to a char array
		char[] reverse = input.toCharArray();
		
		// call the recursive method
		recursive(reverse, characterCounter); System.out.print("\n");
		
		// Call the iterative method
		iterative(reverse);
	}	
	
	// Iterative method
	public static void iterative(char[] reverse) {		

		System.out.print("Reversed iteratively:\t\t");

		int i = 0;
		int j = reverse.length;

		// While the char array still has chars to be read, then the chars will be pushed onto the stack. This will stop when all the chars have been pushed
		while (i < reverse.length) { 
			
			stack.push(reverse[i]);
			i++;	
		}

		// All the characters will be returned in reverse order, how this functions see class LinkedStackOfChars
		while (j > 0) {
			
			System.out.print(stack.pop());
			j--;	
		}
		System.out.println();
	}
	
	// recursive method
	public static void recursive(char[] reverse, int characterCounter)
	{	
		// Print the following line after reversing the characters
		if(characterCounter == reverse.length)
		{
			System.out.print("Reversed recursively:\t\t");
		}
		else
		{	
			// using the characterCounter, all the chars are pushed onto the stack
			char character = reverse[characterCounter]; 
			stack.push(character);							
			characterCounter++;							// After each push, the counter gets incremented
			recursive(reverse, characterCounter);		// the recursive method is called again, but this time a new index is used to push another char
			System.out.print(stack.pop());				// After all characters have been pushed, we pop them in reverse order
		}
	}
}