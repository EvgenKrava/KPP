import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXMLFile {
    public static void main(String argv[]) throws ParserConfigurationException, TransformerConfigurationException {
        try {
            DocumentBuilderFactory docFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
// root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("notebook");
            doc.appendChild(rootElement);
// person element
            Element person = doc.createElement("person");
            rootElement.appendChild(person);
// name elements
            Element name = doc.createElement("name");
            person.appendChild(name);
// set attribute to name element
            Attr attr = doc.createAttribute("first");
            attr.setValue("Иван");
            name.setAttributeNode(attr);
            attr = doc.createAttribute("second");
            attr.setValue("Иванович");
            name.setAttributeNode(attr);
            attr = doc.createAttribute("surname");
            attr.setValue("Иванов");
            // birthday elements
            Element birthday = doc.createElement("birthday");
            birthday.appendChild(doc.createTextNode("12.12.2001"));
            person.appendChild(birthday);
// write the content into xml file
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING,
                    "Windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("file.xml"));
// Output to console for testing
// StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
