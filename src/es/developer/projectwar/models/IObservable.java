package es.developer.projectwar.models;

import es.developer.projectwar.views.IObserver;


public interface IObservable {
	public void registerObserver(IObserver observer);
	public void unregisterObserver(IObserver observer);
}
