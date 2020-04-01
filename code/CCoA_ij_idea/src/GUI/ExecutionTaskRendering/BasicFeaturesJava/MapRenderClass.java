package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.DataFootprintForRendering;
import GUI.StatementTaskRendering.PoolPhisicalBodysForRendering;
import Logic.FootprintSpaceTime.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class MapRenderClass extends JPanel implements MapRender, SubWindow, ActionListener {
    private int xSubWindow = 50;
    private int ySubWindow = 50;
    private int widthSubWindow = 900;
    private int heightSubWindow = 900;
    private String titleSubWindow = "";


    private int xOriginRendering = 0;
    private int yOriginRendering = 0;
    private int widthRendring = 900;
    private int heightRendring = 900;
    private float widthMapScaleRendering = 1; //FIXME QESTION мне кажется, что лучше его каждый раз пересчитывать
    private float heightMapScaleRendering = 1; //FIXME CRITICAL
    private float gameTime = 0;
    private float speedRenderingGameSecondPerSecond = 1;

    private Timer mapTimer = new Timer(1000, (ActionListener) this);




    PoolPhisicalBodysForRendering poolPhisicalBodysForRendering;

    public MapRenderClass(PoolPhisicalBodysForRendering poolPhisicalBodysForRendering) {
        this.poolPhisicalBodysForRendering = poolPhisicalBodysForRendering;

        this.mapTimer.start();
    }

    public void actionPerformed(ActionEvent event) {
        this.gameTime += this.speedRenderingGameSecondPerSecond;
        this.gameTime %= 7;
        repaint();
    }

    public void paint(Graphics g) {


        this.poolPhisicalBodysForRendering.fillYourself(this.getAreaOfRendering(), (int) gameTime); //FIXME CRITICAL add time variable


        //TODO REALISED comments
        //minimum program:
        //    drawing everything
        //maximum program:
        //    drawing only changes (tracking changes will fall on the shoulders of PoolPhisicalBodysForRendering; it will implement an iterator of the changed objects, not all of them)
        //2 maximum program:
        //    first request changes in a second, and then graually change the properties of exiting objects


        g.setColor(Color.RED);
        Iterator<DataFootprintForRendering> it = this.poolPhisicalBodysForRendering.iterator();
        while (it.hasNext()) { //(dubiously) foreach of object rendering, not object pool; the plug goes through the entire system, and you need to change it all at once. With a crutch and so will work.
            RenderingFootprint currentRender = (RenderingFootprint) it.next();
            currentRender.renderingYourself(g);
//
        }

        //TODO REALISED LINK_uVPgVFwt (1) create a iteration of changes, not rendering objects (2) create for delete deleting objects
    }

    private void printPolygonIn2array(PolygonExtended objectOfRendering, Graphics g) {

    }

    private PolygonExtended getAreaOfRendering() {
        PolygonExtended resPolygon = new PolygonExtendedClass();
        resPolygon.setPoint(new PointClass(
                this.xOriginRendering,
                this.yOriginRendering
        ));
        resPolygon.setPoint(new PointClass(
                this.xOriginRendering + this.widthRendring,
                this.yOriginRendering)
        );
        resPolygon.setPoint(new PointClass(
                this.xOriginRendering + this.widthRendring,
                this.yOriginRendering + this.heightRendring
        ));
        resPolygon.setPoint(new PointClass(
                this.xOriginRendering,
                this.yOriginRendering + this.heightRendring
        ));

        return resPolygon;
    }


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
