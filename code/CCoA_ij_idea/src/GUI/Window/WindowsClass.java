package GUI.Window;

import GUI.Rendering.imitation.ImitationMapRender;

import javax.swing.*;

public class WindowsClass implements Windows {
    JFrame generalWindow;

    @Override
    public void createGeneralWindow() {
        this.generalWindow = new JFrame("Centralized Control of Autopilots"); //TODO: add title of room
        this.generalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel subwindowRendering = new ImitationMapRender();

        this.generalWindow.add(subwindowRendering); // добавили наш
        this.generalWindow.pack(); // собрали

        this.generalWindow.setSize(1100, 600);
        generalWindow.setVisible(true);
    }
}
