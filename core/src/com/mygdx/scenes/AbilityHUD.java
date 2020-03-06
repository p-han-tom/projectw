package com.mygdx.scenes;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.abilities.Ability;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.managers.BattleManager;

public class AbilityHUD {

	private BattleManager combat;
	private SpriteBatch batcher = new SpriteBatch();
	private ShapeRenderer sr = new ShapeRenderer() {
		{
			setAutoShapeType(true);
		}
	};
	
	private FitViewport viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
	private Stage stage = new Stage(viewport, batcher);

	private List<Ability> abilityList;
	private List<Button> abilityButtonList = new ArrayList<Button>();
	private boolean activated = false;
	private int buttonIndex;
	private Table table;
	
	public AbilityHUD(BattleManager combat) {
		this.combat = combat;
		abilityList = combat.getCurrentUnit().abilities;		
		table = new Table();
		for (int i = 0; i < abilityList.size(); i ++) {
			final int index = i;
			abilityList.get(i).range.createMapContext(combat.map);
			abilityList.get(i).range.buildRange(combat.getCurrentUnit().getRow(), combat.getCurrentUnit().getCol(), abilityList.get(i).getAbilityRange());
			abilityButtonList.add(new Button(new TextureRegionDrawable(abilityList.get(i).getIcon())) {
				{
					setSize(60,60);
					addListener(new ClickListener() {
						public void clicked(InputEvent event, float x, float y) {
							activated = !activated;
							buttonIndex = index;
						}
					});
				}
			});
		}
		table.setPosition(Game.WIDTH-130, 425);
		table.add(abilityButtonList.get(0)).width(60).height(60).pad(15);
		table.add(abilityButtonList.get(1)).width(60).height(60).pad(15);
		stage.addActor(table);
//		for (Button button : abilityButtonList) stage.addActor(button);
		Gdx.input.setInputProcessor(stage);
	}
	
	public void showNextUnit(Unit unit) {
		for (Ability ability : abilityList) {
			ability.range.reset();
			ability.range.buildRange(unit.getRow() ,unit.getCol(), ability.getAbilityRange());
		}
	}
	
	public void update() {
		combat.flickHud(activated);
	}
	
	public void dispose() {
		activated = false;
	}
	
	public Ability getCurrentAbility() {
		return abilityList.get(buttonIndex);
	}

	public void draw() {
		stage.draw();
		if (activated) {
			sr.begin(ShapeType.Filled);
			Gdx.gl.glEnable(GL30.GL_BLEND);
			Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
			sr.setColor(new Color(1,0,0,0.3f));
			sr.rect(abilityButtonList.get(buttonIndex).getX()+table.getX(), abilityButtonList.get(buttonIndex).getY()+table.getY(), abilityButtonList.get(buttonIndex).getWidth(),abilityButtonList.get(buttonIndex).getHeight());
			sr.end();
			Gdx.gl.glDisable(GL30.GL_BLEND);
			abilityList.get(buttonIndex).range.draw();
		}
	}
}
