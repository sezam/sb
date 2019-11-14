import java.util.Set;
import java.util.TreeSet;

// Как нужно доработать класс Wrapper, чтоб в консоль вывелось ["A","B","C"]?
public class Test2 {

    private static class Wrapper implements Comparable<Wrapper>{
        private final String value;

        private Wrapper(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(Wrapper s) {
            return this.value.compareTo(s.value);
        }

        @Override
        public String toString() {
            return value;
        }
    }


    public static void main(String[] args) {
        Set<Wrapper> set = new TreeSet<>();
        set.add(new Wrapper("B"));
        set.add(new Wrapper("B"));
        set.add(new Wrapper("C"));
        set.add(new Wrapper("B"));
        set.add(new Wrapper("C"));
        set.add(new Wrapper("A"));
        set.add(new Wrapper("C"));
        set.add(new Wrapper("B"));
        System.out.println(set);
    }
}

/*
Добавил реализацию интерфейса Comparable
и метод toString
 */
