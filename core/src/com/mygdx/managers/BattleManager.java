package com.mygdx.managers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.abilities.Ability;
import com.mygdx.entities.Trap;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.maps.TileMap;
import com.mygdx.scenes.HUD;
import com.mygdx.trees.skills.Indomitable;
import com.mygdx.trees.skills.Skill;
import com.mygdx.trees.skills.Zeal;
import com.mygdx.ui.FadingMessage;

public class BattleManager {

	public TileMap map;
	public LinkedList<Unit> units = new LinkedList<Unit>();
	public Unit cUnit;
	public int round = 1;
	public LinkedList<Unit> nextUnits = new LinkedList<Unit>();
	public List<Trap> traps;
	public boolean abilitySelected = false;
	public boolean abilityIsDrawing = false;
	public boolean canMove = true;
	public boolean canCast = true;

	private SpriteBatch batcher = new SpriteBatch();
	private ShapeRenderer sr = new ShapeRenderer();

	private FadingMessage beforeTurnSkills;
	private FadingMessage afterTurnSkills;
	private String skillActivationMessage = "";
	
	private boolean currentTurnStarted = false;
	private Ability animatedAbility;

	public BattleManager(TileMap map, List<Unit> units, List<Trap> traps) {
		this.map = map;
		this.traps = traps;		

		for (Unit unit : units) {
			nextUnits.add(unit);
			for (Skill skill : unit.skills) {
				skill.setCurrentCombat(this);
			}
		}

		this.units = TurnManager.newTurnOrder(nextUnits);
		cUnit = this.units.poll();
		getNextRange();
	}

	public void draw() {
		if (!abilitySelected && canMove) cUnit.rangeFinder.displayRange(map.offsetX, map.offsetY, map.tileDim);
		map.draw();
		for (Unit unit : units) unit.draw(batcher, map);
		for (Unit unit : nextUnits) unit.draw(batcher, map);
		for (Trap trap : traps) trap.draw(batcher, map);
		
		if (abilityIsDrawing) {
			if (animatedAbility.finishedDrawing) {
				abilityIsDrawing = false;
				animatedAbility.finishedDrawing = false;
			} else animatedAbility.draw();
		}
		
		if (beforeTurnSkills != null) beforeTurnSkills.draw(batcher, sr);
		if (afterTurnSkills != null) afterTurnSkills.draw(batcher, sr);
	}

	public void handleTurn(int mRow, int mCol, HUD hud) {
		beforeTurnSkills();
		
		//Checks if an ability has not been selected and if the current unit can move. If so, the unit moves to the selected space.
		if (!abilitySelected && canMove) {
			if (MouseButtons.isLeftPressed()) {
				if (mRow < map.length && mRow >= 0 && mCol < map.width && mCol >= 0 && map.getTile(mRow, mCol).passable) {
					if (!cUnit.rangeFinder.inRange(mRow, mCol)) return;

					cUnit.move(mRow, mCol);
					canMove = false;
				}
			}
		}
		
		//checks if an ability has been selected and the current unit can use an ability. If so, the unit casts an ability.
		else if (abilitySelected && canCast){
			if (MouseButtons.isLeftPressed()) {
				if (mRow < map.length && mRow >= 0 && mCol < map.width && mCol >= 0) {
					if (!hud.getCurrentAbility().range.inRange(mRow, mCol)) return;
					
					hud.getCurrentAbility().effect(mRow, mCol, this);
					
					animatedAbility = hud.getCurrentAbility();
					animatedAbility.drawLocation(cUnit.getCol()*map.tileDim+map.offsetX, cUnit.getRow()*map.tileDim+map.offsetY,
							mCol*map.tileDim+map.offsetX, mRow*map.tileDim+map.offsetY);
					
					abilityIsDrawing = true;

					hud.clearRange();
					canCast = false;
					hud.abilityUsed = true;
				}
			}
		}
	}

	//finds the movement range of the current unit and stores it
	private void getNextRange() {
		//exclude occupied tiles from the current unit's movement range and find the new range
		for (Unit unit : nextUnits) map.getTile(unit.getRow(), unit.getCol()).isOccupied = true;
		map.getTile(cUnit.getRow(), cUnit.getCol()).isOccupied = false;
		
		cUnit.rangeFinder.findRange(map, cUnit.getRow(), cUnit.getCol(), cUnit.attribute.moves);
	}
	//activates skills that activate at the start of a unit's turn
	private void beforeTurnSkills() {
		if (!currentTurnStarted) {
			for (Skill skill : cUnit.skills) {
				if (skill.atTurnStart()) {
					if (skill.activationCondition()) {
						skill.effect();
						skillActivationMessage += "- " + skill.getActivation() + "\n";
					}
				}
			}

			beforeTurnSkills = new FadingMessage(cUnit.getCol()*map.tileDim+map.offsetX,(int) (cUnit.getRow()*map.tileDim+map.offsetY+map.tileDim*1.2), skillActivationMessage);
			skillActivationMessage = "";

			currentTurnStarted = true;
		}
	}
	//clears the current unit and finds the next unit/next turn order
	//also activates any skills that occur at the end of a unit's turn
	public void getNextTurn() {
		canMove = true;
		canCast = true;
		cUnit.rangeFinder.clearRange();
		for (Skill skill : cUnit.skills) {
			if (skill.activationCondition()) {
				skill.effect();
				skillActivationMessage += "- " + skill.getActivation() + "\n";
			}
		}
		
		afterTurnSkills = new FadingMessage(cUnit.getCol()*cUnit.getUnitDim()+map.offsetX,
				(int) (cUnit.getRow()*map.tileDim+map.offsetY+map.tileDim*1.2), skillActivationMessage);
		
		skillActivationMessage = "";

		if (units.isEmpty()) {
			units = TurnManager.newTurnOrder(nextUnits);
			round ++;
		}

		cUnit = ((LinkedList<Unit>) units).poll();
		getNextRange();
		currentTurnStarted = false;
	}
}
