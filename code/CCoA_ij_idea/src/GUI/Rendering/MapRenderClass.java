package GUI.Rendering;

import GUI.Window.SubWindow;
import Logic.FootprintSpaceTime.FootprintSpaceTime;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MapRenderClass extends JPanel implements MapRender, SubWindow {
    private int xSubWindow;
    private int ySubWindow;
    private int widthSubWindow;
    private int heightSubWindow;
    private String titleSubWindow = "";


    private long xOriginRendering;
    private long yOriginRendering;
    private long widthRendring;
    private long heightRendring;
    private float widthMapScaleRendering;
    private float heightMapScaleRendering;


    public MapRenderClass(FootprintSpaceTime map__FootprintSpaceTime) {
    }


    public void paint(Graphics g) {


        //получаем полигоны от FootprintSpaceTime из заданной нам зоны за последнее некоторое время (колебание таймера)
        //





        /*super.paintComponent(g);
        int[] fillPolygon = new int[4];
        fillPolygon[0] = 14;
        fillPolygon[1] = 44;
        fillPolygon[2] = 34;
        fillPolygon[3] = 24;
        int[] ints = new int[4];
        ints[0] = 120;
        ints[1] = 72;
        ints[2] = 42;
        ints[3] = 52;
        g.drawPolygon(ints, fillPolygon, ints.length); // последний параметр неверно был задан*/


/*        int [] x = {5,  100, 100, 40};
        int [] y = {50, 50,  100, 110};*/
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
