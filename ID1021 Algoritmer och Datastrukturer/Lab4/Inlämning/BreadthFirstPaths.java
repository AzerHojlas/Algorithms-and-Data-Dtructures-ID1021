public class BreadthFirstPaths {

    private boolean[] marked;                           // Reference to boelean array of visited vertices
    private int[] edgeTo;                               // Reference to int array that traces back a path
    private final int s;                                // Source vertexx

    public BreadthFirstPaths(Graph graph, int s)    {   // Constructor with int graph and source vertex

        marked = new boolean[graph.V()];                // Instantiate marked array with graph size
        edgeTo = new int[graph.V()];                    // Instantiate edgeTo array with graph size
        this.s = s;                                     // Assign value to source vertex
        bfs(graph, s);                                  // Call bfs method with graph and vertex source
    }

    private void bfs(Graph graph, int s) {              // Organize all vertices with shortest path

        Queue<Integer> queue = new Queue<>();           // Instantiate a queue
        marked[s] = true;                               // Mark the vertex source as visited
        queue.enqueue(s);                               // Enqueue the vertex source as visited

        while (!queue.isEmpty()) {                      // While the queue isn't empty, and it will not be empty until all vertices are processed

            int v = queue.dequeue();                    // Dequeue the vertex source and assign its int value to v

            for (int w : graph.adj(v))                  // Retrieve all adjacent vertices of v and iterate through them
                if (!marked[w]) {                       // If a iteration has already been traveled to, proceed to next adjacent

                    edgeTo[w] = v;                      // Otherwise, assign v to its visited value and...
                    marked[w] = true;                   // Mark w as visited
                    queue.enqueue(w);                   // Enqueue w for later processing, first in line if second visited vertex
                }
        }
    }   // This way, paths will not be dependent on the bag implementation and recursion, but instead each adjacent vertices to
        // the current one will be marked as visited and indexed instead of a potential long path like in the DFS
    
    public boolean hasPathTo(int v) {

        return marked[v];                               // If v can be visited from the vertex source,
    }                                                   // then it should be marked as true or the opposite if that is not the case
                                                       
    public Iterable<Integer> pathTo(int v) {            // Method that returns an iterable of paths from a destination to a source       

        if (!hasPathTo(v)) return null;                 // If no path exists, return null
        
        Stack<Integer> path = new Stack<>();            // Instantiate stack for iteration

        for (int x = v; x != s; x = edgeTo[x])          // While v is not the original vertex, trace back from v from whence it came
            path.push(x);                               // push all these vertices in the path on to the stack
            
        path.push(s);                                   // Lastly, push the original vertex onto the stack
        return path;                                    // Return these vertices that are on the path
    }

}