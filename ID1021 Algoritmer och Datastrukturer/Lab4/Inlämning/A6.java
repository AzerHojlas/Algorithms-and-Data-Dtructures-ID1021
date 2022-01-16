/*
    Author:         Azer Hojlas

    Date:           2021

    Description:    Symbol graph that uses a depth first search algorithm that finds out if vertex A is connected to C possibly via B

    Dependancies:   edu.princeton.cs.algs4.In ---------- edu.princeton.cs.algs4.StdIn

    Compilation:    javac -cp .:algs4.jar A6.java

    Execution:      java -cp .:algs4.jar A6 INPUT.txt 

    Usage:          Replace INPUT.txt with the file that should be read. The result will be printed on the terminal.
                    The client will be asked for a starting vertex, a destination vertex and if the option is chosen,
                    an intermediate vertex. The program will then find and print the shortest path from A to C via (possibly) B. 
                    Press ctrl+c to exit loop and program
 */

import java.io.File;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

@SuppressWarnings("unchecked")

public class A6 {


    private final int V;                        // Amount of vertices
    private int E;                              // Amount of edges
    private Bag<Integer>[] adj;                 // Array of adjacent vertices

    // Create an empty Graph with V vertices
    public A6(int V)   {                   

        this.V = V;
        this.E = 0;

        // Instantiate bag with V vertices
        adj = (Bag<Integer>[]) new Bag[V]; 

        // Each vertex gets a bag of adjacents
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public A6(In in) {

        if (in == null) throw new IllegalArgumentException("argument is null");

        try {
            // Assign amount of vertices at the top
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be non-negative");

            // instantiate bag array
            adj = (Bag<Integer>[]) new Bag[V];

            // instantiate bags in array
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<>();
            }

            // Assign amount of edges
            int E = in.readInt();

            if (E < 0) throw new IllegalArgumentException("number of edges in a Digraph must be non-negative");

            // Create Edges between all vertices
            for (int i = 0; i < E; i++) {

                int v = in.readInt();
                int w = in.readInt();
                int useless = in.readInt();         // Useless int to read in the third integer in each line, will not be assigned
                addEdge(v, w); 
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
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
    public Iterable<Integer> adj(int v) 
    {  return adj[v];  }

    public void printPath(A6 graph) {                       // Print out the path if there is one

        DirectedBreadthFirstPaths searchToVia;              // Instantiate a DFS for the source vertex
        DirectedBreadthFirstPaths searchToDestination;      // Instantiate a DFS for the via vertex                                

        // Read in vertex source from terminal and calculate shortest paths
        System.out.print("\nFind a path from: ");
        int source = StdIn.readInt();
        searchToVia = new DirectedBreadthFirstPaths(graph, source);

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
            searchToDestination = new DirectedBreadthFirstPaths(graph, via);
            boolean hasPathTo = searchToDestination.hasPathTo(destination);

            // If path to intermediate vertex from source exists and likewise from intermediate to destination, the print the path
            if (hasPathTo && hasPathVia) {

                // Confirm to the client that a path exists
                System.out.println("\nThe following path exists between " + source + " and " + destination + " via " + via + "\n");

                // Iterate through the path to via and print all vertices in it
                for (int vertex: searchToVia.pathTo(via)) 
                    System.out.println("\t\t\t" + vertex);

                // Iterate through the path to the destination and print all vertices in it
                // Add an if statement in order to not print via twice
                for (int vertex: searchToDestination.pathTo(destination)) {

                    // Skip an iteration in order for the intermediate vertex to not be printed twice
                    if (vertex == via) {
                        continue; 
                    }

                    // Print out the path
                    System.out.println("\t\t\t" + vertex);  
                }
            }
            // If paths do not exist, state that it is so
            else 
                System.out.println("\nNo such path between " + source + " and " + destination + " exists" + " via" + via + "\n");
        
        }  
        else {

            // Is there a path from the source to the destination
            boolean hasPathTo = searchToVia.hasPathTo(destination);

            // If said path exists, proceed to print it
            if (hasPathTo) {

                // Confirm to the client that a path exists
                System.out.println("\nThe following path exists between " + source + " and " + destination + "\n");

                // Print the path
                for (int vertex: searchToVia.pathTo(destination)) {
                    
                    System.out.println("\t\t\t" + vertex);
                }
            }
            // If path does not exist, state that it is so
            else {
                System.out.println("\nNo such path between " + source + " and " + destination + "\n");
            }
        }
    }   
    
    public static void main(String[] args)  {

        // Enter what file to read 
        String filename = args[0];
        File file = new File(filename);
        In in = new In (file);

        // Instantiate a digraph with the file name inputted from the terminal
        A6 diGraph = new A6(in);

        System.out.println(diGraph.E());
    
        // Ask questions and print said question results in an endless loop. Press ctrl+c to exit loop and program
        while (true) {
            diGraph.printPath(diGraph);
            System.out.println("__________________________________________________________________");
        }
    }
}