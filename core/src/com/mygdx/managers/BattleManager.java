package com.mygdx.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.entities.Unit;
import com.mygdx.entities.Unit.Pair;
import com.mygdx.maps.TileMap;

public class BattleManager {
	
	public TileMap map;
	public List<Unit> units;
	
	private SpriteBatch batcher = new SpriteBatch();
	private ShapeRenderer sr = new ShapeRenderer();
	private int current = 0;
	
	public BattleManager(TileMap map, List<Unit> units) {
		this.map = map;
		this.units = TurnManager.newTurnOrder(units);
		for (Unit unit : units) unit.mapContext(map.mapLength, map.mapWidth);
	}
	
	public Unit getCurrentUnit() {return units.get(current);}
	
	public void draw() {
		map.draw();
		for (Unit unit : units) unit.draw(batcher, map);
	}
	
	public void handleTurn(int mRow, int mCol) {
		
		if (MouseButtons.isLeftPressed()) {
			if (mRow < map.mapLength && mRow >= 0 && mCol < map.mapWidth && mCol >= 0 && map.getTile(mRow, mCol).passable) {
				Unit cUnit = units.get(current);
				
				cUnit.findRange(map, cUnit.getRow(), cUnit.getCol(), cUnit.getMovement()); 
				if (!cUnit.inRange(mRow, mCol)) return;
				
				cUnit.move(mRow, mCol);
				current++;
				
				if (current == units.size()) {
					current = 0;
					units = TurnManager.newTurnOrder(units);
				}
				
				cUnit.clearRange();
			}
		}
		
	}
}
