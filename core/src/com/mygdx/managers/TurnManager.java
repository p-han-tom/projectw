package com.mygdx.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.mygdx.entities.Unit;

public abstract class TurnManager {
	public static Unit currentTurn;
	
	// Kinda shitty thing that will roll initiative for each unit and return an array with the units in their turn order
	// Currently doesn't account for initiative bonus (?) because units dont have stats yet 
	public static LinkedList<Unit> newTurnOrder(List<Unit> units) {
		LinkedList<Unit> turnOrder = new LinkedList<Unit>();
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
		Collections.reverse(initiativeRolls);
		for (Integer roll:initiativeRolls) {
			turnOrder.add(initiativeAndUnitMap.get(roll));
		}
		
		return turnOrder;
	}
}
