package es.developer.projectwar.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.developer.projectwar.utils.TextureRegionFactory.TextureType;
import es.developer.projectwar.utils.UpdateInput;
import es.developer.projectwar.views.IObserver;

public abstract class Model implements IObservable{

	protected List<IObserver> observers;
	//TODO remove texture to a lower implementation of this superclass
	private TextureType textureType;
	
	public Model(){
		this.observers = new ArrayList<IObserver>();
	}

	@Override
	public void registerObserver(IObserver observer) {
		observers.add(observer);
	}
	@Override
	public void unregisterObserver(IObserver observer) {
		observers.remove(observer);
	}
	
	protected void notifyListenersUpdate(UpdateInput input){
		Iterator<IObserver> iterator = observers.iterator();
		while(iterator.hasNext()){
			final IObserver observer = iterator.next();
			observer.onUpdateNotification(input);
		}
	}

	/**
	 * @return the textureType
	 */
	public TextureType getTextureType() {
		return textureType;
	}
	/**
	 * @param textureType the textureType to set
	 */
	public void setTextureType(TextureType textureType) {
		this.textureType = textureType;
	}
}
