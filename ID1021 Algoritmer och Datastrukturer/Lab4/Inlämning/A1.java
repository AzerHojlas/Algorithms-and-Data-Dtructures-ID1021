/*
    Author:         Azer Hojlas

    Date:           2021

    Description:    Symbol graph that uses a depth first search algorithm that finds a way between two vertices A and B

    Dependancies:   edu.princeton.cs.algs4.In  ---------- edu.princeton.cs.algs4.StdIn

    Compilation:    javac -cp .:algs4.jar A1.java

    Execution:      java -cp .:algs4.jar A1 INPUT.txt DELIMITER

    Usage:          Replace INPUT.txt with the file that should be read and
                    DELIMITER with how you wish to split each row in the input file. The result will be printed on the terminal
                    Press ctrl+c to exit loop and program.
                    The client will be asked for a starting vertex and a destination vertex to which the program should find a path.
                    The client proceeds by simply typing in the above mentioned vertices when the terminal asks for them.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

@SuppressWarnings("unchecked")

public class A1 {

    private SequentialSearchST <String, Integer> st;
    private String[] keys;
    private Graph graph;

    public A1(String stream, String sp) { 

        st = new SequentialSearchST<>();                // Instantiate symbol table
        In in = new In(stream);                         // Instantiate input stream

        while (in.hasNextLine()) {                      // while the input has a next line

            String[] a = in.readLine().split(sp);       // create a String array of size 2 that is split by blank space

            for (int i = 0; i < a.length; i++)          // Iterate through the two keys
                if (!st.contains(a[i]))                 // If the symbol table doesn't contain the key, put it in
                    st.put(a[i], st.size());            // Put it in with the size of the symbol table
        }

        // This way above, each unique vertex will increment the symbol table size and will be assigned it's own value (current size)
        // By the end of the while loop, each vertex will have it's own integer representation

        
        keys = new String[st.size()];                   // Now we create a String array which will index all these keys with their values

        for (String name : st.keys())                   // Iterate through all the keys in the symbol table
            keys[st.get(name)] = name;                  // Each element will be assigned it's key counterpart

        graph = new Graph(st.size());                       // A integer graph will be created to represent the symbol graph
        in = new In(stream);                            // Read in the text file again


        while (in.hasNextLine()) {                      // while the input has a next line

            String[] a = in.readLine().split(sp);       // create a String array of size 2 that is split by blank space
            int v = st.get(a[0]);                       // Retrieve the corresponding index number of the first vertex
           
            for (int i = 1; i < a.length; i++)          // Iterate only one step as a.length is 2 and i is 1
                graph.addEdge(v, st.get(a[i]));             // Add an edge between the first vertex and the second vertex
        }
    }

    public boolean contains(String s)   { return st.contains(s); }      // Checks if the graph has vertex s

    public int index(String s)  { return st.get(s); }                   // Returns the corresponding int of s

    public String name(int v)   { return keys[v]; }                     // Returns the corresponding string of v
     
    
    public void printPath() {       // Print out the path if there is one

        DepthFirstPaths search;                   // Reference to a DFS

        // Read in vertex source from terminal and calculate possible paths
        System.out.print("\nFind a path from: ");               
        String source = StdIn.readString();
        search = new DepthFirstPaths(graph,index(source));

        // Read in destination vertex from terminal and call method to confirm that there exists a path
        System.out.print("\nSearch for a path to: ");
        String to = StdIn.readString();
        boolean hasPath = search.hasPathTo(index(to));

        // If path exists then state that it is so
        if (hasPath) {

            // Confirm to the client that a path exists
            System.out.println("\nThe following path exists between " + source + " and " + to + "\n");

            // Iterate through the path and print all vertices in it
            for (int vertex: search.pathTo(index(to))) 
                System.out.println("\t\t\t" + name(vertex));
        }
        else {
            System.out.println("\nNo such path between " + source + " and " + to + " exists\n");
        }
    }

    public static void main(String[] args)  {

        // Enter what file to read and which delimiter to use to separate each line in the input file
        String filename = args[0];          
        String delim = args[1];

        // Instantiate a symbol graph with the values from the terminal
        A1 symbolGraph = new A1(filename, delim);
        
        // Ask questions and print said question results in an endless loop. Press ctrl+c to exit loop and program
        while (true) {

            symbolGraph.printPath();
            System.out.println("__________________________________________________________________");
        }

    }
}