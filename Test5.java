import java.util.*;
import java.util.List;

// Как заполнить ассациативный массив personsByDepartment за один проход по массиву persons?
public class Test5 {

    private static class Person {
        private final int departmentId;
        private final String lastName;
        private final String firstName;
        private final String secondName;

        public Person(int departmentId, String lastName, String firstName, String secondName) {
            this.departmentId = departmentId;
            this.lastName = lastName;
            this.firstName = firstName;
            this.secondName = secondName;
        }
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(6);
        persons.add(new Person(1, "LastName1", "FirstName1", "SecondName1"));
        persons.add(new Person(2, "LastName2", "FirstName2", "SecondName2"));
        persons.add(new Person(1, "LastName3", "FirstName3", "SecondName3"));
        persons.add(new Person(3, "LastName4", "FirstName4", "SecondName4"));
        persons.add(new Person(2, "LastName5", "FirstName5", "SecondName5"));
        persons.add(new Person(1, "LastName6", "FirstName6", "SecondName6"));
        Map<Integer, List<Person>> personsByDepartment = new HashMap<>();
        // todo
        persons.forEach(person -> {
            List<Person> lp = personsByDepartment.get(Integer.valueOf(person.departmentId));
            if(lp == null){
                lp = new ArrayList<>();
                personsByDepartment.put(person.departmentId, lp);
            }
            lp.add(person);
        });
    }
}
