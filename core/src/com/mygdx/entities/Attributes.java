package com.mygdx.entities;

public class Attributes {
		
	//base stats
	protected int strength, arcana, constitution, finesse;
	
	//sub stats
	public int physDmgMod, magDmgMod, maxHP, initiative, maxMP, physRes, magRes, moves, evasion;
		
	
	public Attributes(int strength, int arcana, int constitution, int finesse) {
		physDmgMod = strength / 4;
		magDmgMod = arcana / 4;
		maxHP = constitution * 3;
		maxMP = arcana * 3;
		initiative = finesse / 5;
		physRes = strength / 5;
		magRes = arcana / 5;
		moves = finesse / 5;
		evasion = finesse / 3;	
	}

}
