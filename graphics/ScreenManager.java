package MiSS.graphics;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import MiSS.exceptions.ScreenManagerException;

public final class ScreenManager {
	
	private static final float fadeOutDuration = .5f;
	private static ScreenManager instance;
	private Game game;

	private ScreenManager() {
	}
	
	public static ScreenManager getInstance() {
		if(ScreenManager.instance == null) {
			ScreenManager.instance = new ScreenManager();
		}
		return ScreenManager.instance;
	}
	
	public void initialize(Game game, Screen startScreen) throws ScreenManagerException {
		if(ScreenManager.getInstance().getGame() != null) {
			throw new ScreenManagerException("already initialized");
		}
		this.setGame(game);
		this.getGame().setScreen(startScreen);
	}
	
	public void changeScreen(final ScreenWithStage previousScreen, final ScreenWithStage nextScreen) {
		previousScreen.getStage().addAction(Actions.sequence(
				Actions.fadeOut(ScreenManager.fadeOutDuration),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						ScreenManager.this.getGame().setScreen(nextScreen);
						previousScreen.dispose();
					}
				})));
	}
	
	private Game getGame() {
		return game;
	}

	private void setGame(Game game) {
		this.game = game;
	}
	
	
	
}
