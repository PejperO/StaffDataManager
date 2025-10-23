package model;

import java.util.UUID;

public class Person {
    private final String personId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String pesel;
    private EmployeeType type;

    //New constructor -> auto-generated ID
    public Person(String firstName, String lastName, String mobile, String email, String pesel, EmployeeType type) {
        this.personId = generateUniqueId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.pesel = pesel;
        this.type = type;
    }

    //Only for repository use -> existing ID
    //Could be package-private or protected but then
    //the repository would need to be in the same package or subclass
    @Deprecated // Marked as deprecated to indicate it's for internal use only
    public Person(String personId, String firstName, String lastName, String mobile, String email, String pesel, EmployeeType type) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.pesel = pesel;
        this.type = type;
    }

    //Generating a unique ID
    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    // Getters
    public String getPersonId() {
        return personId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getMobile() {
        return mobile;
    }
    public String getEmail() {
        return email;
    }
    public String getPesel() {
        return pesel;
    }
    public EmployeeType getType() {
        return type;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
    public void setType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s %s %s %s - (%s)", personId, firstName, lastName, email, mobile, type);
    }
}
