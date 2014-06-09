package es.developer.projectwar.views;

import org.andengine.entity.IEntity;

import es.developer.projectwar.models.IObservable;

public abstract class MovableView extends View{
	protected IEntity entity;
	protected int id;
	
	public void subscribeToChanges(IObservable model){
		model.registerObserver(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
