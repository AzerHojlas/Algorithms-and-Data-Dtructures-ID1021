public class DoubleLinkedList {

    private Node first = null;  

    private class Node {

        char character;
        Node next;
        Node previous;
    }

    public boolean isEmpty () {
        return (first == null);
    }

    public void push (char character) {

        Node oldfirst = first;  
        first = new Node ();    
        first.character = character;    
        first.next = oldfirst;     
        oldfirst.previous = first;    
        first.previous = null;
    }

    public char pop () {

        char character = first.character;   
        first = first.next;                 
        return character;                   
    }
}
