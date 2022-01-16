import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>  {

    private Node first;                     // link to least recently added node
    private Node last;                      // link to most recently added node
    private int N;                          // number of items on the queue

    private class Node  {                   // nested class to define nodes
        Item item;
        Node next; 
    }

    public boolean isEmpty() {              

        return first == null; 
    } 

    public void enqueue(Item item)  {       // Add item to the end of the list.

        Node oldlast = last;                // Oldlast points to current last
        last = new Node();                  // Current last becomes a new node
        last.item = item;                   // The new last gets assigned the item value
        last.next = null;                   // last points to nothing

        if (isEmpty())                      // if the queue is empty then first is also the last
            first = last;

        else oldlast.next = last;           // oldlast next (oldlast is now second last) points to new last
        N++;
    }
    public Item dequeue()   {               // Remove item from the beginning of the list.

        Item item = first.item;             // Dequeue first
        first = first.next;                 // first becomes second first

        if (isEmpty())                      // if empty then last is null
            last = null; 
        N--;
        return item;                        // return item from first
    }
    
    public Iterator <Item> iterator() {  

        return new ListIterator();
    }
        

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() { 

            return current != null;  
        }

        // Irrelevant, kan ta bort
        public void remove() { }

        // Interfacet kräver implementering
        public Item next() {

            Item item = current.item; 
            current = current.next; 
            return item;
        }
    }
}
