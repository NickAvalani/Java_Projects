import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.*;

/*
 * The class DGraph stores a directed weighted 
 * graph represented using adjacency list. 
 * It also defines a edge and a node which stores 
 * the path taken and the cost associated with it.
 * The methods defined in the class include:
 * DGraph() -> constructor
 * addEdge() -> adds a edge to the graph
 * getEdge() -> returns an edge when provided with a starting and ending vertex
 * mine() -> A function that traverses the graph depth first
 * mine() -> helper function
 * heuristics() -> A function that traverses the graph breadth first
 * hamiltonianCycles() -> Traverses the graph depth first
 * hamiltonianCycles() -> helper function
 */
class DGraph 
{ 	/*
	 * This class defines an edge
	 * in the graph that contains 
	 * 2 vertices (label) and a weight. 
	 */
	class Edge
	{
		int label;
		float weight;
		public Edge(int v, float w)
		{
			label = v;
			weight = w;
		}
	}
	
	/*
	 * This class defines an object
	 * that contains the weight and 
	 * the path taken.
	 */
	class Node
	{
		double weight;
		ArrayList<Integer> path;
		public Node(double d, ArrayList<Integer> path) {
			this.weight = d;
			this.path = path;
		}
	}
	
	private double min_cost;
	private ArrayList<Integer> min_path;
	private int numVertices;
	private ArrayList<LinkedList<Edge>> adjList = new ArrayList<>();
	
	DGraph(int numVertices) {
        this.min_path = null;
		this.min_cost = Float.MAX_VALUE;
		this.numVertices = numVertices; 
		for (int i = 0; i < numVertices; i++)
			adjList.add(new LinkedList<Edge>());
	} 
 
	void addEdge(int u, int v, float w) { 
		adjList.get(u).add(new Edge(v,w)); 
		//adjList.get(v).add(new Edge(u,w)); 
	} 

	Edge getEdge(int u, int v) { 
		for (int i = 0; i < adjList.get(u).size(); i++)
			if (adjList.get(u).get(i).label == v)
				return adjList.get(u).get(i);
		return null;
	} 
	
	public Node mine(int start)
	{
		boolean[] visited = new boolean[numVertices];
		List<Integer> path = new ArrayList<>();
		path.add(start);
		Node node = mine(start, visited, path, 0);
		ArrayList<Integer> path_1 = node.path;
		for(int i = 0; i < path_1.size(); i++)
		{
			path_1.set(i, path_1.get(i) + 1);
		}
		return node;
	}
	
	public Node mine(int u, boolean[]visited, List<Integer>path, double Cost)
	{
	   visited[u] = true;
	   
	   if(path.size() == numVertices)
	   {
	       int start = path.get(path.size() - 1);
	       int end = path.get(0);
	       double final_Cost = getEdge(start,end).weight;
	       return new Node(Cost + final_Cost, new ArrayList<Integer>(path));
	   }
	   
	   if(min_cost < Cost)
	   {
	    return new Node(min_cost, new ArrayList<Integer>(path));  
	   }
	   
	   for(int i = 0; i < adjList.get(u).size(); i++)
	   {
	       Integer v = adjList.get(u).get(i).label;
	       if(!visited[v])
	       {
	           path.add(v);
	           Node node = mine(v, visited, path, Cost + adjList.get(u).get(i).weight);
	           if (node.weight < min_cost)
	           {
	        	   min_cost = node.weight;
	        	   min_path = node.path; //correct as per function.
	           }
	           
	           visited[v] = false;
	           path.remove(path.size() - 1);
	       }
	   }
	   return new Node(min_cost, min_path);
	}
	
	public Node heuristic(int start) { 
		boolean visited[] = new boolean[numVertices]; 
		LinkedList<Integer> queue = new LinkedList<>(); 
		List<Integer> path = new ArrayList<>();
		queue.add(start); 
		visited[start]=true; 
		double final_cost = 0;
 
		while (queue.size() != 0)  { 
			int u = queue.pollFirst(); 
			path.add(u);
			double minWeight = Float.MAX_VALUE;
			int first_vertex = u;
 
			for (int i = 0; i < adjList.get(u).size(); i++) {
				int v = adjList.get(u).get(i).label;
				if (!visited[v]) {
					if(adjList.get(u).get(i).weight < minWeight) {
						minWeight = adjList.get(u).get(i).weight;
						first_vertex = v;
					}
				}         	  
			}
			if(first_vertex != u) {
				queue.add(first_vertex);
				visited[first_vertex] = true;
				final_cost += getEdge(u, first_vertex).weight;
			}
		} 
		final_cost += getEdge(path.get(path.size() - 1), path.get(0)).weight;
		for(int i = 0; i < path.size(); i++) {
			path.set(i,  path.get(i) + 1);
		}
		return new Node(final_cost, new ArrayList<Integer>(path));
	}
	
	 
    public Node hamiltonianCycles(int start)
    {
    	boolean[] visited = new boolean[numVertices];
    	List<Integer> path = new ArrayList<>();
    	path.add(start);
    	Node node = hamiltonianCycles(start, visited, path, 0);
    	ArrayList<Integer> path_1 = node.path;
    	for(int i = 0; i < path_1.size(); i++) {
    		path_1.set(i, path_1.get(i) + 1);
    	}
    	return node;
    }
    
    public Node hamiltonianCycles(int u, boolean[] visited, List<Integer> path, double cost)
    {
		visited[u] = true;
		
    	// if all the vertices are visited, then the Hamiltonian cycle exists
    	if (path.size() == numVertices){
    		//System.out.println("113");
    		//System.out.println(path);
    		int start = path.get(0); 
    		int end = path.get(path.size() - 1);
    		double final_cost = getEdge(end, start).weight;
    		return new Node(final_cost + cost, new ArrayList<Integer>(path));
    	}
    	
    	double min_cost = Float.MAX_VALUE;
    	ArrayList<Integer> min_path = null; 
    	// Check if every edge starting from vertex `u` leads to a solution or not
    	for (int i = 0; i < adjList.get(u).size(); i++) {
    		Integer v = adjList.get(u).get(i).label;
    		if (!visited[v]) {
    			path.add(v);
    			double total = cost + adjList.get(u).get(i).weight;
    			Node result = hamiltonianCycles(v, visited, path, total);
    			if(result.weight < min_cost) {
    				min_cost = result.weight;
    				min_path = result.path;
    			}
    			// backtrack for the path
    			visited[v] = false;  // so v could be used in another path
    			path.remove(path.size() - 1);
    		}
    	}
    	return new Node(min_cost, min_path);
    }

}



