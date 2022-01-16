public class BellmanFordSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;

    public BellmanFordSP(Assignment6 G, int s)  {


        distTo = new double[G.V()]; 
        edgeTo = new DirectedEdge[G.V()]; 
        onQ = new boolean[G.V()];
        queue = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
        distTo[v] = Double.POSITIVE_INFINITY; 
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;

        while (!queue.isEmpty()) { 

            int v = queue.dequeue(); 
            onQ[v] = false; 
            relax(G, v);
        }
    }

    private void relax(Assignment6  G, int v) {

        for (DirectedEdge e : G.adj[v]) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (!onQ[w])    {

                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
        }
    }

    public double distTo(int v)
    {   return distTo[v];   }

    public boolean hasPathTo(int v)
    { return distTo[v] < Double.POSITIVE_INFINITY; }

    public Iterable<DirectedEdge> pathTo(int v){

        if (!hasPathTo(v)) return null;

        Stack<DirectedEdge> path = new Stack<DirectedEdge>();

        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e); 
            
        return path;
    }    
}