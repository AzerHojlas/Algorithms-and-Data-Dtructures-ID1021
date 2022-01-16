import java.util.Iterator;

public class Bag <Item> implements Iterable<Item> {

    private Node first;         // Instantiate first node. This node points to nothing

    private class Node  {   

        Item item;
        Node next; 
    }

    public void add(Item item)   {  

    Node oldfirst = first;                  // Point to the first with a new first
    first = new Node();                     // The old first becomes a new node which will take on the values in the method argument
    first.item = item;                      // The new item will be added to this new node
    first.next = oldfirst;                  // This new node will point to the old first
    }

    public Iterator<Item> iterator() {

        return new ListIterator();
    }
        

    private class ListIterator implements Iterator<Item> {

        private Node current = first;       // Node current points to first node

        public boolean hasNext()    {  

            return current != null;         // Checks if the current is null 
        }


        public void remove() { }

        public Item next() {

            Item item = current.item; 
            current = current.next; 
            return item;
        }
    }
}