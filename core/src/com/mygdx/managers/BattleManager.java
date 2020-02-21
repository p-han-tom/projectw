package com.mygdx.managers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entities.Trap;
import com.mygdx.entities.Unit;
import com.mygdx.maps.TileMap;

public class BattleManager {
	
	public TileMap map;
	public List<Unit> units;
	public List<Trap> traps;
	
	private SpriteBatch batcher = new SpriteBatch();
	private int current = 0;
	private Unit cUnit;
	
	public BattleManager(TileMap map, List<Unit> units, List<Trap> traps) {
		this.map = map;
		this.units = TurnManager.newTurnOrder(units);
		this.traps = traps;		
	}
	
	public Unit getCurrentUnit() {return units.get(current);}
	
	public void draw() {
		cUnit = units.get(current);
		
		//get current unit's range of movement
		if (cUnit.movement.range.isEmpty()) {
			//prevent unit collision
			for (Unit unit : units) map.getTile(unit.getRow(), unit.getCol()).isOccupied = true;
			map.getTile(cUnit.getRow(), cUnit.getCol()).isOccupied = false;
			cUnit.movement.findRange(map, cUnit.getRow(), cUnit.getCol(), cUnit.getMovement());
		}
		
		//display objects
		cUnit.movement.displayRange(map.offsetX, map.offsetY, map.tileDim);
		map.draw();
		for (Unit unit : units) unit.draw(batcher, map);
		for (Trap trap : traps) trap.draw(batcher, map);
	}
	
	
	public void handleTurn(int mRow, int mCol) {
		
		if (MouseButtons.isLeftPressed()) {
			
			if (mRow < map.length && mRow >= 0 && mCol < map.width && mCol >= 0 && map.getTile(mRow, mCol).passable) {
				if (!cUnit.movement.inRange(mRow, mCol)) return;
				cUnit.move(mRow, mCol);
				current++;
				
				//if current unit is the last unit in the turn order, make a new turn order
				if (current == units.size()) {
					current = 0;
					units = TurnManager.newTurnOrder(units);
				}
				cUnit.movement.clearRange();
			}
			
		}
		
	}
}
