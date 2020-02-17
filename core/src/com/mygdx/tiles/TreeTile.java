package com.mygdx.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//see comments for abstract class Tile
public class TreeTile extends Tile {

	public TreeTile(int tileDm, int offx, int offy) {
		super(tileDm, offx, offy);
		sprite = new Sprite(new TextureRegion(spritesheet, 0, 1*spritedim+1, spritedim, spritedim));
		sprite.setSize(tileDim, tileDim);
		passable = false;
		movement = 0;
	}
}
