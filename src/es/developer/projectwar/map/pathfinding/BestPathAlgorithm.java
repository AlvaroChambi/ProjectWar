package es.developer.projectwar.map.pathfinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import android.util.Log;
import es.developer.projectwar.map.MapMatrix;
import es.developer.projectwar.utils.Coordinate;

public class BestPathAlgorithm {
	//private final static String TAG = BestPathAlgorithm.class.getCanonicalName();
	private Queue<Node> finalPath;
//  Diagonal sprite movement	
//	private final int INC_X[  ] = { 0 ,  0 , 1 , -1 , 1 , 1 , -1 , -1 };		
//	private final int INC_Y[  ] = { 1 , -1 , 0 ,  0 , 1 , -1 , 1 , -1 };
	private final int INC_X[  ] = { 0 ,  0 , 1 , -1 };		
	private final int INC_Y[  ] = { 1 , -1 , 0 ,  0 };
	private int nx;
	private int ny;
	private ArrayList<Node> pathPoints;
	
	/**
	 * Returns a nodes collection that contains the path points associated to the best path between two points
	 * 
	 * @param iniX
	 * @param iniY
	 * @param columns
	 * @param rows
	 * @param ady
	 * @param finalX
	 * @param finalY
	 * @param visitado
	 * @return
	 */

	public  ArrayList<Node> getBestPath( int iniX , int iniY , int columns , int rows , boolean  [][] ady, int finalX, int finalY ,boolean [][] visitado){ 
		
		int idCont = 0;
		Queue<Node> finalPath = new LinkedList<Node>();	
		finalPath.add( new Node( iniX , iniY , 0,idCont++,null ) );					
		
		while( !finalPath.isEmpty() ){		
			Node actual = finalPath.remove();					
			if(actual.getX() == finalX && actual.getY() == finalY ){
				Log.i("tiles","queue size: "+ String.valueOf(finalPath.size()));
				return generatePathPoints(actual);						
			}
			visitado[ actual.getX() ][ actual.getY() ] = true;	
			for( int i = 0 ; i < 4 ; ++i ){				
				nx = INC_X[ i ] + actual.getX();				
				ny = INC_Y[ i ] + actual.getY();					        
				if( nx >= 0 && nx < columns && ny >= 0 && ny < rows && !visitado[ nx ][ ny ] && ady[ ny ][ nx ] ){
					visitado[nx][ny]=true;
					finalPath.add( new Node( nx , ny , actual.getD() + 1 ,idCont++,actual) ); 
				}
			}	
		}
		return null;
	}
	
	public ArrayList<Node> getBestPath(Coordinate initialPosition, MapMatrix mapMatrix, Coordinate finalPosition,
			boolean[][] visitado) {
		int idCont = 0;
		Queue<Node> finalPath = new LinkedList<Node>();	
		finalPath.add( new Node( initialPosition.getX() , initialPosition.getY() , 0,idCont++,null ) );

		while( !finalPath.isEmpty() ){		
			Node actual = finalPath.remove();					
			if(actual.getX() == finalPosition.getX() && actual.getY() == finalPosition.getY()){
				return generatePathPoints(actual);						
			}
			visitado[ actual.getY() ][ actual.getX() ] = true;	
			for( int i = 0 ; i < 4 ; ++i ){				
				nx = INC_X[ i ] + actual.getX();				
				ny = INC_Y[ i ] + actual.getY();
				if( nx >= 0 && nx < mapMatrix.getColumns() && ny >= 0 && ny < mapMatrix.getRows() 
						&& !visitado[ ny ][ nx ] 
						&& mapMatrix.getMapMatrix()[ ny ][ nx ] ){
					visitado[ny][nx]=true;
					finalPath.add( new Node( nx , ny , actual.getD() + 1 ,idCont++,actual) ); 
				}
			}	
		}
		return null;
	}
	
	private ArrayList<Node> generatePathPoints(Node destino){
		ArrayList<Node> resul = new ArrayList<Node>();
		if(destino!=null){
			resul.add(destino);
			while(destino.getParent()!= null){
				resul.add(destino.getParent());
				destino = destino.getParent();
			}
		}
		pathPoints = resul;
		return resul;
	}

	/**
	 * @return the finalPath
	 */
	public Queue<Node> getFinalPath() {
		return finalPath;
	}

	/**
	 * @param finalPath the finalPath to set
	 */
	public void setFinalPath(Queue<Node> finalPath) {
		this.finalPath = finalPath;
	}

	/**
	 * @return the pathPoints
	 */
	public ArrayList<Node> getPathPoints() {
		return pathPoints;
	}
}
