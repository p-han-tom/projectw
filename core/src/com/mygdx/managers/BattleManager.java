package com.mygdx.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entities.Unit;
import com.mygdx.maps.TileMap;

public class BattleManager {
	
	public TileMap map;
	public List<Unit> units;
	
	private SpriteBatch batcher = new SpriteBatch();
	private int unitTracker = 0;
	
	public BattleManager(TileMap map, List<Unit> units) {
		this.map = map;
		this.units = TurnManager.newTurnOrder(units);
	}
	
	public Unit getCurrentUnit() {
		return units.get(unitTracker);
	}
	
	public void draw() {
		map.draw();
		for (Unit unit : units) unit.draw(batcher, map);
	}
	
	public void handleTurn(int mRow, int mCol) {
		if (MouseButtons.isLeftPressed()) {
			if (mRow < map.mapLength && mRow >= 0 && mCol < map.mapWidth && mCol >= 0 ) {
				if (!map.getTile(mRow, mCol).passable) return;
				units.get(unitTracker).move(mRow, mCol);
				unitTracker++;
				if (unitTracker == units.size()) {
					unitTracker = 0;
					units = TurnManager.newTurnOrder(units);
				}
			}
		}
	}
}
