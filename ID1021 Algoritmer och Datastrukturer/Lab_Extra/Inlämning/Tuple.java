public class Tuple implements Comparable<Tuple> {

    private String s;
    private Integer x;
    
    Tuple(String s, Integer x) {

        this.s = s;
        this.x = x;
    }

    Tuple(Character letter, Integer x) {

        this.s = Character.toString(letter);
        this.x = x;
    }

    @Override
    public int compareTo(Tuple that) {
        			
        if(this.s.compareTo(that.s) < 0) return -1;
        if(this.s.compareTo(that.s) > 0) return +1;
        if(this.x.compareTo(that.x) < 0) return -1;
        if(this.x.compareTo(that.x) > 0) return +1;
        return 0;
    }

    @Override
    public String toString() {

        
        return ("\n" + s + " " + x);
    }
}