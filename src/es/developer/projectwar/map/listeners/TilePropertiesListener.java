package es.developer.projectwar.map.listeners;


import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;

import es.developer.projectwar.map.MapMatrix;


public class TilePropertiesListener implements ITMXTilePropertiesListener{

	private MapMatrix mapMatrix;
	private boolean[][] matrix;

	public TilePropertiesListener (MapMatrix mapMatrix){
		this.mapMatrix = mapMatrix;
		matrix = mapMatrix.getMapMatrix();
	}

	@Override
	public void onTMXTileWithPropertiesCreated(TMXTiledMap pTMXTiledMap,
			TMXLayer pTMXLayer, TMXTile pTMXTile,
			TMXProperties<TMXTileProperty> pTMXTileProperties) {

		if(pTMXTileProperties.containsTMXProperty("pared", "true")) {
			matrix[pTMXTile.getTileRow()][pTMXTile.getTileColumn()] = false;	
			mapMatrix.setMapMatrix(matrix);
		}

	}
}
