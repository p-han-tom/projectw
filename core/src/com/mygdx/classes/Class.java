package com.mygdx.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mygdx.abilities.Ability;
import com.mygdx.entities.Unit;

public abstract class Class {

	public List<Ability> abilities = new ArrayList<Ability>();
		
	//base stats
	protected int strength, arcana, constitution, finesse;
	
	//strength, arcana, constitution, and finesse modifiers are based of the class
	protected int strengthMod, arcanaMod, constitutionMod, finesseMod;
	
	//sub stats
	//most of these substats are modifiers since items do a majority of the damage
	//every 4 points of strength or arcana increases physical or magical damage by 1
	//every 3 points of constitution increases hp by 1
	//every 5 points of finesse increases initiative by 1
	//every 5 points of arcana increases cooldown reduction by 3%
	//every 5 points of strength increases physical resistance by 1
	//every 5 points of arcana increases magical resistance by 1
	//every 5 points of finesse increases moves by 1
	//every 3 points of finesse increases evasion by 1
	//debuff resistance to be implemented with constitution modifiers
	protected int physDmgMod, magDmgMod, hpMod, initiative, cdr, physResMod, magResMod, moves, evasion;
	
	//xp requirements <level, xp requirement to next level>
	Map<Integer, Integer> levelUpReq = new HashMap<Integer, Integer>() {{
		put(1, 20);
		put(2, 40);
		put(3, 80);
		put(4, 120);
		put(5, 180);
		put(6, 240);
		put(7, 320);
		put(8, 400);
		put(9, 500);
		put(10, 600);
	}};
	
	public Class(int strength, int arcana, int constitution, int finesse) {
		this.strength = strength + strengthMod;
		this.arcana = arcana + arcanaMod;
		this.constitution = constitution + constitutionMod;
		this.finesse = finesse + finesseMod;
		
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

	public int getFinesse() {
		return finesse;
	}
	public void setFinesse(int finesse) {
		this.finesse = finesse;
	}
	public int getConstitution() {
		return constitution;
	}
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
	public int getArcana() {
		return arcana;
	}
	public void setArcana(int arcana) {
		this.arcana = arcana;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
}
