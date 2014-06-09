package es.developer.projectwar.map;

import org.andengine.extension.tmx.TMXLayer;


public class MapModel {
	private String resource;
	private String name;
	private	MapMatrix mapMatrix;
	private String configuration;
	private TMXLayer tmxLayer;
	
	public MapModel(){

	}
	
	public MapModel(String name){

	}
	
	public void initMap(int rows, int columns){
		mapMatrix = new MapMatrix(rows, columns);
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mapMatrix
	 */
	public MapMatrix getMapMatrix() {
		return mapMatrix;
	}

	/**
	 * @param mapMatrix the mapMatrix to set
	 */
	public void setMapMatrix(MapMatrix mapMatrix) {
		this.mapMatrix = mapMatrix;
	}

	/**
	 * @return the configuration
	 */
	public String getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	
	public int getRows(){
		return mapMatrix.getRows();
	}
	
	public int getColumns(){
		return mapMatrix.getColumns();
	}

	/**
	 * @return the tmxLayer
	 */
	public TMXLayer getTmxLayer() {
		return tmxLayer;
	}

	/**
	 * @param tmxLayer the tmxLayer to set
	 */
	public void setTmxLayer(TMXLayer tmxLayer) {
		this.tmxLayer = tmxLayer;
	}
}
