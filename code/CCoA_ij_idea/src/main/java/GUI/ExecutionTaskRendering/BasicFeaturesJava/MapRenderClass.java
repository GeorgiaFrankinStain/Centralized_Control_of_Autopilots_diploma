package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.DataFootprintForRendering;
import GUI.StatementTaskRendering.PoolDataFootprintForRendering;
import GUI.StatementTaskRendering.SubWindow;
import Logic.FootprintSpaceTime.*;

import javafx.scene.layout.Pane;


import java.util.*;

public class MapRenderClass extends Pane implements MapRender, SubWindow {
    private int xSubWindow = 50;
    private int ySubWindow = 50;
    private int widthSubWindow = 500;
    private int heightSubWindow = 500;
    private String titleSubWindow = "";


    private int xOriginRendering = 0;
//    private int xOriginRendering = -100; //FIXME DON'T WORK
    private int yOriginRendering = 0;
    private int widthRendring = 500;
    private int heightRendring = 500;
    private float widthMapScaleRendering = 1; //FIXME QESTION мне кажется, что лучше его каждый раз пересчитывать
    private float heightMapScaleRendering = 1; //FIXME CRITICAL
    private float gameTime = 0;
    private float speedRenderingGameSecondPerSecond = 1;

    private Map<Integer, RenderingFootprint> storageRenderingFootprints = new TreeMap<Integer, RenderingFootprint>();
    private PoolDataFootprintForRendering poolDataFootprintForRendering;

    public MapRenderClass(PoolDataFootprintForRendering poolDataFootprintForRendering) {
        this.poolDataFootprintForRendering = poolDataFootprintForRendering;

    }


    @Override
    public void update(long now) {
//        this.gameTime += this.speedRenderingGameSecondPerSecond; //FIXME add adapter now in gameTime
        this.gameTime++;
        this.gameTime %= 250;

        this.poolDataFootprintForRendering.fillYourself(this.getAreaOfRendering(), (int) gameTime);


/*
        TODO PROGRAM
        minimum program:
            drawing everything
        maximum program:
            drawing only changes (tracking changes will fall on the shoulders of PoolDataFootprintForRendering; it will implement an iterator of the changed objects, not all of them)
        2 maximum program:
            first request changes in a second, and then graually change the properties of exiting objects
*/


        Iterator<DataFootprintForRendering> it = this.poolDataFootprintForRendering.iterator();
        while (it.hasNext()) {
            DataFootprintForRendering currentDataFootprintForRendering = it.next();

            RenderingFootprint takenRender =
                    this.storageRenderingFootprints.get(currentDataFootprintForRendering.getIdObject());

            boolean isTaken = takenRender != null;
            if (isTaken) {
                takenRender.update(now, currentDataFootprintForRendering);
            } else {
                createRenderingFootrint(currentDataFootprintForRendering);
            }
        }


        //FIXME deleting rendering footprints (yourself find or get from iterator of pool
        //TODO REALISED LINK_uVPgVFwt (1) create a iteration of changes, not rendering objects (2) create for delete deleting objects
    }

    //==== <start> <Private_Methods> =======================================================================
    private void createRenderingFootrint(DataFootprintForRendering dataFootprintForRendering) {

        FabricRendringFootprint fabric = new FabricRenderingFootprintClass();
        Pane currentPane = fabric.getRenderingFootprint(dataFootprintForRendering);
        this.storageRenderingFootprints.put(
                dataFootprintForRendering.getIdObject(),
                (RenderingFootprint) currentPane
        );
        getChildren().addAll(currentPane);
    }


    private PolygonExtended getAreaOfRendering() {
        PolygonExtended resPolygon = new PolygonExtendedClass();
        resPolygon.addPoint(new PointClass(
                this.xOriginRendering,
                this.yOriginRendering
        ));
        resPolygon.addPoint(new PointClass(
                this.xOriginRendering + this.widthRendring,
                this.yOriginRendering)
        );
        resPolygon.addPoint(new PointClass(
                this.xOriginRendering + this.widthRendring,
                this.yOriginRendering + this.heightRendring
        ));
        resPolygon.addPoint(new PointClass(
                this.xOriginRendering,
                this.yOriginRendering + this.heightRendring
        ));

        return resPolygon;
    }


    //==== <end> <Private_Methods> =========================================================================

    //==== <start> <Getter_and_Setter> ==================================================

    public int getxOriginRendering() {
        return xOriginRendering;
    }

    public void setxOriginRendering(int xOriginRendering) {
        this.xOriginRendering = xOriginRendering;
    }

    public int getyOriginRendering() {
        return yOriginRendering;
    }

    public void setyOriginRendering(int yOriginRendering) {
        this.yOriginRendering = yOriginRendering;
    }

    public int getWidthRendring() {
        return widthRendring;
    }

    public void setWidthRendring(int widthRendring) {
        this.widthRendring = widthRendring;
    }

    public int getHeightRendring() {
        return heightRendring;
    }

    public void setHeightRendring(int heightRendring) {
        this.heightRendring = heightRendring;
    }

    public float getWidthMapScaleRendering() {
        return widthMapScaleRendering;
    }

    public void setWidthMapScaleRendering(float widthMapScaleRendering) {
        this.widthMapScaleRendering = widthMapScaleRendering;
    }

    public float getHeightMapScaleRendering() {
        return heightMapScaleRendering;
    }

    public void setHeightMapScaleRendering(float heightMapScaleRendering) {
        this.heightMapScaleRendering = heightMapScaleRendering;
    }


    @Override
    public int getxSubWindow() {
        return 0;
    }

    @Override
    public void setxSubWindow(int xSubWindow) {
        this.xSubWindow = xSubWindow;
    }

    @Override
    public int getySubWindow() {
        return 0;
    }

    @Override
    public void setySubWindow(int ySubWindow) {
        this.ySubWindow = ySubWindow;
    }

    @Override
    public int getWidthSubWindow() {
        return 0;
    }

    @Override
    public void setWidthSubWindow(int widthSubWindow) {
        this.widthMapScaleRendering = widthRendring / widthSubWindow;
        this.widthSubWindow = widthSubWindow;
    }

    @Override
    public int getHeightSubWindow() {
        return 0;
    }

    @Override
    public void setHeightSubWindow(int heightSubWindow) {
        this.heightMapScaleRendering = heightRendring / heightSubWindow;
        this.heightSubWindow = heightSubWindow;
    }

    @Override
    public String getTitleSubWindow() {
        return null;
    }

    @Override
    public void setTitleSubWindow(String titleSubWindow) {
        this.titleSubWindow = titleSubWindow;
    }

    @Override
    public void renderContent() {

    }

    @Override
    public void renderingArea(int xOriginRendering, int yOriginRendering) {

    }

    //==== <end> <Getter_and_Setter> ========================================================
}
