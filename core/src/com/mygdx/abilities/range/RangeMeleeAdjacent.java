package com.mygdx.abilities.range;

import java.util.ArrayList;

import com.mygdx.entities.UnitRangeFinder.Pair;

public class RangeMeleeAdjacent extends Range{
	public RangeMeleeAdjacent() {
		canTarget.add(new Pair(1, 0));
		canTarget.add(new Pair(0, 1));
		canTarget.add(new Pair(-1, 0));
		canTarget.add(new Pair(0, -1));
	}
}
