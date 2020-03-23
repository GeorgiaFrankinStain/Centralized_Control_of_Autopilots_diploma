import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SharedDevelop {
    public static void main(String args[]) {
/*

        // Создание пустого HashSet

        HashSet<String> set = new HashSet<String>();

        // Используем метод add () для добавления элементов в Set

        set.add("Welcome");

        set.add("To");

        set.add("Geeks");

        set.add("4");

        set.add("Geeks");


        // Отображение HashSet

        System.out.println("HashSet: " + set);


        // Создание итератора

        Iterator value = set.iterator();


        // Отображение значений после перебора набора

        System.out.println("The iterator values are: ");

        while (value.hasNext()) {

            System.out.println(value.next());

        }
*/



        List<String> list = new ArrayList<>();
        list.add("Привет");
        list.add("Обучающимся");
        list.add("На");
        list.add("JavaRush");


        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
