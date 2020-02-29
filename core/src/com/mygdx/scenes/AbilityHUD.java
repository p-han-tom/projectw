package com.mygdx.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.abilities.Ability;
import com.mygdx.abilities.Fireball;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;

public class AbilityHUD {
	
	private Unit unit;
	private SpriteBatch batcher = new SpriteBatch();
	private FitViewport viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
	private Stage stage = new Stage(viewport, batcher);
	
	private Ability test1 = new Fireball();
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
						System.out.println("bruh");
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

}
