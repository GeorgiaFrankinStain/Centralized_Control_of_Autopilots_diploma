package com.alamutra.ccoa.GUI.StatementTaskRendering;

public interface SubWindow {


        public int getxSubWindow();
        public void setxSubWindow(int xSubWindow);
        public int getySubWindow();
        public void setySubWindow(int ySubWindow);
        public int getWidthSubWindow();
        public void setWidthSubWindow(int widthSubWindow);
        public int getHeightSubWindow();
        public void setHeightSubWindow(int heightSubWindow);
        public String getTitleSubWindow();
        public void setTitleSubWindow(String titleSubWindow);




        public void renderContent ();
}
