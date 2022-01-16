/*
    Author:         Azer Hojlas

    Date:           2021

    Description:    Symbol graph that uses a depth first search algorithm that finds out if vertex A is in a cycle

    Dependancies:   edu.princeton.cs.algs4.In ---------- edu.princeton.cs.algs4.StdIn

    Compilation:    javac -cp .:algs4.jar A5.java

    Execution:      java -cp .:algs4.jar A5 INPUT.txt DELIMITER

    Usage:          Replace INPUT.txt with the file that should be read and
                    DELIMITER with how you wish to split each row in the input file. The result will be printed on the terminal
                    The client will be asked for a starting vertex whereupon the program will search for a cycle containing said vertex.
                    Press ctrl+c to exit loop and program.

 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

@SuppressWarnings("unchecked")

public class A5 {

    private SequentialSearchST<String, Integer> symbolTable;
    private String[] keys;
    private Digraph graph;

    public A5(String stream, String sp) {

        symbolTable = new SequentialSearchST<>();                // Instantiate symbol table
        In in = new In(stream);                                  // Instantiate input stream

        while (in.hasNextLine()) {                               // while the input has a next line

            String[] a = in.readLine().split(sp);                // create a String array of size 2 that is split by blank space

            for (int i = 0; i < a.length; i++)                   // Iterate through the two keys
                if (!symbolTable.contains(a[i]))                 // If the symbol table doesn't contain the key, put it in
                    symbolTable.put(a[i], symbolTable.size());   // Put it in with the size of the symbol table
        }

        // This way above, each unique vertex will increment the symbol table size and will be assigned it's own value (current size)
        // By the end of the while loop, each vertex will have it's own integer representation

        
        keys = new String[symbolTable.size()];                   // Now we create a String array which will index all these keys with their values

        for (String name : symbolTable.keys())                   // Iterate through all the keys in the symbol table
            keys[symbolTable.get(name)] = name;                  // Each element will be assigned it's key counterpart

        graph = new Digraph(symbolTable.size());                 // A integer graph will be created to represent the symbol graph
        in = new In(stream);                                     // Read in the text file again

        while (in.hasNextLine()) {                               // while the input has a next line

            String[] a = in.readLine().split(sp);                // create a String array of size 2 that is split by blank space
            int v = symbolTable.get(a[0]);                       // Retrieve the corresponding index number of the first vertex

            for (int i = 1; i < a.length; i++)                   // Iterate only one step as a.length is 2 and i is 1
                graph.addEdge(v, symbolTable.get(a[i]));         // Add an edge between the first vertex and the second vertex
        }
        // Difference between DiGraph and BiGraph iss that the addEdge method for DiGraph only adds edge in one vertex adj list
    }

    public boolean contains(String s)   { return symbolTable.contains(s); }      // Checks if the graph has vertex s

    public int index(String s)  { return symbolTable.get(s); }                   // Returns the corresponding int of s

    public String name(int v)   { return keys[v]; }                              // Returns the corresponding string of v    
    
    public void printPath() {                                                    // Print out the path if there is one

            DirectedCycle search;                                                // Reference to a DFS

            // Read in vertex source from terminal and calculate possible paths
            System.out.print("\nFind a cycle with starting vertex: ");               
            String source = StdIn.readString(); 

            search = new DirectedCycle(graph, index(source));

            // If a cycle exists, proceed to print it
            if (search.hasCycle()) {

                System.out.println("\nThe following cycle exists that contains " + source + "\n");
    
                for (int vertex: search.cycle())
                        System.out.println("\t\t\t" + name(vertex));   
            }
            // If not, state that no cycle exists
            else 
                System.out.println("\nNo such cycle exists that contains " + source + "\n");       
    }


    public static void main(String[] args)  {

        // Enter what file to read and which delimiter to use to separate each line in the input file
        String filename = args[0];          
        String delim = args[1];

        // Instantiate a symbol graph with the values from the terminal
        A5 symbolGraph = new A5(filename, delim);

        // Ask questions and print said question results in an endless loop. Press ctrl+c to exit loop and program
        while (true) {

            symbolGraph.printPath();
            System.out.println("______________________________________________________________");
        }

    }
}