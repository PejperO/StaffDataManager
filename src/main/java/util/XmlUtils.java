package util;

import org.w3c.dom.*;
//import javax.xml.bind.* DO NOT USE
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlUtils{

    public static Document createDocument() throws Exception{
        return DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();
    }

    public static Document parse(File file) throws Exception{
        return DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(file);
    }

    public static void write(Document doc, File file) throws Exception{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
    }

    public static void appendElement(Document doc, Element parent, String tag, String value){
        Element e = doc.createElement(tag);
        e.appendChild(doc.createTextNode(value));
        parent.appendChild(e);
    }

    public static String getText(Element root, String tag){
        NodeList nl = root.getElementsByTagName(tag);
        return nl.getLength() > 0 ? nl.item(0).getTextContent() : null;
    }
}
