package MiSS.graphics;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class SimulationScreen implements ScreenWithStage {

	private final Stage stage;
	private final Integer maxTreeHeight;
	private final Integer distribution;
	private final Integer forestSize;
	private final Integer startingPointX;
	private final Integer startingPointY;
	private final Integer radius;
	private final Integer speed;
	
	public SimulationScreen(Integer maxTreeHeight, Integer distribution, Integer forestSize, 
			Integer startingPointX,	Integer startingPointY, Integer radius, Integer speed) {
		stage = new Stage();
		this.maxTreeHeight = maxTreeHeight;
		this.distribution = distribution;
		this.forestSize = forestSize;
		this.startingPointX = startingPointX;
		this.startingPointY = startingPointY;
		this.radius = radius;
		this.speed = speed;
		
		System.out.println("starting simulation with parameters:");
		System.out.println("max tree height " + this.maxTreeHeight);
		System.out.println("distribution " + this.distribution);
		System.out.println("forest size " + this.forestSize);
		System.out.println("starting x " + this.startingPointX);
		System.out.println("starting y " + this.startingPointY);
		System.out.println("radius " + this.radius);
		System.out.println("speed " + this.speed);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
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
	
	
	
}
