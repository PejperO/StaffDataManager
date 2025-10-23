package app;

import model.Person;
import repository.PersonRepository;

import static model.EmployeeType.INTERNAL;
import static model.EmployeeType.EXTERNAL;

public class Main {
    public static void main(String[] args) {
        PersonRepository repo = new PersonRepository("data");

        System.out.println("Application started.");

        //Add a new employee -> repo.create(person)
        Person person1 = new Person( "Alice", "Johnson", "987654321", "alice.johnson@gmail.com", "85050567890", INTERNAL);
        repo.create(person1);
        System.out.println("Person " + person1 + " created.");

        //Find an employee by any of the attributes -> repo.find(attributes)
        repo.find(null, "Alice", null, null, null, null)
                .ifPresent(person -> System.out.println("Found: " + person));

        //Change employee details -> person.setX(details); repo.modify(person);
        repo.find(null, null, "Johnson", null, null, null)
                .ifPresent(person -> {
                    person.setEmail("different.email@gmail.com");
                    repo.modify(person);
                    System.out.println("Person " + person + " updated.");
                });

        //Remove an employee -> repo.remove(ID);
        repo.find(null, null, null, "987654321", null, null)
                .ifPresent(person -> {
                    repo.remove(person.getPersonId());
                    System.out.println("Person " + person + " removed.");
                });
    }
}
