/**
 * Singleton class associated to a tiled map scene that handles the player's path creation.
 */
package es.developer.projectwar.map.pathfinding;

import java.util.ArrayList;

import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.map.MapMatrix;
import es.developer.projectwar.map.MapModel;
import es.developer.projectwar.utils.Coordinate;

public class PathGenerator {
//	private static final String TAG = PathGenerator.class.getCanonicalName();
	private static final int TILE_SPEED = 1;
	private MapModel map;
	private int duration;
	private ArrayList<Node> pathPoints;
	private BestPathAlgorithm algorythm;
	
	public PathGenerator(MapModel map){
		algorythm = new BestPathAlgorithm();
		this.map = map;
	}
	
	/**
	 * Method that returns the best path for a given row-columns, start and end position.
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @return Path
	 */
	
	public Path generatePath(int startX, int startY, int endX, int endY  ){
		MapMatrix matrix = map.getMapMatrix();
		boolean [][] visitado = new boolean[matrix.getRows()][matrix.getColumns()];
		pathPoints = algorythm.getBestPath(startX, startY, matrix.getColumns(), matrix.getRows(), matrix.getMapMatrix(), endX, endY, visitado);
		//printPath();
		return generatePath(pathPoints);
	}
	
	/**
	 * Method that returns the best path for a given row-columns, start and end position.
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @return Path
	 */
	
	public Path generatePath(Coordinate initialPosition, Coordinate finalPosition){
		MapMatrix matrix = map.getMapMatrix();
		boolean [][] visitado = new boolean[matrix.getRows()][matrix.getColumns()];
//		pathPoints = algorythm.getBestPath(initialPosition.getX(), initialPosition.getY(), matrix.getRows(), 
//										matrix.getColumns(), matrix.getmatrix(), finalPosition.getX(), finalPosition.getY(), visitado);
		pathPoints = algorythm.getBestPath(initialPosition, matrix, finalPosition, visitado);
		return generatePath(pathPoints);
	}
	
	/**
	 * Get the real path associated to the scene using the given Nodes collection
	 * @param pathPoints
	 * @return
	 */
	
	private Path generatePath(ArrayList<Node> pathPoints) {
		Path path = null;
		if(pathPoints!= null){
			float[] coordinatesX = new float[pathPoints.size()];
			float[] coordinatesY = new float[pathPoints.size()];
			TMXTile tile;	
			TMXLayer tmxLayer = map.getTmxLayer();
			pathPoints = this.fixPathPoints(pathPoints);
			this.pathPoints = pathPoints;
			for(int i = 0; i< pathPoints.size() ; i++){
				coordinatesX[i] = pathPoints.get(i).getX();
				coordinatesY[i] = pathPoints.get(i).getY();
				tile = tmxLayer.getTMXTile(pathPoints.get(i).getX(), pathPoints.get(i).getY());
				coordinatesX[i] = tile.getTileX();
				coordinatesY[i] = tile.getTileY();
			}
			duration = (pathPoints.size()*TILE_SPEED)/4;
			path = new Path(coordinatesX, coordinatesY);
		}		
		return path;
	}
	
	/**
	 * invert node collect given by the best path algorithm
	 * @param pathPoints
	 * @return
	 */
	
	private ArrayList<Node> fixPathPoints(ArrayList<Node> pathPoints){
		ArrayList<Node> auxList = new ArrayList<Node>();
		for(int i = pathPoints.size()-1; i >= 0 ; i--){
			auxList.add(pathPoints.get(i));
//			Log.i(TAG, "path point:");
//			Log.i(TAG, "x: " + pathPoints.get(i).getX() + "y: " + pathPoints.get(i).getY());
		}	
		return auxList;
	}
	
	/**
	 * Print the associated path in the main scene
	 */
//	public void printPath(){
//		if(pathPoints != null){
//			Iterator<Node> iterador = pathPoints.iterator();
//			TMXTile tile;
//			Node aux;
//			Rectangle rectangle;
//			printBuffer = new ArrayList<Rectangle>();
//			while(iterador.hasNext()){
//				aux = iterador.next();
//				tile = tmxLayer.getTMXTile(aux.getX(), aux.getY());
//				rectangle = createRectangle();
//				rectangle.setPosition(tile.getTileX(), tile.getTileY());
//				printBuffer.add(rectangle);
//				scene.getScene().attachChild(rectangle);
//			}	
//		}
//		
//	}
	
	/**
	 * Clean the printed path and release all the resources associated
	 */
	
//	public void resetPath(){
//		if(printBuffer != null){
//			Iterator<Rectangle> iterador = printBuffer.iterator();
//			Rectangle rectangle;
//			while(iterador.hasNext()){
//				rectangle = iterador.next();
//				scene.getScene().detachChild(rectangle);
//				rectangle.dispose();
//			}	
//			printBuffer.clear();
//		}	
//	}
	
	/**
	 * Instantiation of a rectangle
	 * @return
	 */
	
//	private Rectangle createRectangle(){
//		Rectangle pathTile;
//		float tileWidth = tiledMap.getTileWidth()/2;
//		float tileHeight = tiledMap.getTileHeight()/2;
//		pathTile = new Rectangle(0, 0, tileWidth, tileHeight, scene.getVertexBufferObjectManager());
//		pathTile.setColor(1, 0, 0, 0.50f);
//		return pathTile;
//	}
	
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
}