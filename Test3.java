import java.util.ArrayList;
import java.util.List;

// Как добавить квадрат в список фигур? Класс SquareExternal менять нельзя, но нужно использовать его функицонал.

public class Test3 {

    private interface Figure {
        String getName();
    }

    // Круг
    private static class Circle implements Figure {
        @Override
        public String getName() {
            return "circle";
        }
    }

    // Треугольник
    private static class Triangle implements Figure {
        @Override
        public String getName() {
            return "triangle";
        }
    }

    // Квадрат
    private static final class SquareExternal {
        public String getName() {
            return "square";
        }
    }
    private static class Square implements Figure {
        private final SquareExternal squareExternal = new SquareExternal();
        @Override
        public String getName() {
            return squareExternal.getName();
        }
    }



    public static void main(String[] args) {
        List<Figure> figures = new ArrayList<>(3);
        figures.add(new Circle());
        figures.add(new Triangle());
        figures.add(new Square());
//        figures.add(new SquareExternal());
        for (Figure figure : figures) {
            System.out.println(figure.getName());
        }
    }

}

/*
Может не так понял задание? Но SquareExternal не наследуется от Figure. Пришлось его обернуть в класс Square.
 */
