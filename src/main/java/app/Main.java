package app;

import model.Person;
import static model.Type.INTERNAL;
import static model.Type.EXTERNAL;

public class Main {
    public static void main(String[] args) {
        System.out.println("Application started.");
        Person person = new Person("1", "John", "Doe", "123456789", " ", "90010112345", INTERNAL);
        Person person2 = new Person("2", "Jane", "Smith", "987654321", " ", "85050554321", EXTERNAL);

        System.out.println("Person created: " + person.getFirstName() + " " + person.getLastName() + ", Type: " + person.getType());
        System.out.println("Person created: " + person2.getFirstName() + " " + person2.getLastName() + ", Type: " + person2.getType());
    }
}
