package es.developer.projectwar.utils;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import es.developer.projectwar.views.sprites.UnitSprite;

public class TextureRegionFactory {

	private BaseGameActivity activity;
	private List<BitmapTextureAtlas> bitmaps;
	//Static reference to the tile view resource
	private static String TILE_RESOURCE = "p1_tile.png";
	
	public TextureRegionFactory(BaseGameActivity activity){
		this.activity = activity;
		bitmaps = new ArrayList<BitmapTextureAtlas>();
		bitmaps.add(this.createTextureFactory(128, 128));
		bitmaps.add(this.createTextureFactory(512, 512));
		bitmaps.add(this.createTextureFactory(512, 512));
	}
	
	private BitmapTextureAtlas createTextureFactory(int width, int height){
		BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), width, height, TextureOptions.DEFAULT);
		bitmapTextureAtlas.load();
		return bitmapTextureAtlas;
	}
	
	private ITextureRegion createTextureRegion(String resource, TextureType texture){
		ITextureRegion textureRegion = null;
		switch(texture){
		case map:
			break;
		case player:
			textureRegion = BitmapTextureAtlasTextureRegionFactory
			.createFromAsset(bitmaps.get(0), activity, resource, 0, 0);
			break;
		case unit:
			textureRegion = BitmapTextureAtlasTextureRegionFactory
			.createTiledFromAsset(bitmaps.get(1), activity, resource, 0, 0, 3, 4);
			break;
		case tile:
			textureRegion = BitmapTextureAtlasTextureRegionFactory
			.createFromAsset(bitmaps.get(2), activity, "p1_tile.png", 0, 0);
			break;
		default:
			break;
		}
		return textureRegion;
	}
	
	public IShape createSprite(String resource, TextureType texture){
		IShape sprite = null;
		ITextureRegion textureRegion;
		switch(texture){
		case map:
			break;
		case player:
			textureRegion = this.createTextureRegion(resource, texture);
			sprite = (new Sprite(32,32, textureRegion, activity.getVertexBufferObjectManager()));
			break;
		case unit:
			textureRegion = this.createTextureRegion(resource, texture);
			sprite = new UnitSprite(0, 0, (ITiledTextureRegion)textureRegion, activity.getVertexBufferObjectManager());
			break;
		case tile:
			//Gets the static reference for the resource
			textureRegion = this.createTextureRegion(TILE_RESOURCE, texture);
			sprite = new Sprite(0, 0, textureRegion, activity.getVertexBufferObjectManager());
			break;
		default:
			break;
		}
		return sprite;
	}
	
	public enum TextureType{
		unit,
		player,
		tile,
		map;
	}
}
