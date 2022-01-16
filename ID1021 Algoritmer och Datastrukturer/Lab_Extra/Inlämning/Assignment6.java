/******************************************************************************
 *  @author:      Azer Hojlas
 *  @Date:        04/10-2021 
 *  Compilation:  javac -cp .:algs4.jar Assignment6.java
 *  Execution:    java -cp .:algs4.jar Assignment6 graph
 *  Dependencies: Insertion.java >>>>>> Quick3way.java >>>>>> KeyValue.java
 *  Data files:   none
 *  Usage:        Run the programe with the execution statement above, where graph is the graph you want to perform operations on
 *               
 *                
 *  Description:  Program that finds the path between two vertices possibly going through an intermediate vertex
 ******************************************************************************/

import java.io.File;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

@SuppressWarnings("unchecked")

public class Assignment6  {

    private final int V;
    private int E;
    public Bag<DirectedEdge>[] adj;

    /**
     * Constructor that creates a digraph from a inputted text file
     * @param in that contains said text file
     */
    public Assignment6 (In in) {

        if (in == null) throw new IllegalArgumentException("argument is null");

        this.V = in.readInt();

        if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be non-negative");
        
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }

        int E = in.readInt();

        if (E < 0) throw new IllegalArgumentException("Number of edges must be non-negative");

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public int V() {  
        
        return V;  
    }

    public int E() {

         return E;  
    }
    // adds an edge to the adjacency bag
    public void addEdge(DirectedEdge edge) {

        int v = edge.from();

        adj[v].add(edge);

        E++;
    }

    public void printPath(Assignment6 graph) {                       // Print out the path if there is one

        BellmanFordSP searchToVia;              // Instantiate a DFS for the source vertex
        BellmanFordSP searchToDestination;      // Instantiate a DFS for the via vertex                                

        // Read in vertex source from terminal and calculate shortest paths
        System.out.print("\nFind a path from: ");
        int source = StdIn.readInt();
        searchToVia = new BellmanFordSP(graph, source);

        // Read in destination vertex from terminal
        System.out.print("\nSearch for a path to: ");
        int destination = StdIn.readInt();

        // Ask if the client wishes to find the path through a specified vertex
        System.out.print("\nFind a path via another vertex? Press y for yes and n for no: ");
        String option = StdIn.readString();

        // Instantiate possible middle man vertex
        int via;

        // If they want to traverse through a intermediate vertex
        if (option.equals("y")) {

            // Read in said vertex
            System.out.print("\nVia: ");
            via = StdIn.readInt();

            // Is there a path to the intermediate vertex from the source
            boolean hasPathVia = searchToVia.hasPathTo(via);

            // Create new BFS to find shortest path to destination if there is any. Confirm with hasPathTo
            searchToDestination = new BellmanFordSP(graph, via);
            boolean hasPathTo = searchToDestination.hasPathTo(destination);

            // If path to intermediate vertex from source exists and likewise from intermediate to destination, the print the path
            if (hasPathTo && hasPathVia) {

                // Confirm to the client that a path exists
                System.out.println("\nThe following path exists between " + source + " and " + destination + " via " + via + "\n");

                // Iterate through the path to via and print all vertices in it
                for (DirectedEdge vertex: searchToVia.pathTo(via)) {

                    System.out.println("\t\t\t" + vertex.from());
                }

                // Iterate through the path to the destination and print all vertices in it
                // Add an if statement in order to not print via twice
                for (DirectedEdge vertex: searchToDestination.pathTo(destination)) {

                    // Print out the path
                    System.out.println("\t\t\t" + vertex.from());  

                    if (vertex.to() == destination)
                        System.out.println("\t\t\t" + vertex.to());
                }

                System.out.printf("%nTotal travel cost is %.0f units %n", searchToVia.distTo(via) + searchToDestination.distTo(destination));
            }
            // If paths do not exist, state that it is so
            else System.out.println("\nNo such path between " + source + " and " + destination + " exists" + " via" + via + "\n");
        
        }  
        else {

            // Is there a path from the source to the destination
            boolean hasPathTo = searchToVia.hasPathTo(destination);

            // If said path exists, proceed to print it
            if (hasPathTo) {

                // Confirm to the client that a path exists
                System.out.println("\nThe following path exists between " + source + " and " + destination + "\n");

                // Print the path
                for (DirectedEdge vertex: searchToVia.pathTo(destination)) {

                    System.out.println("\t\t\t" + vertex.from());

                    if (vertex.to() == destination)
                        System.out.println("\t\t\t" + vertex.to());
                }

                System.out.printf("%nTotal travel cost is %.0f units %n", searchToVia.distTo(destination));
            }
            // If path does not exist, state that it is so
            else {
                System.out.println("\nNo such path between " + source + " and " + destination + "\n");
            }
        }
    }   

    public static void main(String[] args)  {

        // String filename = "NYC.txt"; 

        // Enter what file to read 
        String filename = args[0];
        File file = new File(filename);
        In in = new In (file);

        // Instantiate a digraph with the file name inputted from the terminal
        Assignment6  diGraph = new Assignment6(in);
    
        // Ask questions and print said question results in an endless loop. Press ctrl+c to exit loop and program
        while (true) {
            diGraph.printPath(diGraph);
            System.out.println("__________________________________________________________________");
        }
    }
}