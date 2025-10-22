import model.Person;
import model.Type;
import repository.PersonRepository;

import org.junit.jupiter.api.*;
import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonRepositoryTest {

    static final String TEST_DIR = "testdata";
    static PersonRepository repo;

    //setup and teardown
    @BeforeAll
    static void setup() {
        repo = new PersonRepository(TEST_DIR);
        new File(TEST_DIR).mkdirs();
    }

    //test create and find
    @Test @Order(1)
    void testCreateAndFindPerson() {
        Person p = new Person("1", "Jan", "Kowalski", "123456789", "jan@firma.pl", "90010112345", Type.INTERNAL);
        repo.create(p);
        Optional<Person> found = repo.find("1", null, null, null, null, null);
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("Jan", found.get().getFirstName());
    }

    //Test modify
    @Test @Order(2)
    void testModifyPerson() {
        Person p = new Person("1", "Janusz", "Kowalski", "987654321", "janusz@firma.pl", "90010112345", Type.INTERNAL);
        repo.modify(p);
        Optional<Person> found = repo.find("1", null, null, null, null, null);
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("Janusz", found.get().getFirstName());
    }

    //Test remove
    @Test @Order(3)
    void testRemovePerson() {
        boolean removed = repo.remove("1");
        Assertions.assertTrue(removed);
        Optional<Person> found = repo.find("1", null, null, null, null, null);
        Assertions.assertFalse(found.isPresent());
    }

    //cleanup
    @AfterAll
    static void cleanup() throws Exception {
        Files.walk(new File(TEST_DIR).toPath())
                .map(java.nio.file.Path::toFile)
                .sorted((a,b)->-a.compareTo(b))
                .forEach(File::delete);
    }
}
