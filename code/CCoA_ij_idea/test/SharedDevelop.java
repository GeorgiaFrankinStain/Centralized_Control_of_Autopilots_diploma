import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SharedDevelop {
    public static void main(String args[]) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("JavaFx");
        list.add("Java");
        list.add("WebGL");
        list.add("OpenCV");
        ArrayList<String> newList = new ArrayList<String>();
        newList.add("HBase");
        newList.add("Neo4j");
        newList.add("MangoDB");
        list.addAll(newList);


    }
}
