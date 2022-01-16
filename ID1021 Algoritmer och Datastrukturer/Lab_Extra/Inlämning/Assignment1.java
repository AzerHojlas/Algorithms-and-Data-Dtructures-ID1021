/******************************************************************************
 *  @author:      Azer Hojlas
 *  @Date:        04/10-2021 
 *  Compilation:  javac -cp .:algs4.jar Assignment1.java
 *  Execution:    java -cp .:algs4.jar Assignment1. Change the main method for desiredinput
 *  Dependencies: none
 *  Data files:   none
 *
 *  A doubly linked circular list
 * 
 ******************************************************************************/

import java.util.Iterator;

public class Assignment1 <Item extends Comparable<Item>> implements Iterable<Item> { 

    private Node sentinel;

    private class Node  {

        Item item;
        Node next; 
        Node previous;
    }

    public Assignment1() {

		sentinel = new Node();
		sentinel.previous = sentinel;
		sentinel.next = sentinel;
	}

    public void insertLast(Item item) {

        Node newLast = new Node();              // Create a new node
		newLast.item = item;                    // Assign the empty new node a value

        
        newLast.next = sentinel;                // Point the new last node to the sentinel      
        newLast.previous = sentinel.previous;   // Point the old last to the new last
        newLast.next.previous = newLast;        // Point from sentinel to new last
        newLast.previous.next = newLast;        // point from old last to new last
    }

    public void insertFirst (Item item) {

        Node newFirst = new Node();             // Create a new node
		newFirst.item = item;                   // Assign the empty new node a value

        newFirst.previous = sentinel;           // Point the new first to the sentinel
        newFirst.next = sentinel.next;          // Point the new first to the old first
        newFirst.previous.next = newFirst;      // Point the sentinel to the new first
        newFirst.next.previous = newFirst;      // Point the old first to the new first
    }

    public Item removeFirst () {

        Item item = sentinel.next.item;         // Retrieve first item

        // Sever all connections to the first one
        sentinel.next = sentinel.next.next;     
		sentinel.next.previous = sentinel;

        return item;                            // Return the item
    }

    public Item removeLast () {

        Item item = sentinel.previous.item;     // Retrieve last item

        // Sever all connections to the last one
        sentinel.previous = sentinel.previous.previous;
		sentinel.previous.next = sentinel;

        return item;                            // Return the item
    }

    public void insertOrdered (Item item) {

        Node newNode = new Node();              // Create a new node
        newNode.item = item;                    // Assign value to new node
        newNode.next = sentinel.next;           // Connect the next of the new node to the first in the list

        // The new node iterates forward if it is biggerthan the one next to it, until it becomes smaller, equal or reaches the end, i.e the sentinel
        while (newNode.next != sentinel && newNode.item.compareTo(newNode.next.item) > 0)
            newNode.next = newNode.next.next;
        
        newNode.previous = newNode.next.previous;   // "Squeeze in the new node between two pre-existing"
        newNode.next.previous = newNode;        // Connect the comparably bigger or sentinel to its right with said node
        newNode.previous.next = newNode;        // Connect its smaller previous one with said node
    }

    // Iterator
    public Iterator<Item> iterator() {  return new ListIterator();  }
        
    private class ListIterator implements Iterator<Item> {

        private Node current = sentinel;

        public boolean hasNext() { return current.next != sentinel; }

        public Item next() {
            current = current.next; 
            return current.item;
        }
    }
        public static void main(String[] args) {
        
        Assignment1 <Integer> test = new Assignment1<>();

        test.insertFirst(2);
        test.insertLast(4);
        test.insertOrdered(1);
        test.insertOrdered(3);
        test.insertOrdered(4);
        test.insertOrdered(8);
        test.insertOrdered(5);


        for (int s: test)
            System.out.println(s);

    }
}
