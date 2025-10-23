# 🧾 StaffDataManager

Welcome to **StaffDataManager**, a desktop application for managing employee records using XML files as a lightweight data store.  
This project was built entirely with **Java SE**, focusing on file-based persistence (without JDBC or JAXB) and a straightforward Swing-based GUI interface.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
    - [Data Model](#data-model)
    - [Repository Layer](#repository-layer)
    - [User Interface](#user-interface)
3. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation & Run](#installation--run)
4. [Code Samples](#code-samples)
5. [Screenshots](#screenshots)
6. [What I Learned](#what-i-learned)
7. [License](#license)

---

## Project Overview

**StaffDataManager** provides CRUD (Create, Read, Update, Delete) operations on employee data, stored as individual XML files.  
The goal of this project was to build a self-contained personnel management system using only Java SE — no frameworks, no databases.

Employees are separated into two categories:
- `INTERNAL` — in-house employees.
- `EXTERNAL` — external contractors.

Each employee record is stored in an XML file located under one of the following directories:

```
data/
 ├── INTERNAL/
 │    ├── 1.xml
 │    ├── 2.xml
 │    └── ...
 └── EXTERNAL/
      ├── 3.xml
      ├── 4.xml
      └── ...
```

---

## Architecture

### 🧱 Data Model
Defined in the `model` package, the `Person` class represents a single employee entry:

```java
package model;

public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String pesel;
    private Type type;

    public Person(String personId, String firstName, String lastName, String mobile,
                  String email, String pesel, Type type) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.pesel = pesel;
        this.type = type;
    }

    // Getters, setters, and toString()
}
```

### 📂 Repository Layer
Located in the `repository` package, `PersonRepository` handles all XML file I/O operations using the DOM API.  
It provides the following key methods:

```java
Optional<Person> find(String personId, String firstName, String lastName,
                      String mobile, String pesel, Type type);

void create(Person person);
boolean remove(String personId);
void modify(Person person);
```

### 💻 User Interface
A minimal GUI built with **Swing** (`EmployeeManagerGUI.java`), featuring form fields and buttons for:
- Adding a new employee
- Searching employees by any combination of fields
- Modifying employee data
- Removing an employee

---

## Getting Started

### Prerequisites
- Java 17+
- Any IDE (IntelliJ / Eclipse / NetBeans)

### Installation & Run

1️⃣ Clone or download the repository

```bash
git clone https://github.com/YourUsername/StaffDataManager.git
cd StaffDataManager
```

If you downloaded the .zip file manually, extract it and navigate into the project folder:
```bash
cd path/to/StaffDataManager
```

2️⃣ Compile the project

Make sure you have Java 17 or newer installed.
You can check your version with:

```bash
java -version
```

Then compile all source files:
```bash
javac -d out src/main/java/**/*.java
```
This command creates the out/ directory containing all compiled .class files.

3️⃣ Run the application

Start the GUI directly from the compiled classes:

```bash
java -cp out app.EmployeeManagerGUI
```

If you prefer to run the console version:
```bash
java -cp out app.Main
```

4️⃣ Verify data directory

On first launch, the program will automatically create the necessary folder structure if it doesn’t exist:

```
data/
 ├── INTERNAL/
 └── EXTERNAL/
```
Each employee will be stored as a separate XML file inside one of these directories.

5️⃣ (Optional) Run unit tests

If you want to verify repository logic, make sure you have JUnit 5 on the classpath and run:
```bash
javac -cp .;lib/junit-platform-console-standalone-1.10.0.jar -d out test/**/*.java
java -jar lib/junit-platform-console-standalone-1.10.0.jar -cp out --scan-class-path
```

---

## Code Samples

Example usage from `Main.java`:

```java
PersonRepository repo = new PersonRepository("data");

Person p = new Person("1", "Anna", "Nowak", "555123456",
        "anna@firma.pl", "90010112345", Type.INTERNAL);
repo.create(p);

repo.find("1", null, null, null, null, null)
    .ifPresent(System.out::println);
```

---

## Screenshots

<img width="1012" height="562" alt="1" src="https://github.com/user-attachments/assets/fa0915d2-5210-4356-b499-02c202ea56b1" />
<img width="1012" height="562" alt="2" src="https://github.com/user-attachments/assets/2337a7da-a919-440c-9ffa-e5b8fe6b0adb" />
<img width="1012" height="562" alt="3" src="https://github.com/user-attachments/assets/7ec32051-d219-4083-960f-6cc6e9a686c1" />
<img width="1012" height="562" alt="4" src="https://github.com/user-attachments/assets/94cce916-0529-4389-9765-a16f14deb28b" />

---

## What I Learned

- 💡 Working with XML via DOM API in Java SE
- 🧠 Designing CRUD logic without databases or external libraries
- 🪟 Building a GUI with Swing components (JFrame, JPanel, JTextField, JComboBox, JButton)
- 🧩 Organizing a Java project using clean package structure (`model`, `repository`, `service`, `app`)
- ✅ Writing simple unit tests with JUnit 5 for repository operations
- 🗃️ Managing file-based persistence and directory structure dynamically

---

## 📄 License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---
