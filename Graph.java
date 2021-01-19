
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Set;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Graph extends JFrame implements GraphADT   {
	int width;
	int height;
	private GraphEdge[][] matrix;
	private List<GraphNode> nodes;
	private List<GraphEdge> edges;


	//the constructor for graph object. create n nodes and store them into an array.
	 public  Graph(int n,int width,int height) throws GraphException {
//	 	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	nodes = new ArrayList<GraphNode>();
	 	edges = new ArrayList<GraphEdge>();
	 	matrix = new GraphEdge[n][n];
	 	setSize(width,height);
	 	setVisible(true);
	 	this.width = width;
	 	this.height = height;
	 	int unitwidth = width/n;
	 	int unitheight = height/n;
		for (int i = 0; i<n;i++) {
			Random r = new Random();
			int randomwidth = r.nextInt(width) ;
			int randomheight = r.nextInt(height) ;
			 GraphNode newnode= new GraphNode(i,randomwidth,randomheight);
			 nodes.add(newnode);
		}
		 for (int i = 0; i < n - 1; i++) {
			 for (int j = i + 1; j < n; j++) {
				 int rd = Math.random() > 0.5 ? 1 : 0;
				 if (rd == 1) {
					 GraphNode u = nodes.get(i);
					 GraphNode v = nodes.get(j);
					 GraphEdge edge = new GraphEdge(u, v);
					 insertEdge(edge);

				 }

			 }

		 }


	 }




	 // insert edge between two nodes. if not exsit. throw errr
	 public void insertEdge(GraphEdge edge)throws GraphException{
			int row = edge.firstEndpoint().getName();
			int col = edge.secondEndpoint().getName();
			if(row>nodes.size()-1||col>nodes.size()-1) {
				throw new GraphException("we can't find either of nodes");
			}
		    if (nodes.get(row)==null||nodes.get(col)==null) {
		    	throw new GraphException("we can't find either of nodes");
		    }
		    if(matrix[row][col]!=null) {
		       throw new GraphException("the edge has already exist");
		    }
		    edges.add(edge);
		    matrix[row][col] = edge;
		    matrix[col][row] = edge;
	 }


	 public boolean ifNode(int name){
		 if (nodes.get(name)==null) {
			 return false;
		 }
		 else {
		 	return true;
		 }



	 }

	 public List<GraphEdge> returnedges(){
	 	return edges;
	 }





	public GraphNode getNode(int name)throws GraphException {
		 if(name>nodes.size()-1) {
			 throw new GraphException("we can't find the node");
		 }
		 if (nodes.get(name)==null) {
			 throw new GraphException("the node doesn't exsit.");
		 }
		
		 else {
			 return nodes.get(name);
		 }

	 }
	 
	 public Iterator<GraphEdge> incidentEdges(GraphNode u)throws GraphException{
		 Stack<GraphEdge> stack = new Stack<GraphEdge>();
		 int name = u.getName();
		 if(name>nodes.size()-1) {
			 throw new GraphException("we can't find the node");
		 }
		 if(nodes.get(name)==null) {
			  throw new GraphException("the node doesn't exsit.");
		 }
		 else {
			 for(int i=0;i<nodes.size();i++) {
				if( matrix[name][i]!=null) {
					stack.push(matrix[name][i]);
				}
			 }
		 }
		 return stack.iterator();
	 }
	 
	 public GraphEdge getEdge(GraphNode u, GraphNode v)throws GraphException {
			int row = u.getName();
			int col = v.getName();
			if(row>nodes.size()-1||col>nodes.size()-1) {
				throw new GraphException("we can't find either of nodes");
			}
	        if(matrix[row][col]==null) {
			       throw new GraphException("there is no edge between those two points.");
			    }
	        else {
	        	return matrix[row][col];
	        }
	 }
	 
	 public boolean areAdjacent(GraphNode u, GraphNode v)throws GraphException {
		 int row = u.getName();
		 int col = v.getName();
		if(row>nodes.size()-1||col>nodes.size()-1) {
			throw new GraphException("we can't find either of nodes");
		}
		  if (nodes.get(row)==null||nodes.get(col)==null) {
		    	throw new GraphException("we can't find either of nodes");
		    }
		  if(matrix[row][col]==null) {
			  return false;
		  }
		  else {
			  return true;
		  }
	 }



	public void paint(Graphics g) { // draw the nodes and edges
		FontMetrics f = g.getFontMetrics();
//		int nodeHeight = Math.max(height, f.getHeight());
		int nodeHeight = 30;

		g.setColor(Color.black);
		for ( GraphEdge e : edges) {

			g.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
					nodes.get(e.j).x, nodes.get(e.j).y);
		}

		for (GraphNode n : nodes) {
//			int nodeWidth = Math.max(width, f.stringWidth(String.valueOf((n.getName()))+width/2));
			int nodeWidth = 30;
			g.setColor(Color.white);
			g.fillOval(n.x-nodeWidth/2, n.y-nodeHeight/2,
					nodeWidth, nodeHeight);
			g.setColor(Color.black);
			g.drawOval(n.x-nodeWidth/2, n.y-nodeHeight/2,
					nodeWidth, nodeHeight);

			g.drawString(String.valueOf((n.getName())), n.x-f.stringWidth(String.valueOf((n.getName())))/2,
					n.y+f.getHeight()/2);
		}
	}

	 
	  
}
