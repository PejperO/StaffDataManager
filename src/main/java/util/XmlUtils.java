package util;

import org.w3c.dom.*;
//import javax.xml.bind.* DO NOT USE
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlUtils{

    //Create a new empty XML Document
    public static Document createDocument() throws Exception{
        return DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();
    }

    //Parse an XML file into a Document
    public static Document parse(File file) throws Exception{
        return DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(file);
    }

    //Write a Document to an XML file
    public static void write(Document doc, File file) throws Exception{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
    }

    //Append a new element with text content to a parent element
    public static void appendElement(Document doc, Element parent, String tag, String value){
        Element e = doc.createElement(tag);
        e.appendChild(doc.createTextNode(value));
        parent.appendChild(e);
    }

    //Get text content of the first occurrence of a tag within a root element
    public static String getText(Element root, String tag){
        NodeList nl = root.getElementsByTagName(tag);
        return nl.getLength() > 0 ? nl.item(0).getTextContent() : null;
    }
}
