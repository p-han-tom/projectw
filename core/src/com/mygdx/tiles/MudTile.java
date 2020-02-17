package com.mygdx.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MudTile extends Tile{

	public MudTile(int tileDim, int offx, int offy) {
		super(tileDim, offx, offy);
		sprite = new Sprite(new TextureRegion(spritesheet, 4*spritedim+4, 0*spritedim, spritedim, spritedim));
		sprite.setSize(tileDim, tileDim);
		passable = true;
		movement = 2;
	}
	
}
