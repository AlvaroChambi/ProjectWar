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
import android.util.Log;
import es.developer.projectwar.models.BuildingModel;
import es.developer.projectwar.models.Player;
import es.developer.projectwar.models.PlayerModel;
import es.developer.projectwar.models.UnitModel;
import es.developer.projectwar.models.factories.UnitModelFactory;
import es.developer.projectwar.utils.UnitType;

public class PlayersParser {
	private static final String TAG = PlayersParser.class.getCanonicalName();
	
	private Document rootDocument;
	private Context context;
	private String resource;

	public PlayersParser(Context context, String resource){
		this.context = context;
		this.resource = resource;
		this.initializeParser();
	}
	
	private void initializeParser(){
		InputStream is;
		try {
			is = context.getAssets().open(resource);
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
	
	public List<PlayerModel> getPlayers(){
		Log.i(TAG,"resource: " + resource );
		List<PlayerModel> playersList = new ArrayList<PlayerModel>();
		NodeList playersNodes = rootDocument.getElementsByTagName("player");
		for( int i = 0 ; i < playersNodes.getLength() ; i++ ){
			final Node playerNode = playersNodes.item(i);
			PlayerModel playerModel = new PlayerModel();
			final NodeList childNodes = playerNode.getChildNodes();
			
			for( int j = 0 ; j < childNodes.getLength() ; j++ ){
				final Node playerValuesNode = childNodes.item(j);
				if( playerValuesNode.getNodeName().equals( "id" ) ){
					playerModel.setId(Integer.valueOf(playerValuesNode.getTextContent()));
				} else if ( playerValuesNode.getNodeName().equals( "type" )){
					playerModel.setType(Player.valueOf(playerValuesNode.getTextContent()));
				} else if ( playerValuesNode.getNodeName().equals( "units" ) ){
					playerModel.setUnits( this.parseUnitResources(playerValuesNode) );
				} else if ( playerValuesNode.getNodeName().equals( "buildings" ) ){
					playerModel.setBuildings( this.parseBuildingResources(playerValuesNode) );
				} else if ( playerValuesNode.getNodeName().equals( "name" ) ){
					playerModel.setName( playerValuesNode.getTextContent() );
				}
			}	
			playersList.add(playerModel);
		}
		return playersList;
	}
	
	public List<UnitModel> parseUnitResources( Node node ){
		UnitModelFactory unitModelFactory = new UnitModelFactory();
		List<UnitModel> units = new ArrayList<UnitModel>();
		final NodeList unitsNodes = node.getChildNodes();
		for( int i = 0 ; i < unitsNodes.getLength() ; i++ ){
			final Node unitNode = unitsNodes.item(i);
			if(unitNode.getNodeType() == Node.ELEMENT_NODE 
									&& unitNode.getNodeName().equals("unit")){
				final Element element = (Element) unitNode;
				UnitModel unitModel = unitModelFactory.createUnitModel(
														UnitType.valueOf(element.getAttribute("type")));
				unitModel.setId( Integer.valueOf( element.getAttribute( "id" ) ));
				//Sets unit row, columns position in the map, real pixel position is casted with the Map.class help
				unitModel.setPostion(Integer.valueOf( element.getAttribute( "coordx" ) ), 
									 Integer.valueOf( element.getAttribute( "coordy" ) ) );
				units.add(unitModel);
			}
		}
		return units;
	}
	
	public List<BuildingModel> parseBuildingResources( Node node){
		//BuildingModelFactory buildingModelFactory = new BuildingModelFactory();
		List<BuildingModel> buildings = new ArrayList<BuildingModel>();
		return buildings;
	}
}
