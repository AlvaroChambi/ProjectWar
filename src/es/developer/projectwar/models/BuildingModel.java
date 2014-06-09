package es.developer.projectwar.models;

import java.util.ArrayList;

import es.developer.projectwar.views.IObserver;

public class BuildingModel extends Model{
	
	private int id;
	
	public BuildingModel(int id){
		this.observers = new ArrayList<IObserver>();
		this.setId(id);
		//this.setTextureType(TextureType.building);
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
}
