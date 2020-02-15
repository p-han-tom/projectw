package com.mygdx.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GrassTile extends Tile {

	public GrassTile(int row, int col) {
		super(row, col);
		sprite = new Sprite(new TextureRegion(spritesheet, 6*spritedim+6, 0*spritedim, spritedim, spritedim));
		sprite.setSize(tileDim, tileDim);
		passable = true;
		movement = 0;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		sprite.setPosition(col*tileDim+offx, row*tileDim+offy);
		sprite.draw(batch);
		batch.end();
	}

}
