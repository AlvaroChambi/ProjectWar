package es.developer.projectwar.scenes.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import es.developer.projectwar.R;
import es.developer.projectwar.map.MapModel;

public class MapListAdapter extends BaseAdapter{
	
	private Context context;
	private TextView mapName;
	private List<MapModel> mapList;
	
	public MapListAdapter(Context context, List<MapModel> mapList){
		this.context = context;
		this.mapList = mapList;
	}
	@Override
	public int getCount() {
		return mapList.size();
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
		
		mapName = (TextView)inflatedView.findViewById(R.id.list_item_text);
		mapName.setText(mapList.get(position).getName());
		return inflatedView;
	}
}
