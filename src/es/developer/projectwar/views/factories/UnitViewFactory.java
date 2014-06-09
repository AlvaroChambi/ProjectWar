 package es.developer.projectwar.views.factories;

import es.developer.projectwar.models.UnitModel;
import es.developer.projectwar.utils.UnitType;
import es.developer.projectwar.views.UnitView;

public class UnitViewFactory {
	//private static final String TAG = UnitViewFactory.class.getCanonicalName();
	
	public UnitView createUnitView(UnitType type, UnitModel model){
		UnitView view = null;
		switch(type){
		case antiAircraft:
			break;
		case helicopter:
			break;
		case soldier:
			view = new UnitView(model);
			break;
		case tank:
			break;
		default:
			break;
		
		}
		return view;
	}
}
