package com.mygdx.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.mygdx.entities.Unit;

public abstract class TurnManager {
	public static Unit currentTurn;
	
	public static List<Unit> newTurnOrder(List<Unit> units) {
		List<Unit> turnOrder = new ArrayList<Unit>();
		HashMap<Integer, Unit> initiativeAndUnitMap = new HashMap<Integer, Unit>();
		List<Integer> initiativeRolls = new ArrayList<Integer>();
		
		for (Unit unit:units) {
			int roll = unit.rollInitiative();
			while (initiativeRolls.contains(roll)) {
				roll = unit.rollInitiative();
			}
			initiativeRolls.add(roll);
			initiativeAndUnitMap.put(roll, unit);
		}
		Collections.sort(initiativeRolls);
		for (Integer roll:initiativeRolls) {
			turnOrder.add(initiativeAndUnitMap.get(roll));
		}
		return turnOrder;
	}
}
