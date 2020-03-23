package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.ExecutionTaskRendering.PoolPhisicalBodys;
import GUI.ExecutionTaskRendering.PoolPhisicalBodysClass;
import GUI.StatementTaskRendering.PhisicalBodysFromWhen;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PolygonExtendedClass;
import Logic.FootprintSpaceTime.PhisicalBody;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class MapRenderClass extends JPanel implements MapRender, SubWindow {
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


    private PhisicalBodysFromWhen mapPhisicalBodysFromWhen;

    PoolPhisicalBodys poolPhisicalBodys;

    public MapRenderClass(PoolPhisicalBodys poolPhisicalBodys) {
        this.poolPhisicalBodys = poolPhisicalBodys;
    }


    public void paint(Graphics g) {


        this.poolPhisicalBodys.fillYourself(this.getAreaOfRendering(), 1); //FIXME CRITICAL add time variable


        //TODO REALISED comments
        //программа минимум:
        //  отрисовываем все
        //программа максимум:
        //  отрисовываем только изменения (отслеживаение изменений ляжет на плечи PoolPhisicalBodys; там будет реализован итератор изменившихся объектов, а не всех)


        g.setColor(Color.RED);
        Iterator<PhisicalBody> it = this.poolPhisicalBodys.iterator();
        while (it.hasNext()) {
            this.printPolygonIn2array(it.next().getArea(), g);
        }
    }

    private void printPolygonIn2array(PolygonExtended objectOfRendering, Graphics g) {
        int[] xPoints = new int[objectOfRendering.countPoints()];
        int[] yPoints = new int[objectOfRendering.countPoints()];

        for (int i = 0; i < objectOfRendering.countPoints(); i++) {
            xPoints[i] = objectOfRendering.getPoint(i).getX();
            yPoints[i] = objectOfRendering.getPoint(i).getY();
        }

        g.fillPolygon(xPoints, yPoints, xPoints.length);
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
