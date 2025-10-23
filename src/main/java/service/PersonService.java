package service;

import model.Person;
import repository.PersonRepository;

import java.util.Optional;

//not used, but could be useful in the future
public class PersonService{
    private final PersonRepository repo;

    public PersonService(PersonRepository repo){
        this.repo = repo;
    }

    //Add person
    public void addPerson(Person p){
        if (p.getPersonId() == null || p.getPersonId().isEmpty()) {
            throw new IllegalArgumentException("Person ID cannot be empty");
        }
        repo.create(p);
    }

    //Find person by X
    public Optional<Person> findById(String id){
        return repo.find(id, null, null, null, null, null);
    }

    public Optional<Person> findByFirstName(String firstName){
        return repo.find(null, firstName, null, null, null, null);
    }

    public Optional<Person> findByLastName(String lastName){
        return repo.find(null, null, lastName, null, null, null);
    }

    public Optional<Person> findByMobile(String mobile){
        return repo.find(null, null, null, mobile, null, null);
    }

    public Optional<Person> findByPesel(String pesel){
        return repo.find(null, null, null, null, pesel, null);
    }

    //Remove person by ID
    public boolean remove(String id){
        return repo.remove(id);
    }

    //Update person
    public void update(Person p){
        repo.modify(p);
    }
}
