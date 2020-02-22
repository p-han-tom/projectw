package com.mygdx.managers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.entities.Trap;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.maps.TileMap;
import com.mygdx.trees.skills.Indomitable;
import com.mygdx.trees.skills.Skill;
import com.mygdx.trees.skills.Zeal;
import com.mygdx.ui.FadingMessage;

public class BattleManager {
	
	public TileMap map;
	public LinkedList<Unit> units;
	public List<Unit> nextUnits = new LinkedList<Unit>();
	public List<Trap> traps;
	
	private SpriteBatch batcher = new SpriteBatch();
	private ShapeRenderer sr = new ShapeRenderer();
	
	private Unit cUnit;
	private FadingMessage fadeTextBox;
	private String message = "";
	
	public BattleManager(TileMap map, List<Unit> units, List<Trap> traps) {
		this.map = map;
		this.units = TurnManager.newTurnOrder(units);
		this.traps = traps;		
		for (Unit unit : units) {
			nextUnits.add(unit);
			unit.skills.add(new Indomitable(this));
			unit.skills.add(new Zeal(this));
		}
		cUnit = ((LinkedList<Unit>) units).poll();
		getNextRange();
	}
	
	public void draw() {
		cUnit.rangeFinder.displayRange(map.offsetX, map.offsetY, map.tileDim);
		map.draw();
		for (Unit unit : units) unit.draw(batcher, map);
		for (Unit unit : nextUnits) unit.draw(batcher, map);
		for (Trap trap : traps) trap.draw(batcher, map);
		if (fadeTextBox != null) fadeTextBox.draw(batcher, sr);
	}
	
	public void handleTurn(int mRow, int mCol) {
		if (MouseButtons.isLeftPressed()) {
			
			if (mRow < map.length && mRow >= 0 && mCol < map.width && mCol >= 0 && map.getTile(mRow, mCol).passable) {
				
				if (!cUnit.rangeFinder.inRange(mRow, mCol)) return;
				
				cUnit.move(mRow, mCol);
				
				for (Skill skill : cUnit.skills) {
					if (skill.activationCondition()) {
						
						//if a skill is a special skill, then it will be handled here
						if (skill.isSpecial()) {
							skill.effect();
						}	
						skill.effect();
						message += "- " + skill.getActivation() + "\n";
					}
				}
				fadeTextBox = new FadingMessage(MouseButtons.getX()+cUnit.getUnitDim()/2,
						Game.HEIGHT-MouseButtons.getY()+cUnit.getUnitDim(), message);				
				cUnit.rangeFinder.clearRange();
				message = "";
				
				cUnit = ((LinkedList<Unit>) units).poll();
				
				if (units.size() == 0) units = TurnManager.newTurnOrder(nextUnits);
				getNextRange();
			}
		}
	}
	
	//getters
	public void getNextRange() {
		//exclude occupied tiles from the current unit's movement range and find the new range
		for (Unit unit : units) map.getTile(unit.getRow(), unit.getCol()).isOccupied = true;
		map.getTile(cUnit.getRow(), cUnit.getCol()).isOccupied = false;
		cUnit.rangeFinder.findRange(map, cUnit.getRow(), cUnit.getCol(), cUnit.attribute.moves);
	}
	
	public Unit getCurrentUnit() {return cUnit;}
}
