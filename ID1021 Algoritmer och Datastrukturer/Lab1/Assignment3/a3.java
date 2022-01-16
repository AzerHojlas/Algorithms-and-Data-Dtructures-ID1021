public class a3 <T>
{
    private class Node
    {
    	Node next;		
    	Node previous;
        T data;
        
    	Node(T data)
    	{
    		this.data = data;
    	}
    }
    
	// the empty head node is not considered a node, but instead used to keep track of where in the queue we are
	private Node head = new Node(null);
	private int count; // the count is only incremented when non head nodes are added
	
	public FifoQueue()
	{
		count = 0;				// the count is only incremented when non head nodes are added
		head.next = head;		// The head is connected to itself as it is the only node in the queue
		head.previous = head;	// The head is connected to itself as it is the only node in the queue
	}
	
	// enqueue into the queue
	public void enqueue(T data)
	{
		Node newNode = new Node(data);	// a new node with the enqueueed data is created

		// If there is no other node besides the head, then we connect them to eachother
		if(count == 0)					
		{
			head.next = newNode;		// heads next points to the newNode
			head.previous = newNode;	// heads previous points to the ned node
			
			newNode.next = head;		// Same as with head
			newNode.previous = head;	// Same as with head
		}
		// It is a tad bit more complicated if other nodes are present
		else
		{
			newNode.next = head.next;			// The newNode's next points to what used to be the heads last next, making it the last node in the queue
			newNode.previous = head;			// The newNode is made to be the new tail
			
			newNode.next.previous = newNode;	// newNode is connected to the tail that was the newest before itself
			head.next = newNode;				// The head connects to the new tail
		}
		count++;	// The counter is incremented
		print();	// Print as per instruction
	}
	
	// dequeue
	public void dequeue() {

		// If only one other element besides head is present, then their ties get severed as is evident
		if(count == 1)
		{
			head.next = head;
			head.previous = head;
		}
		// If only the head is present, then we abort as we do not want to remove the head
		else if(count == 0)
		{
			System.exit(1);
		}
		// If there are other nodes present, then we do the following
		else
		{
			head.previous = head.previous.previous;		// The heads previous skips its immediate neighbour and attaches to the next one
			head.previous.next = head;					// The same new previous gets attached to the head
		}
		
		count--;	// the counter is decremented as there now is one less node to worry about
		print();	// print as per instruction
	}
	
	// the print method iterates through all nodes and prints them
	public void print()
	{
		Node iterate = head.previous;
		for (int i = 0; i < count; i++) {

			System.out.print("[" + iterate.data + "] , ");
			iterate = iterate.previous; 						// advance to the next node
		}
		System.out.println();
	}   

}