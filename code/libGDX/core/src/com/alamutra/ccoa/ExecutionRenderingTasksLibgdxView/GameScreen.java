package com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoAClass;
import com.alamutra.ccoa.Core.Logic.GameTime;
import com.alamutra.ccoa.Core.SettingRenderingTasks.DataFootprintForRendering;
import com.alamutra.ccoa.Core.SettingRenderingTasks.PoolDataFootprintForRendering;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class GameScreen implements Screen {

    private int xOriginRendering = 0;
    //    private int xOriginRendering = -100; //FIXME DON'T WORK
    private int yOriginRendering = 0;
    private int widthRendring = 500;
    private int heightRendring = 500;

    private SpriteBatch batch;
    private PolygonSpriteBatch polyBatch = new PolygonSpriteBatch();
    private GameTime gameTime;
    private PoolDataFootprintForRendering poolDataFootprintForRendering;
    private Map<Integer, SpriteWrapper> storageRenderingFootprints = new TreeMap<Integer, SpriteWrapper>();

    private FabricSprite fabricSprite = new FabricRenderingFootprintClass();


    public GameScreen(GameTime gameTime, PoolDataFootprintForRendering poolDataFootprintForRendering) {
        this.gameTime = gameTime;
        this.poolDataFootprintForRendering = poolDataFootprintForRendering;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
/*
        TODO PROGRAM
        minimum program:
            drawing everything
        maximum program:
            drawing only changes (tracking changes will fall on the shoulders of PoolDataFootprintForRendering; it will implement an iterator of the changed objects, not all of them)
        2 maximum program:
            first request changes in a second, and then graually change the properties of exiting objects
*/
        gameTime.addGameTime(delta);


        this.poolDataFootprintForRendering.fillYourself(this.getAreaOfRendering(), (int) gameTime.getGameTime());


        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        polyBatch.begin();

        Iterator<DataFootprintForRendering> it = this.poolDataFootprintForRendering.iterator();
        while (it.hasNext()) {
            DataFootprintForRendering currentDataFootprintForRendering = it.next();

            SpriteWrapper spriteWrapper = getFromStorageAdditionAsNeeded(currentDataFootprintForRendering);

            spriteWrapper.update(currentDataFootprintForRendering, this.batch, this.polyBatch);
        }
        polyBatch.end();
        batch.end();
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
        for (SpriteWrapper currentSpriteWrapper :
                this.storageRenderingFootprints.values()) {
            Texture texture = currentSpriteWrapper.getTexture();
            texture.dispose();
        }

        this.batch.dispose();
    }

    private PolygonCCoA getAreaOfRendering() {
        PolygonCCoA resPolygon = new PolygonCCoAClass();
        resPolygon.addPoint(new PointCCoAClass(
                this.xOriginRendering,
                this.yOriginRendering
        ));
        resPolygon.addPoint(new PointCCoAClass(
                this.xOriginRendering + this.widthRendring,
                this.yOriginRendering)
        );
        resPolygon.addPoint(new PointCCoAClass(
                this.xOriginRendering + this.widthRendring,
                this.yOriginRendering + this.heightRendring
        ));
        resPolygon.addPoint(new PointCCoAClass(
                this.xOriginRendering,
                this.yOriginRendering + this.heightRendring
        ));

        return resPolygon;
    }


    private SpriteWrapper createRenderingFootprint(DataFootprintForRendering dataFootprintForRendering) {


        SpriteWrapper currentSprite = this.fabricSprite.getRenderingFootprint(dataFootprintForRendering);
        this.storageRenderingFootprints.put(
                dataFootprintForRendering.getIdMovingObject(),
                currentSprite
        );

        return currentSprite;
    }

    private SpriteWrapper getFromStorageAdditionAsNeeded(DataFootprintForRendering currentDataFootprintForRendering) {
        SpriteWrapper spriteWrapper =
                this.storageRenderingFootprints.get(currentDataFootprintForRendering.getIdMovingObject());

        boolean isAlreadyCreated = spriteWrapper != null;
        if (!isAlreadyCreated) {
            spriteWrapper = createRenderingFootprint(currentDataFootprintForRendering);
        }

        return spriteWrapper;
    }
}
