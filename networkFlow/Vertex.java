package networkFlow;

/**
 * The Class Vertex.
 * Represents a vertex in a directed graph.
 */
public class Vertex {

	/** Stores an integer label associated with the vertex.*/
      protected int label;
      private boolean isVisited;
      private int color;
      private Vertex paraent;
      
      public static final int BLACK = 0;
      public static final int GRAY = 1;
      public static final int WHITE = 2;

	/**
	 * Instantiates a new vertex.
	 * @param i the vertex label
	 */
	public Vertex(int i) {
		label = i;
	}

	/**
	 * Gets the vertex label.
	 * @return the vertex label
	 */
	public int getLabel() {
		return label;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	@Override
	public boolean equals(Object obj) {
		boolean b = false;
		if(obj instanceof Vertex && ((Vertex) obj).getLabel() == this.label){
			b = true;
		}
		return b;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Vertex getParaent() {
		return paraent;
	}

	public void setParaent(Vertex paraent) {
		this.paraent = paraent;
	}
	
}
