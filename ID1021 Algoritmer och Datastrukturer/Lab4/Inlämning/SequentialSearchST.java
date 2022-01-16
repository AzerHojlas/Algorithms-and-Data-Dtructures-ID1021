public class SequentialSearchST <Key, Value> {

    private Node first;

    private int n = 0;

    // linked-list 
    private class Node  {

        // first node in the linked list
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next)  {   

            this.key = key; 
            this.val = val; 
            this.next = next;
        }   
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    
    public Value get(Key key)   { // Search for key, 

        if (key == null) throw new IllegalArgumentException("argument to get() is null");

        // Last always points to null, so that is our indicator of the end
        for (Node x = first; x != null; x = x.next)        
            if (key.equals(x.key))
                return x.val;
        
        return null;
    }

                   
    public void put(Key key, Value val) {

        // Iterate through list and add value if key already exists, otherwise we iterate to the end
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))  {

                x.val = val; 
                return; 
            } 
        }

        // If key does not exist, then we create a new first where this key value pair is added
        first = new Node(key, val, first);
        n++;
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

    public int size() {
        return n;
    }
}