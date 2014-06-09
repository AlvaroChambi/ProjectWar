package es.developer.projectwar.models;

public enum Player {
	Player (0),
	IAPlayer (1);
	
	private int code;
	
	private Player(int code){
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
}
