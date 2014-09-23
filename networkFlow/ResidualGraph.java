package networkFlow;
import java.util.*;

/**
 * The Class ResidualGraph.
 * Represents the residual graph corresponding to a given network.
 */
public class ResidualGraph extends Network {
	/**
	 * Instantiates a new ResidualGraph object.
	 * Builds the residual graph corresponding to the given network net.
	 * Residual graph has the same number of vertices as net.
	 * @param net the network
	 */
	private int [][] res_capacity;
	
	public ResidualGraph (Network net) {
		super(net.numVertices);
		this.adjLists = net.adjLists;
		this.adjMatrix = net.adjMatrix;
		res_capacity = new int[net.numVertices][net.numVertices];
		for(int i=0;i<numVertices;i++){
			for(int j=0;j<numVertices;j++){
				if(adjMatrix[i][j] != null){
					res_capacity[i][j] = adjMatrix[i][j].getCap();
				}
				else{
					res_capacity[i][j] = 0;
				}
			}
		}
	}

	/**
	 * Find an augmenting path if one exists.
	 * Determines whether there is a directed path from the source to the sink in the residual
	 * graph -- if so, return a linked list containing the edges in the augmenting path in the
     * form (s,v_1), (v_1,v_2), ..., (v_{k-1},v_k), (v_k,t); if not, return an empty linked list.
	 * @return the linked list
	 */
	public LinkedList<Edge> findAugmentingPath () {
		
		//BFS Algorithm begin
		Queue<Vertex> queue = new LinkedList<Vertex>();
		LinkedList<Edge> edges = new LinkedList<Edge>();
		boolean containsSink = false;
		
		for(int i=0;i<numVertices;i++){
			getVertexByIndex(i).setColor(Vertex.WHITE);
			getVertexByIndex(i).setParaent(null);
		}
		
		source.setColor(Vertex.GRAY);
		source.setParaent(null);
		queue.clear();
		
		queue.add(source);
		while(!queue.isEmpty()){
			Vertex v = queue.poll();
			if(v.equals(sink)){
				containsSink = true;
			}
			for(int i=0;i<res_capacity.length;i++){
				if(res_capacity[v.getLabel()][i] > 0){
					if(getVertexByIndex(i).getColor() == Vertex.WHITE){
						getVertexByIndex(i).setColor(Vertex.GRAY);
						getVertexByIndex(i).setParaent(v);
						queue.add(getVertexByIndex(i));
					}
				}
			}
			v.setColor(Vertex.BLACK);
		}
		if(containsSink){
			Vertex v = sink;
			while(v.getParaent() != null){
				edges.addFirst(new Edge(v.getParaent(),v));
				v = v.getParaent();
			}
		}
		else{
			edges = null;
		}
		return edges;
	}
	
	public void setRes_capacity(int source,int target,int value){
		res_capacity[source][target] = value;
	}
	
	public int[][] getRes_capacity(){
		return res_capacity;
	}
	
	public void clearFlow(){
		for(int i=0;i<numVertices;i++){
			for(int j=0;j<numVertices;j++){
				if(adjMatrix[i][j] != null){
					adjMatrix[i][j].setFlow(0);
					res_capacity[i][j] = adjMatrix[i][j].getCap();
				}
			}
		}
	}
	
}
