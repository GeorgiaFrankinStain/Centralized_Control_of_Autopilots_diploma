package GUI.Rendering;

import GUI.Window.SubWindow;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PolygonExtendedClass;
import Logic.FootprintSpaceTime.RenderingPolygon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapRenderClass extends JPanel implements MapRender, SubWindow {
    private int xSubWindow = 50;
    private int ySubWindow = 50;
    private int widthSubWindow = 900;
    private int heightSubWindow = 900;
    private String titleSubWindow = "";


    private long xOriginRendering = 0;
    private long yOriginRendering = 0;
    private long widthRendring = 900;
    private long heightRendring = 900;
    private float widthMapScaleRendering = 1; //FIXME QESTION мне кажется, что лучше его каждый раз пересчитывать
    private float heightMapScaleRendering = 1; //FIXME CRITICAL


    private RenderingPolygonsFromWhen mapRenderingPolygonsFromWhen;
    private long timeLastRenderingLandscape;

    public MapRenderClass(RenderingPolygonsFromWhen mapFootprintSpaceTime) {
        this.mapRenderingPolygonsFromWhen = mapFootprintSpaceTime;
    }


    public void paint(Graphics g) {


        //FIXME получаем полигоны от FootprintSpaceTime из заданной нам зоны за последнее некоторое время (колебание таймера)

        List<RenderingPolygon> objectsOfRendering =
                this.mapRenderingPolygonsFromWhen.getRenderingPolygonsFromWhen(this.getAreaOfRendering(), 1); //FIXME CRITICAL add time variable


        //сравниваем время последнего обновления ландшафта и время последнего рендеринга ландшафта //FIXME realised comments
        //если устарело, то обновляем ландшафт (по началу будем все отрисовывать

        //отрисовываем изменившиеся части (сначала будем отрисовывать все подряд, а потом то, у чего хешь свойств изменился


        int[] x = {50, 50, 90, 90, 150, 90, 90};
        int[] y = {55, 85, 85, 110, 70, 30, 55};
        g.setColor(Color.RED);
        g.fillPolygon(x, y, x.length);
        g.fillPolygon(x, y, x.length);
    }

    private void fillPolygon(PolygonExtended objectOfRendering) {
        int[] xArray = new int[objectOfRendering.countPoints()];
        int[] yArray = new int[objectOfRendering.countPoints()];

        for (int i = 0; i < objectOfRendering.countPoints(); i++) {
//            xArray[i] = objectOfRendering.getPoint(i).getX()
        }
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
