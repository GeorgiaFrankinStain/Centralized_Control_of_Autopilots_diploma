package GUI.Rendering.imitation;

import GUI.Rendering.MapRender;
import Logic.FootprintSpaceTime.Point;

import javax.swing.*;
import java.awt.*;

public class ImitationMapRender extends JPanel implements MapRender {


    public void paint(Graphics g) {
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


    @Override
    public void renderingZone() {

    }
}