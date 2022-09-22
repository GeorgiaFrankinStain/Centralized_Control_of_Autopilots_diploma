package com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView.RenderingFootprints;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintClass;
import com.alamutra.ccoa.Core.SettingRenderingTasks.DataFootprintForRendering;
import com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView.SpriteWrapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Machine extends Sprite implements SpriteWrapper {
    private static final Logger LOGGER = LogManager.getLogger(Machine.class);

    private Sprite sprite;

    public Machine(int numberPicture) {
        Texture texture = new Texture(Gdx.files.internal("machine" + numberPicture + ".png"));
        this.sprite = new Sprite(texture);
    }

    @Override
    public void update(DataFootprintForRendering newProperties, SpriteBatch batch, PolygonSpriteBatch polygonBatch) {
        double x = newProperties.getCoordinates().getX();
        double y = newProperties.getCoordinates().getY();


        double rotation = newProperties.getRotationDegree();
        float width = calculateWidth(newProperties);
        float height = calculateHeight(newProperties);

        LOGGER.debug("update x: {}, y: {}, x (float): {}, y (float): {}, rotation: {}", x, y, (float) x, (float) y, rotation);

        this.sprite.setOriginCenter();
//        this.sprite.setOriginCenter((float) x, (float) y);
        this.sprite.setPosition((float) x, (float) y); //FIXME добавить смещение координаты отрисовки
        this.sprite.setSize(width, height);
        this.sprite.setRotation((float) rotation);

        this.sprite.draw(batch);
    }

    private float calculateHeight(DataFootprintForRendering newProperties) {
        float height = (float) (newProperties.getMovingObject().getShape().getPoint(1).getY()
                - newProperties.getMovingObject().getShape().getPoint(2).getX());
        height = Math.abs(height);

        return height;
    }

    private float calculateWidth(DataFootprintForRendering newProperties) {
        float width = (float) (newProperties.getMovingObject().getShape().getPoint(1).getX()
                - newProperties.getMovingObject().getShape().getPoint(0).getX());
        width = Math.abs(width);

        return width;
    }
}
