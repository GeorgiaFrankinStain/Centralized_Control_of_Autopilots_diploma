package com.alamutra.CCoAWeb.Core.Logic;

public class GameTimeClass implements GameTime {
    private float gameTime = 0;

    @Override
    public float getGameTime() {
        return gameTime;
    }

    @Override
    public void addGameTime(float adding) {
        this.gameTime += adding; //FIXME add tests
    }
}
