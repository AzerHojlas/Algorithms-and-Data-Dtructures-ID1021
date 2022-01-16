@SuppressWarnings("unchecked")

public class Digraph {


    private final int V;                        // Amount of vertices
    private int E;                              // Amount of edges
    private Bag<Integer>[] adj;                 // Array of adjacent vertices

    // Create an empty Graph with V vertices
    public Digraph(int V)   {                   

        this.V = V;
        this.E = 0;

        // Instantiate bag with V vertices
        adj = (Bag<Integer>[]) new Bag[V]; 

        // Each vertex gets a bag of adjacents
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    // Return amount of vertices
    public int V()  {  return V;  }  
    
    // Return amount of edges
    public int E()  {  return E;  }     

    // Create virtual edges between already adjacent vertices
    public void addEdge(int v, int w) {

        adj[v].add(w);                  // Edge is only added to one of the adjacent arrays, instead of two, in effect making it a DiGraph
        E++;                            // Increment edges
    }

    // Returns the adjacent vertices of a vertex as an iterable 
    public Iterable<Integer> adj(int v) {  
        return adj[v];  
    }

}