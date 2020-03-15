package GUI.Window;

public interface SubWindow {
        public int x = 0;
        public int y = 0;
        public int width = 0;
        public int height = 0;
        public String title = "";

        public void renderContent ();
}
