package GUI.Window;

import GUI.Rendering.MapRenderClass;
import GUI.Rendering.imitation.ImitationMapRender;
import Logic.FootprintSpaceTime.FootprintSpaceTime;
import Logic.FootprintSpaceTime.imitation.ImitationFootprintSpaceTime;

import javax.swing.*;

public class WindowsClass implements Windows {

    int heightWindowDefault = 768;
    int widthWindowDefault = 1024;
    String titleGeneralWindowDefault = "Centralized Control of Autopilots";

    JFrame generalWindow;

    @Override
    public void createGeneralWindow() {
        this.generalWindow = new JFrame(titleGeneralWindowDefault); //TODO: add title of room
        this.generalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FootprintSpaceTime onlyFootprintSpaceTime = new ImitationFootprintSpaceTime();
        JPanel subwindowRendering = new MapRenderClass(onlyFootprintSpaceTime);
//        subwindowRendering.
        this.generalWindow.add(subwindowRendering);
        this.generalWindow.pack();

        this.generalWindow.setSize(widthWindowDefault, heightWindowDefault);
        generalWindow.setVisible(true);
    }
}
