// Методы getPersonSalaryFromDB и getAvgSalaryFromDB на самом деле обращаются к базе данных и они ресурсоемкие.
// Что не так в методе main()?
public class Test4 {

    private static double getPersonSalaryFromDB(int personId) {
        return Math.random()*100000;
    }

    private static double getAvgSalaryFromDB() {
        return 50000;
    }

    public static void main(String[] args) {
        int[] personIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int personId : personIds) {
            if (getPersonSalaryFromDB(personId) > getAvgSalaryFromDB()) {
                System.out.println(personId);
            }
        }
    }
}

/*
На каждой итерации будет обращение к базе и за getPersonSalaryFromDB(personId) и за getAvgSalaryFromDB()
В такой постановке можно было getAvgSalaryFromDB() получить один раз, снаружи цикла и положить в переменную

 */