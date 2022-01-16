@SuppressWarnings("unchecked")

public class DirectedDepthFirstPaths {

    private boolean[] marked;               // Array of visited vertices, initially set to false
    private int[] edgeTo;                   // Array that keeps track of from which vertex another vertex was visited
    private final int s;                    // Starting vertex

    // Constructor
    public DirectedDepthFirstPaths(Digraph graph, int s) {

        marked = new boolean[graph.V()];    // marked gets set to graph size
        edgeTo = new int[graph.V()];        // Same with edgeTo
        this.s = s;                         // S gets reaffirmed
        dfs(graph, s);                      // Begin search
    }

    // Searches for all the reachable vertices from S
    private void dfs(Digraph graph, int v) {

        marked[v] = true;                   // Mark vertex as visited
        for (int w : graph.adj(v))          // Iterate through all adjacent vertices
            if (!marked[w]) {               // If stumbled upon an unvisited

                edgeTo[w] = v;              // Register from which vertex that one has arrived to the unmarked
                dfs(graph, w);              // Visit all vertices from this adjacent
            }
        // Once a vertiex has been reached where all adjacents have been marked, the method will recursively fall back to it's for-
        // each statement until it has arrived back at the starting vertex. Now all possible paths are known for s
    }

    // Check if v is visitable from s, i.e if there is a path from s to v
    public boolean hasPathTo(int v) {  return marked[v];  }

    // Return all the vertices on the path to v from s
    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) return null;                 // If no path exists, return null

        Stack <Integer> path = new Stack<>();           // Instantiate stack for iteration

        for (int x = v; x != s; x = edgeTo[x])          // While v is not the original vertex, trace back from v from whence it came
            path.push(x);                               // push all these vertices in the path on to the stack
            
        path.push(s);                                   // Lastly, push the original vertex onto the stack
        return path;                                    // Return these vertices that are on the path
    }

}