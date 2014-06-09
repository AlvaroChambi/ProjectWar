package es.developer.projectwar.map;

/**
 * Class that contains the model of map containing matrix
 * @author AF
 *
 */
public class MapMatrix {

	private int columns;
	private int rows;
	private boolean [][] mapMatrix;

	public MapMatrix(int rows, int columns){
		this.columns = columns;
		this.rows = rows;
		initMapMatrix(rows,columns);
	}

	//Initialize the matrix associated to the tile map attached to the scene

	private void initMapMatrix(int rows, int columns){

		mapMatrix = new boolean [rows][columns];
		for(int i=0; i < rows ; i++){
			for(int j=0; j < columns ; j++){

				mapMatrix[i][j] = true;
			}
		}
	}


	public void setMapMatrix(boolean [][] mapMatrix){
		this.mapMatrix = mapMatrix;
	}

	public boolean[][] getMapMatrix(){
		return mapMatrix;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}



}
