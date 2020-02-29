package com.mygdx.abilities.range;

import java.util.ArrayList;

import com.mygdx.entities.UnitRangeFinder.Pair;

public abstract class Range {
	// Arraylist of targetable spaces relative to unit position
	public ArrayList<Pair> canTarget = new ArrayList<Pair>();
	public Range() {
		
	}
}
