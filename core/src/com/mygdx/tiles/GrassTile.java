package com.mygdx.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//see comments for abstract class Tile
public class GrassTile extends Tile {

	public GrassTile(int tileDim, int offx, int offy) {
		super(tileDim, offx, offy);
		sprite = new Sprite(new TextureRegion(spritesheet, 6*spritedim+6, 0*spritedim, spritedim, spritedim));
		sprite.setSize(tileDim, tileDim);
		passable = true;
		movement = 0;
	}

	@Override
	public void render(SpriteBatch batch, int row, int col) {
		batch.begin();
		sprite.setPosition(col*tileDim+offx, row*tileDim+offy);
		sprite.draw(batch);
		batch.end();
	}

}
