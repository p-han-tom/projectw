package com.mygdx.classes;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.abilities.Ability;

public abstract class Class {

	public List<Ability> abilities = new ArrayList<Ability>();
	
	private int level;
	private int experience;
	
	
	public Class() {
		
	}
	
}
