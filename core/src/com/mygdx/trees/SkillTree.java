package com.mygdx.trees;

import com.mygdx.trees.skills.Skill;

public abstract class SkillTree {
	
	protected int skillPoints;
	protected Skill baseSkill;
	protected Skill[] path1 = new Skill[2];
	protected Skill[] path2 = new Skill[5];
	protected Skill[] path3 = new Skill[4];
	
	public SkillTree(int level) {
		this.skillPoints = level;
	}
	
}
