package model;

public class Person {
    private final String personId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String pesel;
    private Type type;

    public Person(String personId, String firstName, String lastName, String mobile, String email, String pesel, Type type) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.pesel = pesel;
        this.type = type;
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
    public Type getType() {
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
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s %s %s %s - (%s)", personId, firstName, lastName, email, mobile, type);
    }
}
