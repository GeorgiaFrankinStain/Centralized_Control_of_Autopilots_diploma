package com.alamutra.ccoa;

import com.alamutra.ccoa.libgdxView.GameScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends Game {

	private Screen gameScreen;

	@Override
	public void create() {
		this.gameScreen = new GameScreen();
		setScreen(this.gameScreen);
	}
}
