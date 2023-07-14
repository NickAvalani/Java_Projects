/*
 * Author: Nishith Avalani
 * File: PA11Main.java
 * Course: CSc 210, Professor Claveau, Fall 2021
 * Purpose: This file reads in the command line arguments and 
 *          creates a weighted, directed graph, and finds a path from
 *          the start vertex to the end vertex with the minimum cost 
 *          in terms of their weights, based on the second command
 *          argument (heuristic, backtrack, time, mine).     
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.*;

/*
 * This class reads in the command-line arguments,
 * creates a weighted, directed graph and finds 
 * a path in the graph based on the second command
 * line argument.
 * The method in the class include:
 * createGraph()
 */
public class PA11Main {

	public static void main(String[] args) throws FileNotFoundException {
        Scanner file = null;
        try {
        	file = new Scanner(new File(args[0]));} 
        catch (FileNotFoundException e) {
            e.printStackTrace();}
        
        String command = new String(args[1]).toLowerCase();
        
        DGraph g = createGraph(file);
        
        if(command.equals("heuristic"))
        {	
        	DGraph.Node node = g.heuristic(0);
        	System.out.printf("cost = %.1f, visitOrder = " + node.path +"\n", node.weight);
        }
        
        if(command.equals("backtrack"))
        {
        	DGraph.Node node = g.hamiltonianCycles(0);
        	System.out.printf("cost = %.1f, visitOrder = " + node.path +"\n", node.weight);
        }
        
        else if(command.equals("time"))
        {
        	long startTime = System.nanoTime();
        	DGraph.Node trip = g.heuristic(0);
        	long endTime = System.nanoTime();
        	long duration = (endTime - startTime) / 1000000;
        	System.out.println("heuristic: cost = " + trip.weight + ", " + duration + " milliseconds");
        	
        	long startTime1 = System.nanoTime();
        	DGraph.Node trip1 = g.mine(0);
        	long endTime1 = System.nanoTime();
        	long duration1 = (endTime1 - startTime1) / 1000000;
        	System.out.println("mine: cost = " + trip1.weight + ", " + duration1 + " milliseconds");
        	
        	long startTime2 = System.nanoTime();
        	DGraph.Node trip2 = g.hamiltonianCycles(0);
        	long endTime2 = System.nanoTime();
        	long duration2 = (endTime2 - startTime2) / 1000000;
        	System.out.println("backtrack: cost = " + trip2.weight + ", " + duration2 + " milliseconds");
        	
        }
        
        else if(command.equals("mine"))
        {	
        	DGraph.Node node = g.mine(0);
        	System.out.printf("cost = %.1f, visitOrder = " + node.path +"\n", node.weight);
        }
           
	}
	
	/*
	 * This method creates a directed, weighted graph
	 * based on the vertices and weights given in the 
	 * .mtx file. 
	 * @Param Scanner object that contains the file path
	 *        of the .mtx file. 
	 * @Return a directed, weighted graph created with 
	 *         the .mtx file. 
	 */
    public static DGraph createGraph(Scanner file) 
    {
    	//First comments starting with '%' are ignored
        String startLine = null;
        while (file.hasNextLine()) {
        	startLine = file.nextLine();
            if (!startLine.startsWith("%"))
                break;
        }
        
        //Reads the number of vertices and create a DGraph
        String[] startLineSplit = startLine.split("( )+");
        int numVertices = Integer.parseInt(startLineSplit[0]);
        DGraph graph = new DGraph(numVertices);
        
        //Reads the edge's info and adds the edges to the graph
        while (file.hasNextLine()) {
            String[] s = file.nextLine().split("( )+");
            graph.addEdge(Integer.parseInt(s[0])-1, Integer.parseInt(s[1])-1, Float.parseFloat(s[2]));
        }
        return graph;
    }	
}