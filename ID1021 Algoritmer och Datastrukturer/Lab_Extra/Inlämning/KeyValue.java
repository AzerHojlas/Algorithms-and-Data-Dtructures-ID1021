public class KeyValue implements Comparable<KeyValue> {

    private String s;
    private Integer x;
    
    KeyValue(String s, Integer x) {

        this.s = s;
        this.x = x;
    }

    @Override
    public int compareTo(KeyValue that) {
        			
        if(this.x.compareTo(that.x) < 0) return -1;
        if(this.x.compareTo(that.x) > 0) return +1;
        return 0;
    }

    @Override
    public String toString() {

        
        return (s + " " + x + "\n");
    }
}


