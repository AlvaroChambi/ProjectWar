package es.developer.projectwar.utils.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import es.developer.projectwar.map.MapModel;

public class MapsParser {
	//private static final String TAG = MapsParser.class.getCanonicalName();
	private Document rootDocument;

	public MapsParser(Context context){
		InputStream is;
		try {
			is = context.getAssets().open("game_maps_setting.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			rootDocument = dBuilder.parse(is);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ParserConfigurationException e){
			e.printStackTrace();
		}catch (SAXException e){
			e.printStackTrace();
		}

	}

	public List<MapModel> getMapsList(){
		List<MapModel> mapList = new ArrayList<MapModel>();
		NodeList maps = rootDocument.getElementsByTagName("Map");
		for(int i = 0; i < maps.getLength(); i++){
			final Node node = maps.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				final Element element = (Element) node;
				MapModel mapModel = new MapModel();
				int rows, columns;
				rows = Integer.valueOf(element.getAttribute("rows"));
				columns = Integer.valueOf(element.getAttribute("columns"));
				mapModel.initMap(rows, columns);
				mapModel.setName(element.getAttribute("name"));
				mapModel.setConfiguration(element.getAttribute("playersSetting"));
				mapModel.setResource(element.getAttribute("resource"));
				mapList.add(mapModel);
			}
		}
		return mapList;
	}

	public MapModel getMap(String mapName){
		MapModel mapModel = new MapModel();
		NodeList maps = rootDocument.getElementsByTagName("Map");
		for(int i = 0; i < maps.getLength(); i++){
			final Node node = maps.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				final Element element = (Element) node;
				if(element.getAttribute("name").equals(mapName)){
					int rows, columns;
					rows = Integer.valueOf(element.getAttribute("rows"));
					columns = Integer.valueOf(element.getAttribute("columns"));
					mapModel.initMap(rows, columns);
					mapModel.setConfiguration(element.getAttribute("playersSetting"));
					mapModel.setResource(element.getAttribute("resource"));
				}
			}
		}
		return mapModel;
	}
}
