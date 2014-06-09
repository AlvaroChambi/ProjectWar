package es.developer.projectwar.scenes.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import es.developer.projectwar.R;
import es.developer.projectwar.controllers.commands.Command;

public class MenuHUDAdapter extends BaseAdapter{
//	private static final String TAG = MenuHUDAdapter.class.getCanonicalName();
	private List<Command> commands;
	private Context context;
	
	public MenuHUDAdapter(Context context, List<Command> commands){
		this.commands = commands;
		this.context = context;
	}

	@Override
	public int getCount() {
		return commands.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View inflatedView = convertView;
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflatedView = inflater.inflate(R.layout.list_item, null);
		
		TextView commandText = (TextView)inflatedView.findViewById(R.id.list_item_text);
		commandText.setTextColor(Color.BLACK);
		commandText.setTextAppearance(context, android.R.attr.textAppearanceSmall);
		commandText.setText(commands.get(position).toString());
		return inflatedView;
	}

}
