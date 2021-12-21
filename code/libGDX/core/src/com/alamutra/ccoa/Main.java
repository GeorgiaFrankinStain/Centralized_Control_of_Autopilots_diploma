package com.alamutra.ccoa;

import com.alamutra.ccoa.Logic.GameTime;
import com.alamutra.ccoa.Logic.GameTimeClass;
import com.alamutra.ccoa.libgdxView.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Main extends Game {

	private Screen gameScreen;

	@Override
	public void create() {
		GameTime gameTime = new GameTimeClass();
		this.gameScreen = new GameScreen(gameTime);
		setScreen(this.gameScreen);
	}
}
