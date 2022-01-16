@SuppressWarnings("unchecked")

public class Graph {

     private final int V;                // Amount of vertices
     private int E;                      // Amount of edges
     private Bag<Integer>[] adj;         // Bag to hold adjacent vertices

     // Create an empty Graph with V vertices
     public Graph(int V) {

        this.V = V; 
        this.E = 0;
        
        // Instantiate bag with V vertices
        adj = (Bag<Integer>[]) new Bag[V]; 

        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();   // Each vertex gets a bag of adjacents
    }

    public int V()  {  return V;  }

    public int E()  {  return E;  }

    // Create virtual edges between already adjacent vertices
    public void addEdge(int v, int w)   {

        adj[v].add(w);         // The bag for that vertex adds the integer to its list of adjacent vertices
        adj[w].add(v);         // The same is done here
        E++;                   // increment edges
    }

   // Returns the adjacent vertices of a vertex as an iterable 
   public Iterable<Integer> adj(int v) {  

       return adj[v];  
    }
}

