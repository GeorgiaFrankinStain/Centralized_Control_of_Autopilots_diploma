package GUI.Window;

import GUI.StatementTaskRendering.MapRender;

import javax.swing.*;

public class WindowsClass implements Windows {

    private int heightGeneralWindowDefault = 1000;
    private int widthGeneralWindowDefault = 1000;
    private String titleGeneralWindowDefault = "Centralized Control of Autopilots";

    JFrame generalWindow;

    @Override
    public void createGeneralWindow(MapRender mapRender) {
        this.generalWindow = new JFrame(titleGeneralWindowDefault); //TODO: add title of room
        this.generalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        this.generalWindow.setUndecorated(true);
//        this.generalWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); //сделать игру полноэкранной

        JPanel subwindowRendering = (JPanel) mapRender;
        this.generalWindow.add(subwindowRendering);
        this.generalWindow.pack();

        this.generalWindow.setSize(widthGeneralWindowDefault, heightGeneralWindowDefault);




        generalWindow.setVisible(true);
    }
}
