public class Link <T> {

    private T data;
    public Link previous;
    public Link next;

    public Link (T data) {

        previous = null;
        this.data = data;
        next = null;
    }

    public Link (Link previous, T data, Link next) {

        this.previous = previous;
        this.data = data;
        this.next = next;

    }

    public T getData () {
        return this.data;
    }
}
