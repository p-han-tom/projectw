package com.mygdx.scenes;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.abilities.Ability;
import com.mygdx.abilities.Fireball;
import com.mygdx.abilities.range.RangeMeleeAdjacent;
import com.mygdx.entities.Unit;
import com.mygdx.entities.UnitRangeFinder.Pair;
import com.mygdx.game.Game;
import com.mygdx.maps.TileMap;

public class AbilityHUD {

	private Unit unit;
	private SpriteBatch batcher = new SpriteBatch();
	private FitViewport viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
	private Stage stage = new Stage(viewport, batcher);

	public Ability castingAbility = null;

	public ArrayList<Pair> canTargetAdj;
	
	private Ability test1 = new Fireball(new RangeMeleeAdjacent());
	private ImageButton atest1;

	public AbilityHUD(Unit unit) {
		this.unit = unit;
		Drawable drawable = new TextureRegionDrawable(test1.getIcon());
		atest1 = new ImageButton(drawable) {
			{
				setSize(100, 100);
				getImage().setFillParent(true);
				setPosition(Game.WIDTH-200, Game.HEIGHT-400);
				addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y) {
						if (castingAbility == null) {
							castingAbility = test1;
							canTargetAdj = new ArrayList<Pair>();
						}
						else {
							castingAbility = null;
							canTargetAdj = null;
						}
						System.out.println(castingAbility);
					}
				});
			}
		};
		

		stage.addActor(atest1);
		Gdx.input.setInputProcessor(stage);

	}

	public void draw() {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	public void displayRange(Unit cUnit, TileMap map, ShapeRenderer sr) {
		sr.begin(ShapeType.Filled);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		sr.setColor(new Color(1, 0, 0, 0.3f));
		for (Pair pair : castingAbility.range.canTarget) {
			canTargetAdj.add(new Pair(pair.y + cUnit.getCol(), pair.x+cUnit.getRow()));
			sr.rect((pair.y + cUnit.getCol())*map.tileDim+map.offsetX, (pair.x+cUnit.getRow())*map.tileDim+map.offsetY, map.tileDim, map.tileDim);
		}
		sr.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
	}

}
