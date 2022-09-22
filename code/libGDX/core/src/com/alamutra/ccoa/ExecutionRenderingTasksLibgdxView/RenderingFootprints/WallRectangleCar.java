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

public class WallRectangleCar extends Sprite implements SpriteWrapper {
    private PolygonSprite sprite;

    public WallRectangleCar() {
    }

    @Override
    public void update(DataFootprintForRendering newProperties, SpriteBatch batch, PolygonSpriteBatch polygonBatch) {
        verificationInput(newProperties);
        //FIXME test exist add
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(1, 0, 0, 1); //FIXME add color from hash id
        pix.fill();

        Texture tempTexture = new Texture(pix);


        TextureRegion textureRegion = new TextureRegion(tempTexture);

        FloatArray vertices = calculateVertices(newProperties);

        EarClippingTriangulator triangulator = new EarClippingTriangulator();
        ShortArray triangleindices = triangulator.computeTriangles(vertices);

        PolygonRegion polyReg = new PolygonRegion(textureRegion, vertices.toArray(), triangleindices.toArray());
        PolygonSprite polygonSprite = new PolygonSprite(polyReg);



        float x = (float) newProperties.getCoordinates().getX();
        float y = (float) newProperties.getCoordinates().getY();
        float rotation = (float) newProperties.getRotationDegree();

        polygonSprite.setPosition(x, y);;
        polygonSprite.setRotation(rotation);

        polygonSprite.draw(polygonBatch);
    }

    private FloatArray calculateVertices(DataFootprintForRendering newProperties) {
        PointCCoA topLeft = newProperties.getMovingObject().getShape().getPoint(0);
        PointCCoA topRight = newProperties.getMovingObject().getShape().getPoint(1);
        PointCCoA bottomLeft = newProperties.getMovingObject().getShape().getPoint(2);
        PointCCoA bottomRight = newProperties.getMovingObject().getShape().getPoint(3);

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

        return vertices;
    }

    private void verificationInput(DataFootprintForRendering newProperties) {
        int countPointsPolygon = newProperties.getMovingObject().getShape().getCountPoints();
        assert(countPointsPolygon == 4);
    }

}
