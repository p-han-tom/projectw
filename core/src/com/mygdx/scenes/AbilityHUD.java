package com.mygdx.scenes;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.abilities.Ability;
import com.mygdx.abilities.Fireball;
import com.mygdx.abilities.Slash;
import com.mygdx.abilities.range.AbilityRange;
import com.mygdx.entities.Unit;
import com.mygdx.entities.UnitRangeFinder.Pair;
import com.mygdx.game.Game;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;

public class AbilityHUD {

	private BattleManager combat;
	private SpriteBatch batcher = new SpriteBatch();
	private FitViewport viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
	private Stage stage = new Stage(viewport, batcher);

	
	private Ability test1 = new Slash();
	private boolean activated = false;

	public Ability castingAbility = null;

	public ArrayList<Pair> canTargetAdj;

	private Button atest1;

	private ShapeRenderer sr = new ShapeRenderer();
	
	public AbilityHUD(BattleManager combat) {
		this.combat = combat;

		Drawable drawable1 = new TextureRegionDrawable(test1.getIcon());
		sr.setAutoShapeType(true);
		test1.range.createMapContext(combat.map);
		test1.range.buildRange(combat.getCurrentUnit().getRow(), combat.getCurrentUnit().getCol(), test1.getAbilityRange());
		System.out.println(test1.range.canTarget);
		

		atest1 = new Button(drawable1) {
			{
				setSize(100, 100);
				setPosition(Game.WIDTH-200, Game.HEIGHT-400);
				addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y) {
						activated = !activated;

					}
				});
			}
		};

		stage.addActor(atest1);
		Gdx.input.setInputProcessor(stage);
	}
	
	public void update(Unit unit) {
		test1.range.reset();
		test1.range.buildRange(unit.getRow(), unit.getCol(), test1.getAbilityRange());
	}

	public void draw() {
		stage.draw();
		if (activated) {
			sr.begin(ShapeType.Filled);
			Gdx.gl.glEnable(GL30.GL_BLEND);
			Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
			sr.setColor(new Color(1,0,0,0.3f));
			sr.rect(Game.WIDTH-200, Game.HEIGHT-400, 100,100);
			sr.end();
			Gdx.gl.glDisable(GL30.GL_BLEND);
			test1.range.draw();
		}
	}


}
