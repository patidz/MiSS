package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import exceptions.ScreenManagerException;

public class Game extends com.badlogic.gdx.Game {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	@Override
	public void create () {
		try {
			Gdx.graphics.setContinuousRendering(false);
			System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
			ScreenManager.getInstance().initialize(this, new MenuScreen());
		} catch (ScreenManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose () {
	}
}