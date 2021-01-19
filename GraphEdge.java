
//the graphedge which connect two node.
public class GraphEdge {
	private GraphNode left;
	private GraphNode right;
	private boolean marked;
	int i;
	int j;
	//the constructor: which contain two node variable and the busline char variable.
    GraphEdge(GraphNode u, GraphNode v){
		 left = u;
		 right = v;
		 marked = false;
		 i = u.getName();
		 j = v.getName();

	 }
    //get the first node.
    GraphNode firstEndpoint() {
    	return left;
    }
    //get the second node.
     GraphNode secondEndpoint() {
    	return right;
     }


     void setmark(){
    	marked = true;
	 }



     //get the busline variable.
}
