package app;

import model.Person;
import model.EmployeeType;
import repository.PersonRepository;

import javax.swing.*;
import java.awt.*;

//README
/*
At first, I wanted to create the interface in JavaScript, with communication handled through a REST API (Spring).
Changing the entire project to Spring would have taken some time, and according to the task description,
"it wasn’t graded in any way". Honestly, I could have left the code as it was and not added this class (StaffDataManagerGUI),
but I thought it would look nicer this way.
Console commands would have been extremely prone to input errors, which is why I decided to go with Swing instead.
It may not be perfect - there are many things that could be improved - but it works well enough :)
 */

public class StaffDataManagerGUI extends JFrame {

    private final JTextField idField = new JTextField(10);
    private final JTextField firstNameField = new JTextField(10);
    private final JTextField lastNameField = new JTextField(10);
    private final JTextField phoneField = new JTextField(10);
    private final JTextField emailField = new JTextField(10);
    private final JTextField peselField = new JTextField(10);
    private final JComboBox<EmployeeType> typeBox = new JComboBox<>(EmployeeType.values());
    private final JTextArea logArea = new JTextArea(8, 40);

    private final PersonRepository repo = new PersonRepository("data");

    public StaffDataManagerGUI() {
        super("StaffDataManager (XML DB)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //FORM PANEL
        JPanel form = new JPanel(new GridLayout(7, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Employee Data"));
        form.add(new JLabel("ID:"));
        form.add(idField);
        form.add(new JLabel("First Name:"));
        form.add(firstNameField);
        form.add(new JLabel("Last Name:"));
        form.add(lastNameField);
        form.add(new JLabel("Mobile:"));
        form.add(phoneField);
        form.add(new JLabel("Email:"));
        form.add(emailField);
        form.add(new JLabel("PESEL:"));
        form.add(peselField);
        form.add(new JLabel("Type:"));
        form.add(typeBox);
        add(form, BorderLayout.NORTH);

        //BUTTONS
        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton findBtn = new JButton("Find");
        JButton modifyBtn = new JButton("Modify");
        JButton removeBtn = new JButton("Remove");

        buttons.add(addBtn);
        buttons.add(findBtn);
        buttons.add(modifyBtn);
        buttons.add(removeBtn);

        add(buttons, BorderLayout.CENTER);

        //LOG AREA
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.SOUTH);

        //BUTTON ACTIONS
        addBtn.addActionListener(e -> addPerson());
        findBtn.addActionListener(e -> findPerson());
        modifyBtn.addActionListener(e -> modifyPerson());
        removeBtn.addActionListener(e -> removePerson());

        //pack();
        setSize(900, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addPerson() {
        try {
            Person p = new Person(
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim(),
                    phoneField.getText().trim(),
                    emailField.getText().trim(),
                    peselField.getText().trim(),
                    (EmployeeType) typeBox.getSelectedItem()
            );
            repo.create(p);
            log("Added: " + p);
        } catch (Exception ex) {
            log("Error adding person: " + ex.getMessage());
        }
    }

    private void findPerson() {
        try {
            var result = repo.find(
                    emptyToNull(idField.getText()),
                    emptyToNull(firstNameField.getText()),
                    emptyToNull(lastNameField.getText()),
                    emptyToNull(phoneField.getText()),
                    emptyToNull(peselField.getText()),
                    (EmployeeType) typeBox.getSelectedItem()
            );

            if (result.isPresent()) {
                Person p = result.get();
                log("Found: " + p);

                //Follow-up: populate fields
                idField.setText(p.getPersonId());
                firstNameField.setText(p.getFirstName());
                lastNameField.setText(p.getLastName());
                phoneField.setText(p.getMobile());
                emailField.setText(p.getEmail());
                peselField.setText(p.getPesel());
                typeBox.setSelectedItem(p.getType());
            } else {
                log("No match found.");
            }
        } catch (Exception ex) {
            log("Error finding person: " + ex.getMessage());
        }
    }

    private void modifyPerson() {
        try {
            var found = repo.find(idField.getText().trim(), null, null, null, null, null);
            if (found.isEmpty()) {
                log("No person with ID " + idField.getText());
                return;
            }
            Person p = found.get();
            p.setFirstName(firstNameField.getText().trim());
            p.setLastName(lastNameField.getText().trim());
            p.setMobile(phoneField.getText().trim());
            p.setEmail(emailField.getText().trim());
            p.setPesel(peselField.getText().trim());
            p.setType((EmployeeType) typeBox.getSelectedItem());
            repo.modify(p);
            log("Modified: " + p);
        } catch (Exception ex) {
            log("Error modifying person: " + ex.getMessage());
        }
    }

    private void removePerson() {
        try {
            var found = repo.find(idField.getText().trim(), null, null, null, null, null);
            if (found.isPresent()) {
                repo.remove(found.get().getPersonId());
                log("Removed: " + found.get());
            } else {
                log("No person found with ID " + idField.getText());
            }
        } catch (Exception ex) {
            log("Error removing person: " + ex.getMessage());
        }
    }

    private String emptyToNull(String s) {
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    private void log(String msg) {
        logArea.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StaffDataManagerGUI::new);
    }
}
