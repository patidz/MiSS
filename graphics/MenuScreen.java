package MiSS.graphics;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import MiSS.exceptions.SimulationParametersException;

public class MenuScreen implements ScreenWithStage {

	/**
	 * sample constants, to be changed
	 */
	private final int MIN_TREE_HEIGHT = 2;
	private final int MAX_TREE_HEIGHT = 10;
	private final int MIN_DISTRIBUTION = 0;
	private final int MAX_DISTRIBUTION = 2;
	private final int MIN_FOREST_SIZE = 100;
	private final int MAX_FOREST_SIZE = 1000;
	private final int MIN_RADIUS = 5;
	private final int MAX_RADIUS = 25;
    	public static int MIN_SPEED = 10;
	private final int MAX_SPEED = 50;
	
	private final SpriteBatch batch;
	private final Texture backgroundTexture;
	private final Stage stage;
	private final MenuActorsFactory menuActorsfactory;
	private final Skin skin;

	private final TextField maxTreeHeightField;
	private final TextField distributionField;
	private final TextField forestSizeField;
	private final TextField startingPointField;
	private final TextField radiusField;
	private final TextField speedField;
	
	private final Random random;
	
	public MenuScreen() {
		batch = new SpriteBatch();
		backgroundTexture = new Texture(Gdx.files.internal("./forest.jpg"));
		stage = new Stage();
		menuActorsfactory = new MenuActorsFactory(stage, 400, 35, 3);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		maxTreeHeightField = new TextField("random", skin);
		distributionField = new TextField("random", skin);
		forestSizeField = new TextField("random", skin);
		startingPointField = new TextField("random", skin);
		radiusField = new TextField("random", skin);
		speedField = new TextField("random", skin);
		
		random = new Random();
	}
	
	@Override
	public void show() {

		menuActorsfactory.addActor(
				new Label("max tree height("+MIN_TREE_HEIGHT+"-"+MAX_TREE_HEIGHT+")", skin), null);
		menuActorsfactory.addActor(maxTreeHeightField, null);

		menuActorsfactory.addActor(
				new Label("distribution("+MIN_DISTRIBUTION+"-"+MAX_DISTRIBUTION+")", skin), null);
		menuActorsfactory.addActor(distributionField, null);

		menuActorsfactory.addActor(
				new Label("forest size("+MIN_FOREST_SIZE+"-"+MAX_FOREST_SIZE+")", skin), null);
		menuActorsfactory.addActor(forestSizeField, null);

		menuActorsfactory.addActor(
				new Label("starting point(x;y)", skin), null);
		menuActorsfactory.addActor(startingPointField, null);

		menuActorsfactory.addActor(
				new Label("radius("+MIN_RADIUS+"-"+MAX_RADIUS+")", skin), null);
		menuActorsfactory.addActor(radiusField, null);

		menuActorsfactory.addActor(
				new Label("speed("+MIN_SPEED+"-"+MAX_SPEED+")", skin), null);
		menuActorsfactory.addActor(speedField, null);
		
		menuActorsfactory.addActor(new TextButton("begin", skin), new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				try {
					SimulationScreen simulationScreen = validateData();
					ScreenManager.getInstance().changeScreen(MenuScreen.this, simulationScreen);
				} catch (SimulationParametersException e) {
					alert(e.getMessage());
				}
			}
		});
		
		Gdx.input.setInputProcessor(getStage());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.begin();
		this.batch.draw(backgroundTexture, 0, 0);
		this.batch.end();
		this.menuActorsfactory.act(delta);
		this.getStage().act();
		this.getStage().draw();
	}
	
	private SimulationScreen validateData() throws SimulationParametersException {
		Integer maxTreeHeight;
		Integer distribution;
		Integer forestSize;
		Integer startingPointX = null, startingPointY = null;
		Integer radius;
		Integer speed;
		
		//max tree height
		try {
			String val = maxTreeHeightField.getText();
			if(val.equals("random")) {
				maxTreeHeight = random.nextInt(MAX_TREE_HEIGHT-MIN_TREE_HEIGHT)+MIN_TREE_HEIGHT;
			} else {
				maxTreeHeight = Integer.parseInt(val);
			}
			if(maxTreeHeight > MAX_TREE_HEIGHT || maxTreeHeight < MIN_TREE_HEIGHT) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new SimulationParametersException("invalid field: max tree height");
		}

		//distribution
		try {
			String val = distributionField.getText();
			if(val.equals("random")) {
				distribution = random.nextInt(MAX_DISTRIBUTION-MIN_DISTRIBUTION)+MIN_DISTRIBUTION;
			} else {
				distribution = Integer.parseInt(val);
			}
			if(distribution > MAX_DISTRIBUTION || distribution < MIN_DISTRIBUTION) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new SimulationParametersException("invalid field: distribution");
		}

		//forest size
		try {
			String val = forestSizeField.getText();
			if(val.equals("random")) {
				forestSize = random.nextInt(MAX_FOREST_SIZE-MIN_FOREST_SIZE)+MIN_FOREST_SIZE;
			} else {
				forestSize = Integer.parseInt(val);
			}
			if(forestSize > MAX_FOREST_SIZE || forestSize < MIN_FOREST_SIZE) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new SimulationParametersException("invalid field: forest size");
		}

		//starting point
		try {
			String val = startingPointField.getText();
			if(val.equals("random")) {
				startingPointX = random.nextInt(forestSize);
				startingPointY = random.nextInt(forestSize);
			} else {
				String[] valArr = val.split(";");
				if(valArr.length != 2) {
					throw new NumberFormatException();
				}
				startingPointX = Integer.parseInt(valArr[0]);
				startingPointY = Integer.parseInt(valArr[1]);
			}
			if(startingPointX > forestSize || startingPointX < 0) {
				throw new NumberFormatException();
			}
			if(startingPointY > forestSize || startingPointY < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new SimulationParametersException("invalid field: starting point");
		}

		//radius
		try {
			String val = radiusField.getText();
			if(val.equals("random")) {
				radius = random.nextInt(MAX_RADIUS-MIN_RADIUS)+MIN_RADIUS;
			} else {
				radius = Integer.parseInt(val);
			}
			if(radius > MAX_RADIUS || radius < MIN_RADIUS) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new SimulationParametersException("invalid field: radius");
		}

		//speed
		try {
			String val = speedField.getText();
			if(val.equals("random")) {
				speed = random.nextInt(MAX_SPEED-MIN_SPEED)+MIN_SPEED;
			} else {
				speed = Integer.parseInt(val);
			}
			if(speed > MAX_SPEED || speed < MIN_SPEED) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new SimulationParametersException("invalid field: speed");
		}
		
		return new SimulationScreen(maxTreeHeight, distribution, forestSize, startingPointX, 
				startingPointY, radius, speed);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Stage getStage() {
		return stage;
	}
	
	public void alert(String info) {
		this.alert(info, (int)(Gdx.graphics.getWidth()*.9f), 150);
	}
	
	public void alert(String info, int width, int height) {
		Dialog dialog = new Dialog("info", this.skin);
		dialog.text(info);
		dialog.button("OK");
		dialog.setBounds(
				Gdx.graphics.getWidth()/2-width/2, 
				Gdx.graphics.getHeight()/2-height/2, 
				width, 
				height);
		this.getStage().addActor(dialog);
	}

}
