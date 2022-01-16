import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {

        // Create a list
	    DoublyCircularList <Integer> list = new LinkedStackOfChars <> ();
        list.print();
    }
    public static void init(List<Integer> list)
	{
		int cmd = 0;
		Integer inputData = null;
		
		do {
			cmd = getOperation();
			
			switch(cmd)
			{
				case 0:
					System.out.println();
					System.out.println("*****************");
					System.out.println("Program ended");
					System.out.println("*****************");
					return;
				case 1:
					inputData = getInputData();
					list.addNewFront(inputData);
					System.out.println();
					break;
				case 2:
					inputData = getInputData();
					list.addNewBack(inputData);
					System.out.println();
					break;
				case 3:
					list.removeFirst();
					System.out.println();
					break;
				case 4:
					list.removeLast();
					System.out.println();
					break;
				case 5:
					list.print();
					System.out.println();
					break;
				default:
					System.out.println("Invalid input");
					return;
			}	
		} while (true);
	}

    public static Integer getInputData()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Data to insert:\t\t\t");
		Integer input = Integer.parseInt(sc.next());
        
		
		return input;
	}

    public static void printMenu()
	{
		System.out.println("*****************");
		System.out.println("Menu of operations");
		System.out.println("*****************");

		System.out.println("0. Exit program");
		System.out.println("1. Add element at front of list ");
		System.out.println("2. Add element at end of list");
		System.out.println("3. Remove element from front of list");
		System.out.println("4. Remove element from end of list");
		System.out.println("5. Show content of list");
		System.out.println();
	}
}
