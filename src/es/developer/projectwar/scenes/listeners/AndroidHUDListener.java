package es.developer.projectwar.scenes.listeners;

import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import es.developer.projectwar.R;
import es.developer.projectwar.controllers.GameCommand;
import es.developer.projectwar.controllers.IGameCommandListener;
import es.developer.projectwar.controllers.IPlayerCommandListener;
import es.developer.projectwar.controllers.commands.Command;

public class AndroidHUDListener implements OnClickListener, OnItemClickListener{
	private static final String TAG = AndroidHUDListener.class.getCanonicalName();
	private IPlayerCommandListener playerCommandListener;
	private IGameCommandListener gameCommandListener;
	private List<Command> commands;
	

	@Override	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		playerCommandListener.onPlayerCommandReceived(commands.get(position));
		
	}

	@Override
	public void onClick(View element) {
		switch(element.getId()){
		case R.id.finish_command:
			if(gameCommandListener!= null){
				gameCommandListener.onGameCommandReceived(GameCommand.End);
			}
			Log.i(TAG , "finish!");
			break;
		}
	}


	/**
	 * @param playerCommandListener the playerCommandListener to set
	 */
	public void setPlayerCommandListener(IPlayerCommandListener playerCommandListener) {
		this.playerCommandListener = playerCommandListener;
	}

	/**
	 * @param gameCommandListener the gameCommandListener to set
	 */
	public void setGameCommandListener(IGameCommandListener gameCommandListener) {
		this.gameCommandListener = gameCommandListener;
	}

	/**
	 * @param commands the commands to set
	 */
	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}
}
