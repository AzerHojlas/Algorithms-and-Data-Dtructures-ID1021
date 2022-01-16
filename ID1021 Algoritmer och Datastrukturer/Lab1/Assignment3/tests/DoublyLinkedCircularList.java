public class DoublyLinkedCircularList <T> {

    private Link head;
    private int size;

    public DoublyLinkedList () {
        head = null;
        size = 0;
    }

    public void addFront (T data) {

        if (head == null)
            head = new Link <T> (null, data, null);
        
        else {
            Link newLink = new Link <T> (null, data, head);
            head.previous = newLink;
            head = newLink;
        }
        size++;
    }

    public void addRear (T data) {
        if (head == null)
            head = new Link <T> (null, data, null);

        else {
            Link current = head;
            while (current.next != null)
                current = current.next;
            Link newLink = new Link <T> (current, data, null);
            current.next = newLink;
        } 
       size++;
    }

    public void removeFront () {

        if (head == null) return;

        head = head.next;
        head.previous = null;
        size--;
    }

    public void removeRear () {
        if (head == null) return;

        if (head.next == null) {
            head = null;
            size--;
            return;
        }

        Link current = head;

        while (current.next.next != null)
            current = current.next;

        current.next = null;
        size--;

    }

    public int getSize () {
        return size;
    }

    public void print () {
        Link current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.next;
        } 
    }

    public boolean isEmpty () {
        return head == null;
    }
    public static void main (String [] args) {

        DoublyLinkedList <Integer> dll = new DoublyLinkedList<>();

        dll.addFront(3);
        dll.print();

        /* dll.addFront('h');
        dll.addFront('e');
        dll.addFront('j');
        dll.print();
        
        System.out.println("---------------");

        dll.addRear('h');
        dll.addRear('e');
        dll.addRear('j');
        dll.addRear('!');
        dll.print();

        System.out.println("--------------");

        dll.removeFront();
        dll.removeFront();
        dll.removeFront();
        dll.print();

        System.out.println("--------------");

        dll.removeRear();
        dll.print();
        */
    }

}