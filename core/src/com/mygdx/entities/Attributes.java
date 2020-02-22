package com.mygdx.entities;

public class Attributes {
		
	//base stats
	protected int strength, arcana, constitution, finesse;
	
	//strength, arcana, constitution, and finesse modifiers are based of the class
	protected double strengthMod = 1, arcanaMod = 1, constitutionMod = 1, finesseMod = 1;
	
	//sub stats
	public int physDmgMod, magDmgMod, hpMod, initiative, cdr, physResMod, magResMod, moves, evasion;
		
	
	public Attributes(int strength, double strengthMod, int arcana, double arcanaMod, int constitution, double constitutionMod, int finesse, double finesseMod) {
		this.strength = ((int) (strength * strengthMod));
		this.arcana = ((int) (arcana * arcanaMod));
		this.constitution = ((int) (constitution * constitutionMod));
		this.finesse = ((int) (finesse * finesseMod));
				
		physDmgMod = strength/4;
		magDmgMod = arcana/4;
		hpMod = constitution/3;
		initiative = finesse/5;
		cdr = (arcana/5) * 3;
		physResMod = strength/5;
		magResMod = arcana/5;
		moves = finesse/5;
		evasion = finesse/3;	
	}

}
