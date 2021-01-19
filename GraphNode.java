
//create an graphNode
public class GraphNode {
	private int name;
	private boolean mark=false;
	int x, y;
	public GraphNode(int name,int x, int y){
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	//set mark on certain node.
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	//get mark on certain node.
	public boolean getMark() {
		return mark;
	}
	//get the name of certain node.
	public int getName() {
		return name;
	}
}
