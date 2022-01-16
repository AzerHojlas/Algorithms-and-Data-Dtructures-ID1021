public class DirectedCycle  {

    private boolean[] marked;                                // marked[v] = has vertex v been marked?
    private int[] edgeTo;                                    // edgeTo[v] = previous vertex on path to v
    private Stack<Integer> cycle = new Stack<>();            // directed cycle (or null if no such cycle)
    private boolean[] onStack;                               // onStack[v] = is vertex on the stack?
    private int source;                                      // Vertex source used for comparison 

     public DirectedCycle(Digraph graph, int v)    {


        onStack = new boolean[graph.V()];
        edgeTo = new int[graph.V()]; 
        marked = new boolean[graph.V()]; 
        source = v;

        dfs(graph, v);
    }

    // run DFS and find a directed cycle (if one exists)
    // The DFS only searches for ONE path. If one is found then the program will terminate
    // 
    private void dfs(Digraph graph, int v) {

        onStack[v] = true;
        marked[v] = true;

        for (int w : graph.adj(v)) {
            
            // If unmarked, it will visit all reachable vertices from there
            if (!marked[w])    {  
                edgeTo[w] = v; 
                dfs(graph, w);  
            }

            // If we reach this stage, then we have a cycle and we add all the vertices to the stack
            // If the adjacent vertex has been visited, but we have not yet returned to its method call, i.e we are still on the
            // inside of the recursive method that was called with the source, then we have a cycle and we put the vertices on the stack
            else if (onStack[w])    {

                // Count amount of pushes
                int n = 0;

                // push cycle on to stack
                for (int x = v; x != w; x = edgeTo[x])  {
                    cycle.push(x); 
                    n++;

                }

                cycle.push(w); 
                cycle.push(v);
                n = n+2;

                int c = 0;
                boolean p = false;

                // Check the pushed cycle if it contains desired vertex
                while (n > 0) {

                    c = cycle.pop();

                    if (c == source)
                        p = true;
                    
                    n--;
                }
                // If it contains the desired vertex, create a new stack and push it on to that
                // A new stack is created in case there are several cycles with desired vertex
                if (p) {

                    cycle = new Stack<>(); 

                    for (int x = v; x != w; x = edgeTo[x])  {
                        cycle.push(x); 
    
                    }
    
                    cycle.push(w); 
                    cycle.push(v);
                }
               
            }
        }

        // Once all reachable vertices from the vertex source have been reached and there is no cycle, then we mark this one
        // as not being in a cycle, i.e onStack = false.
        onStack[v] = false;

    }

    public boolean hasCycle()   {  
        return !cycle.isEmpty();  
    }



    public Iterable<Integer> cycle()    {  
        return cycle;  
    }
}