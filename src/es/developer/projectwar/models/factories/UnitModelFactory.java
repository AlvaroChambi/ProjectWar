package es.developer.projectwar.models.factories;

import es.developer.projectwar.models.SoldierModel;
import es.developer.projectwar.models.UnitModel;
import es.developer.projectwar.utils.UnitType;

public class UnitModelFactory {
	//private final static String TAG = UnitModelFactory.class.getCanonicalName();
	private int idUnitCont;
	
	public UnitModelFactory(){
		this.idUnitCont = 0;
	}
	
	public UnitModel createUnitModel(UnitType type){
		UnitModel model = null;
		switch(type){
		case soldier:
			model = new SoldierModel(idUnitCont++);
			break;
		case antiAircraft:
			break;
		case helicopter:
			break;
		case tank:
			break;
		default:
			break;
		}

		return model;
	}
}
