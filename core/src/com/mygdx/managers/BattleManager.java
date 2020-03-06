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
import com.mygdx.scenes.AbilityHUD;
import com.mygdx.trees.skills.Indomitable;
import com.mygdx.trees.skills.Skill;
import com.mygdx.trees.skills.Zeal;
import com.mygdx.ui.FadingMessage;

public class BattleManager {

	public TileMap map;
	public LinkedList<Unit> units = new LinkedList<Unit>();
	public  LinkedList<Unit> nextUnits = new LinkedList<Unit>();
	public List<Trap> traps;
	public boolean hudIsOpen = false;

	private SpriteBatch batcher = new SpriteBatch();
	private ShapeRenderer sr = new ShapeRenderer();

	private Unit cUnit;
	private FadingMessage beforeActivation;
	private FadingMessage afterActivation;
	private String message = "";
	private int round = 1;
	private boolean currentTurnStarted = false;

	private AbilityHUD abilityHUD;

	public BattleManager(TileMap map, List<Unit> units, List<Trap> traps) {
		this.map = map;
		this.traps = traps;		

		for (Unit unit : units) {
			nextUnits.add(unit);
			unit.skills.add(new Indomitable(this));
			unit.skills.add(new Zeal(this));
		}


		this.units = TurnManager.newTurnOrder(nextUnits);
		cUnit = this.units.poll();
		getNextRange();

		abilityHUD = new AbilityHUD(this);
	}

	public void draw() {
		if (!hudIsOpen) cUnit.rangeFinder.displayRange(map.offsetX, map.offsetY, map.tileDim);
		map.draw();
		for (Unit unit : units) unit.draw(batcher, map);
		for (Unit unit : nextUnits) unit.draw(batcher, map);
		for (Trap trap : traps) trap.draw(batcher, map);
		if (beforeActivation != null) beforeActivation.draw(batcher, sr);
		if (afterActivation != null) afterActivation.draw(batcher, sr);
		abilityHUD.draw();
	}
	
	public void update() {
		abilityHUD.update();
	}

	public void handleTurn(int mRow, int mCol) {
		beforeTurnSkills();
		if (!hudIsOpen) {
			//If a left click is received, the unit is moving
			if (MouseButtons.isLeftPressed()) {

				if (mRow < map.length && mRow >= 0 && mCol < map.width && mCol >= 0 && map.getTile(mRow, mCol).passable) {
					if (!cUnit.rangeFinder.inRange(mRow, mCol)) return;

					cUnit.move(mRow, mCol);

					for (Skill skill : cUnit.skills) {
						if (skill.activationCondition()) {
							skill.effect();
							message += "- " + skill.getActivation() + "\n";
						}
					}

					getNextTurn();
				}
			}
		} else {
			if (MouseButtons.isLeftPressed()) {
				
				
				
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
						message += "- " + skill.getActivation() + "\n";
					}
				}
			}

			beforeActivation = new FadingMessage(cUnit.getCol()*map.tileDim+map.offsetX,(int) (cUnit.getRow()*map.tileDim+map.offsetY+map.tileDim*1.2), message);
			message = "";

			currentTurnStarted = true;
		}
	}
	//clears the current unit and finds the next unit/next turn order
	private void getNextTurn() {
		afterActivation = new FadingMessage(MouseButtons.getX()-cUnit.getUnitDim(),
				Game.HEIGHT-MouseButtons.getY()+cUnit.getUnitDim(), message);			

		cUnit.rangeFinder.clearRange();
		message = "";

		if (units.isEmpty()) {
			units = TurnManager.newTurnOrder(nextUnits);
			round ++;
		}

		cUnit = ((LinkedList<Unit>) units).poll();
		abilityHUD.showNextUnit(cUnit);
		getNextRange();
		currentTurnStarted = false;
	}

	public Unit getCurrentUnit() {return cUnit;}
	public int getRound() {return round;}
	public void flickHud(boolean activation) {hudIsOpen = activation;}
}
