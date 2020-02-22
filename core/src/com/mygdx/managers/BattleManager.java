package com.mygdx.managers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.entities.Trap;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.maps.TileMap;
import com.mygdx.trees.skills.Indomitable;
import com.mygdx.trees.skills.Skill;
import com.mygdx.ui.FadingMessage;

public class BattleManager {
	
	public TileMap map;
	public List<Unit> units;
	public List<Trap> traps;
	
	private SpriteBatch batcher = new SpriteBatch();
	private ShapeRenderer sr = new ShapeRenderer();
	private int current = 0;
	private Unit cUnit;
	private FadingMessage message;
	
	public BattleManager(TileMap map, List<Unit> units, List<Trap> traps) {
		this.map = map;
		this.units = TurnManager.newTurnOrder(units);
		this.traps = traps;		
		for (Unit unit : units) unit.skills.add(new Indomitable(unit));
	}
	
	public Unit getCurrentUnit() {return units.get(current);}
	
	public void draw() {
		if (message != null) {
			System.out.println(cUnit.getName());
			message.draw(batcher, sr);
		}
		cUnit = units.get(current);
		
		//get current unit's range of movement
		if (cUnit.movement.range.isEmpty()) {
			//prevent unit collision
			for (Unit unit : units) map.getTile(unit.getRow(), unit.getCol()).isOccupied = true;
			map.getTile(cUnit.getRow(), cUnit.getCol()).isOccupied = false;
			cUnit.movement.findRange(map, cUnit.getRow(), cUnit.getCol(), cUnit.attribute.moves);
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
				for (Skill skill : cUnit.skills) {
					if (skill.activationCondition()) {
						skill.effect();
						message = new FadingMessage(MouseButtons.getX()+cUnit.getUnitDim()/2, Game.HEIGHT-MouseButtons.getY()+cUnit.getUnitDim(), "INDOMITABLE ACTIVATED");
					}
				}
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
