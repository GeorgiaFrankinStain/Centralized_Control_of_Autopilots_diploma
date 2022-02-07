package com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView;

import com.alamutra.ccoa.Core.SettingRenderingTasks.DataFootprintForRendering;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface SpriteWrapper {
    void update(DataFootprintForRendering newProperties, SpriteBatch batch, PolygonSpriteBatch polygonBatch);
    Texture getTexture();
}
