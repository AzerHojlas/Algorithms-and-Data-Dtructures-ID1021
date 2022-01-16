// This class was more or less taken from the course litterature
public class a2 {

    private Node first = null;  // the first node or element is empty

    // create nodes
    private class Node {

        char character;
        Node next;
    }

    public boolean isEmpty () {
        return (first == null);
    }

    // push a node on to the stack
    public void push (char character) {

        Node oldfirst = first;  // create a new reference for the first old node
        first = new Node ();    // create a new node that will now be the first. New keyword allocates memory for the object
        first.character = character;    // assign a character to that new node
        first.next = oldfirst;          // make the new node point to the old one
    }
    // pop a node from the stack
    public char pop () {

        char character = first.character;   // retrieve the char of the first node
        first = first.next;                 // dereference the current node and make the current node the old one
        return character;                   // return the char that was in the original node
    }
    
}
