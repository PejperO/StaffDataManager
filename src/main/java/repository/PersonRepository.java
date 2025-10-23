package repository;

import model.Person;
import model.EmployeeType;
import util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.*;

public class PersonRepository{
    private final String baseDir;

    public PersonRepository(String baseDir){
        this.baseDir = baseDir;
    }

    public Optional<Person> find(String personId, String firstName, String lastName, String mobile, String pesel, EmployeeType type){
        List<File> files = getFiles(type);

        for(File f : files){
            Person p = readPersonFromXml(f);
            if(p == null)
                continue;

            if(personId == null && firstName == null && lastName == null && mobile == null && pesel == null && type == null)
                return Optional.empty();

            boolean match = (personId == null || personId.equals(p.getPersonId())) &&
                            (firstName == null || firstName.equalsIgnoreCase(p.getFirstName())) &&
                            (lastName == null || lastName.equalsIgnoreCase(p.getLastName())) &&
                            (mobile == null || mobile.equals(p.getMobile())) &&
                            (pesel == null || pesel.equals(p.getPesel())) &&
                            (type == null || type == p.getType());

            if(match)
                return Optional.of(p);
        }
        return Optional.empty();
    }

    public void create(Person person){
        try{
            File dir = new File(baseDir + "/" + person.getType().name());
            if(!dir.exists())
                dir.mkdirs();

            File file = new File(dir, person.getPersonId() + ".xml");
            Document doc = XmlUtils.createDocument();
            Element root = doc.createElement("person");
            doc.appendChild(root);

            XmlUtils.appendElement(doc, root, "personId", person.getPersonId());
            XmlUtils.appendElement(doc, root, "firstName", person.getFirstName());
            XmlUtils.appendElement(doc, root, "lastName", person.getLastName());
            XmlUtils.appendElement(doc, root, "mobile", person.getMobile());
            XmlUtils.appendElement(doc, root, "email", person.getEmail());
            XmlUtils.appendElement(doc, root, "pesel", person.getPesel());
            XmlUtils.appendElement(doc, root, "type", person.getType().toString());

            XmlUtils.write(doc, file);

        }catch (Exception e){
            throw new RuntimeException("Cannot create person XML", e);
        }
    }

    public boolean remove(String personId){
        for(EmployeeType t : EmployeeType.values()){
            for(File f : getFiles(t)){
                Person p = readPersonFromXml(f);
                if(p != null && p.getPersonId().equals(personId)){
                    return f.delete();
                }
            }
        }
        return false;
    }

    public void modify(Person person){
        remove(person.getPersonId());
        create(person);
    }

    private List<File> getFiles(EmployeeType type){
        if(type == null){
            List<File> all = new ArrayList<>();
            all.addAll(getFiles(EmployeeType.INTERNAL));
            all.addAll(getFiles(EmployeeType.EXTERNAL));
            return all;
        }

        File dir = new File(baseDir + "/" + type.name());
        File[] files = dir.listFiles((d, name) -> name.endsWith(".xml"));
        return files == null ? Collections.emptyList() : Arrays.asList(files);
    }

    private Person readPersonFromXml(File file){
        try{
            Document doc = XmlUtils.parse(file);
            Element root = doc.getDocumentElement();

            String personId = XmlUtils.getText(root, "personId");
            String firstName = XmlUtils.getText(root, "firstName");
            String lastName = XmlUtils.getText(root, "lastName");
            String mobile = XmlUtils.getText(root, "mobile");
            String email = XmlUtils.getText(root, "email");
            String pesel = XmlUtils.getText(root, "pesel");
            EmployeeType type = EmployeeType.valueOf(XmlUtils.getText(root, "type").toUpperCase());

            return new Person(personId, firstName, lastName, mobile, email, pesel, type);
        }catch(Exception e){
            return null;
        }
    }
}
