package es.developer.projectwar.views;

import org.andengine.entity.shape.IShape;
import org.andengine.extension.tmx.TMXTile;

import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.utils.UpdateInput;

public class PlayerView extends MovableView {
//	private static final String TAG = PlayerView.class.getCanonicalName();
	private PlayerModel model;
	
	public PlayerView(PlayerModel model, IShape sprite){
		this.entity = sprite;
		this.model = model;
	}
	@Override
	public void onUpdateNotification(UpdateInput input) {
		TMXTile position = model.getPosition();
		switch(input){
		case POSITION_CHANGED:
		case UNIT_SELECTED:
			this.entity.setPosition(position.getTileX(),position.getTileY());
			break;
		default:
			break;
		
		}
	}
	
	public void setModel(PlayerModel model){
		this.model = model;
		model.registerObserver(this);
	}
}
