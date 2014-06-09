package es.developer.projectwar.utils;

public class Coordinate {

	private int x;
	private int y;
	
	public Coordinate (int x, int y){
		this.x = x;
		this.setY(y);
	}
	
	public boolean equals(Coordinate coordinate){
		boolean resul = false;
		if(coordinate.getX() == this.getX()
				&& coordinate.getY() == this.getY()){
			resul = true;
		}
		return resul;
	}
	
	@Override
	public String toString() {
		String resul = "";
		resul = "x: "+this.x;
		resul += " y:"+this.y;
		return resul;
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
	
	
}
