package es.developer.projectwar.map.pathfinding;

public class Node {
	  	
	private int x , y , d;	
    private int id;
    private Node parent;
    
    public Node( int x1, int y1 , int d1,int id,Node parent){
        this.setX(x1);
        this.setY(y1);
        this.setD(d1);
        this.id = id;
        this.setParent(parent);
    }
    public Node(){
    	x=0;
    	y=0;
    	d=0;
    	id=0;
    	parent=null;
    }

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the d
	 */
	public int getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(int d) {
		this.d = d;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the parent node
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @param parent node to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
}
