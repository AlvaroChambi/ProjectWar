package es.developer.projectwar.scenes.listeners;

import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import es.developer.projectwar.R;
import es.developer.projectwar.controllers.GameController;
import es.developer.projectwar.controllers.commands.Command;
import es.developer.projectwar.controllers.commands.PlayerCommand;
import es.developer.projectwar.controllers.commands.factories.CommandFactory;
import es.developer.projectwar.drawers.listeners.IPlayerEventsListener;
import es.developer.projectwar.scenes.huds.MenuHUD;

public class MenuHUDListener implements OnClickListener, OnItemClickListener{
	private static final String TAG = MenuHUDListener.class.getCanonicalName();
	private GameController gameController;
	private IPlayerEventsListener eventsHandler;
	private MenuHUD menuHUD;
	private List<Command> commands;
	private CommandFactory commandFactory;
	
	public MenuHUDListener(MenuHUD menuHUD){
		this.gameController = null;
		this.menuHUD = menuHUD;
		commandFactory = new CommandFactory();
	}
	
	/**
	 * Receives MenuHUD buttons click events
	 */
	@Override
	public void onClick(View element) {
		switch(element.getId()){
		case R.id.finish_command:
			if(gameController != null){
				gameController.shiftPlayer();
				menuHUD.updateView();
			}
			Log.i(TAG , "finish!");
			break;
		}
	}
	
	/**
	 * Receives commands list click events
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		PlayerCommand command = commandFactory.createCommand(commands.get(position));
		//Sends the command to the player events handler
		eventsHandler.onCommandSelected(command);
	}

	/**
	 * @param gameController the gameController to set
	 */
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	/**
	 * @param eventsHandler the eventsHandler to set
	 */
	public void setEventsHandler(IPlayerEventsListener eventsHandler) {
		this.eventsHandler = eventsHandler;
	}

	/**
	 * @param commands the commands to set
	 */
	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}
}
