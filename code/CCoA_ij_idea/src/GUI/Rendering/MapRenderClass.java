package GUI.Rendering;

import GUI.Window.SubWindow;
import Logic.FootprintSpaceTime.FootprintSpaceTime;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MapRenderClass extends JPanel implements MapRender, SubWindow {
    private int xSubWindow = 50;
    private int ySubWindow = 50;
    private int widthSubWindow = 900;
    private int heightSubWindow = 900;
    private String titleSubWindow = "";


    private long xOriginRendering;
    private long yOriginRendering;
    private long widthRendring;
    private long heightRendring;
    private float widthMapScaleRendering; //FIXME QESTION мне кажется, что лучше его каждый раз пересчитывать
    private float heightMapScaleRendering;


    private FootprintSpaceTime mapFootprintSpaceTime;
    private long timeLastRendering;

    public MapRenderClass(FootprintSpaceTime mapFootprintSpaceTime) {
        this.mapFootprintSpaceTime = mapFootprintSpaceTime;
    }


    public void paint(Graphics g) {


        //FIXME получаем полигоны от FootprintSpaceTime из заданной нам зоны за последнее некоторое время (колебание таймера)

        //


        int [] x = {50,50,90,90,150,90,90};
        int [] y = {55,85,85,110,70,30,55};
        g.setColor(Color.RED);
//        g.drawPolygon(x, y, x.length);
        g.fillPolygon(x, y, x.length);
    }







    //==== <start> <Getter_and_Setter> ==================================================

    public long getxOriginRendering() {
        return xOriginRendering;
    }

    public void setxOriginRendering(long xOriginRendering) {
        this.xOriginRendering = xOriginRendering;
    }

    public long getyOriginRendering() {
        return yOriginRendering;
    }

    public void setyOriginRendering(long yOriginRendering) {
        this.yOriginRendering = yOriginRendering;
    }

    public long getWidthRendring() {
        return widthRendring;
    }

    public void setWidthRendring(long widthRendring) {
        this.widthRendring = widthRendring;
    }

    public long getHeightRendring() {
        return heightRendring;
    }

    public void setHeightRendring(long heightRendring) {
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
    public void renderingZone(long xOriginRendering, long yOriginRendering) {

    }

    //==== <end> <Getter_and_Setter> ========================================================
}
