package com.mygdx.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.mygdx.entities.Unit;

public abstract class TurnManager {
	public static Unit currentTurn;
	public static ArrayList<Unit> newTurnOrder(ArrayList<Unit> units) {
		ArrayList<Unit> turnOrder = new ArrayList<Unit>();
		HashMap<Integer, Unit> initiativeAndUnitMap = new HashMap<Integer, Unit>();
		ArrayList<Integer> initiativeRolls = new ArrayList<Integer>();
		for (Unit unit:units) {
			int roll = unit.rollInitiative();
			initiativeRolls.add(roll);
			initiativeAndUnitMap.put(roll, unit);
		}
		Collections.sort(initiativeRolls);
		return turnOrder;
	}
}
