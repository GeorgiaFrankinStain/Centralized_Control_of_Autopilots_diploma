package com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView.RenderingFootprints;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.SettingRenderingTasks.DataFootprintForRendering;
import com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView.SpriteWrapper;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;

public class WallCar extends Sprite implements SpriteWrapper {
    private PolygonSprite sprite;

    public WallCar() {
    }

    @Override
    public void update(DataFootprintForRendering newProperties, SpriteBatch batch, PolygonSpriteBatch polygonBatch) {
        //FIXME test exist add
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(1, 0, 0, 1); //FIXME add color from hash id
        pix.fill();

        Texture tempTexture = new Texture(pix);

        Vector2 center = new Vector2(0, 0);


        PointCCoA topLeft = newProperties.getMovingObject().getShape().getPoint(0);
        PointCCoA topRight = newProperties.getMovingObject().getShape().getPoint(1);
        PointCCoA bottomLeft = newProperties.getMovingObject().getShape().getPoint(2);
        PointCCoA bottomRight = newProperties.getMovingObject().getShape().getPoint(3);

        TextureRegion textureRegion = new TextureRegion(tempTexture);

        FloatArray vertices = new FloatArray(new float[] {
                (float) topLeft.getX(),
                (float) topLeft.getY(),
                (float) topRight.getX(),
                (float) topRight.getY(),
                (float) bottomLeft.getX(),
                (float) bottomLeft.getY(),
                (float) bottomRight.getX(),
                (float) bottomRight.getY(),
        });

        EarClippingTriangulator triangulator = new EarClippingTriangulator();
        ShortArray triangleindices = triangulator.computeTriangles(vertices);

        PolygonRegion polyReg = new PolygonRegion(textureRegion, vertices.toArray(), triangleindices.toArray());
        PolygonSprite polygonSprite = new PolygonSprite(polyReg);


        float width = (float) (newProperties.getMovingObject().getShape().getPoint(1).getX()
                - newProperties.getMovingObject().getShape().getPoint(0).getX());
        float height = (float) (newProperties.getMovingObject().getShape().getPoint(1).getY()
                - newProperties.getMovingObject().getShape().getPoint(2).getX());

        width = Math.abs(width);
        height = Math.abs(height);

        float x = (float) newProperties.getCoordinates().getX();
//        x += width / 2;
        float y = (float) newProperties.getCoordinates().getY();
//        y += height / 2;
        float rotation = (float) newProperties.getRotationDegree();

        polygonSprite.setPosition(x, y);
        polygonSprite.setRotation(rotation);

        polygonSprite.draw(polygonBatch);
    }
}
