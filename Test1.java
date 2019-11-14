// В чем здесь ошибка?
public class Test1 {

    public static void main(String[] args) {
/*
        try {
            System.out.println(Integer.parseInt("111"));
        } catch (Exception e1) {
            e1.printStackTrace();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
*/
    }
}

/*
Второй обработчик никогда не вызовется т.к. является наследником первого, а тот отрабатывает раньше.
*/