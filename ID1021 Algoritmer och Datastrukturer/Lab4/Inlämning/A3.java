/*
    Author:         Azer Hojlas

    Date:           2021

    Description:    Symbol graph that uses a breadth first search algorithm that finds a way between two vertices A and B via C

    Dependancies:   edu.princeton.cs.algs4.In ---------- edu.princeton.cs.algs4.StdIn

    Compilation:    javac -cp .:algs4.jar A3.java

    Execution:      java -cp .:algs4.jar A3 INPUT.txt DELIMITER

    Usage:          Replace INPUT.txt with the file that should be read and
                    DELIMITER with how you wish to split each row in the input file. The result will be printed on the terminal
                    The client will be asked for a starting vertex and a destination vertex to which the program should find a path,
                    then an intermediate vertex through which this path, assuming that it exists, should run through. All this is done
                    by simply typing in the above mentioned vertices when the terminal asks for them.
                
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

@SuppressWarnings("unchecked")

public class A3 {

    private SequentialSearchST<String, Integer> symbolTable;
    private String[] keys;
    private Graph graph;

    public A3(String file, String delimiter) {

        symbolTable = new SequentialSearchST<>();               // Instantiate symbol table
        In in = new In(file);                                   // Instantiate input stream

        while (in.hasNextLine()) {                              // while the input has a next line

            String[] a = in.readLine().split(delimiter);        // create a String array of size 2 that is split by blank space

            for (int i = 0; i < a.length; i++)                  // Iterate through the two keys
                if (!symbolTable.contains(a[i]))                // If the symbol table doesn't contain the key, put it in
                    symbolTable.put(a[i], symbolTable.size());  // Put it in with the size of the symbol table
        }

        // This way above, each unique vertex will increment the symbol table size and will be assigned it's own value (current size)
        // By the end of the while loop, each vertex will have it's own integer representation

        
        keys = new String[symbolTable.size()];                   // Now we create a String array which will index all these keys with their values

        for (String name : symbolTable.keys())                   // Iterate through all the keys in the symbol table
            keys[symbolTable.get(name)] = name;                  // Each element will be assigned it's key counterpart

        graph = new Graph(symbolTable.size());                   // A integer graph will be created to represent the symbol graph
        in = new In(file);                                       // Read in the text file again

        while (in.hasNextLine()) {                               // while the input has a next line

            String[] a = in.readLine().split(delimiter);         // create a String array of size 2 that is split by blank space
            int v = symbolTable.get(a[0]);                       // Retrieve the corresponding index number of the first vertex

            for (int i = 1; i < a.length; i++)                   // Iterate only one step as a.length is 2 and i is 1
                graph.addEdge(v, symbolTable.get(a[i]));         // Add an edge between the first vertex and the second vertex
        }
    }

    public boolean contains(String s)   { return symbolTable.contains(s); }      // Checks if the graph has vertex s

    public int index(String s)  { return symbolTable.get(s); }                   // Returns the corresponding int of s

    public String name(int v)   { return keys[v]; }                              // Returns the corresponding string of v
    
    
    public void printPath() {                                                    // Print out the path if there is one

        BreadthFirstPaths searchToVia; 
        BreadthFirstPaths searchToDestination;                                    

        // Read in vertex source from terminal and calculate shortest paths
        System.out.print("\nFind a path from: ");
        String source = StdIn.readString();
        searchToVia = new BreadthFirstPaths(graph, index(source));

        // Read in destination vertex from terminal
        System.out.print("\nSearch for a path to: ");
        String destination = StdIn.readString();

        // Read in via which vertex to traverse
        System.out.print("\nVia: ");
        String via = StdIn.readString();

        // Check if a path exists between source and Via
        boolean hasPathVia = searchToVia.hasPathTo(index(via));

        // Create new BFS to find shortest path to destination if there is any. Confirm with hasPathTo
        searchToDestination = new BreadthFirstPaths(graph, index(via));

        // Check if a path exists between Via and Destination
        boolean hasPathTo = searchToDestination.hasPathTo(index(destination));

        // If path doesn't exist then state that it is so
        if (hasPathTo && hasPathVia) {

            // Confirm to the client that a path exists
            System.out.println("\nThe following path exists between " + source + " and " + destination + " via " + via + "\n");

            // Iterate through the path to via and print all vertices in it
            for (int vertex: searchToVia.pathTo(index(via)))  {
                System.out.println("\t\t\t" + name(vertex));
            }

            // Iterate through the path to the destination and print all vertices in it
            // Add an if statement in order to not print via twice
            for (int vertex: searchToDestination.pathTo(index(destination))) {

                if (vertex == index(via)) {
                    continue; 
                }
                
                System.out.println("\t\t\t" + name(vertex));  
            }
        }     
        else {
            System.out.println("\nNo such path between " + source + " and " + destination + " exists" + " via" + via + "\n");
            
        }
    }   

    public static void main(String[] args)  {

        // Enter what file to read and which delimiter to use to separate each line in the input file
        String filename = args[0];
        String delim = args[1];

        // Instantiate a symbol graph with the values from the terminal
        A3 symbolGraph = new A3(filename, delim);   

        // Ask questions and print said question results in an endless loop. Press ctrl+c to exit loop and program
        while (true) {

            symbolGraph.printPath();
            System.out.println("__________________________________________________________________");
        }
    }
}