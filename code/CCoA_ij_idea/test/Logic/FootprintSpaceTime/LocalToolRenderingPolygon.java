package Logic.FootprintSpaceTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.CountDownLatch;

public class LocalToolRenderingPolygon {
    private PolygonExtended objectOfRendering;
    private Point point;

    public LocalToolRenderingPolygon(PolygonExtended objectOfRendering, Point point) {
        this.objectOfRendering = objectOfRendering;
        this.point = point;
    }

    public void rendering() {
        JFrame generalWindow;

        generalWindow = new JFrame("debug polygons");
        generalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);








        JPanel subwindowRendering = (JPanel) new LocalPainter();
        generalWindow.add(subwindowRendering);
        generalWindow.pack();

        generalWindow.setSize(300, 300);
        generalWindow.setVisible(true);


        try {
            waitForSpace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + " --- main");
        }

    }


    private class LocalPainter extends JPanel {

        public void paint(Graphics g) {
            int[] xPoints = new int[LocalToolRenderingPolygon.this.objectOfRendering.countPoints()];
            int[] yPoints = new int[LocalToolRenderingPolygon.this.objectOfRendering.countPoints()];

            for (int i = 0; i < LocalToolRenderingPolygon.this.objectOfRendering.countPoints(); i++) {
                xPoints[i] = LocalToolRenderingPolygon.this.objectOfRendering.getPoint(i).getX();
                yPoints[i] = LocalToolRenderingPolygon.this.objectOfRendering.getPoint(i).getY();
            }

            g.fillPolygon(xPoints, yPoints, xPoints.length);


            g.fillOval(
                    LocalToolRenderingPolygon.this.point.getX(),
                    LocalToolRenderingPolygon.this.point.getY(),
                    3,
                    3);
        }
    }


    private void waitForSpace() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        KeyEventDispatcher dispatcher = new KeyEventDispatcher() {
            // Anonymous class invoked from EDT
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    latch.countDown();
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
        latch.await();  // current thread waits here until countDown() is called
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
    }
}
