import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("src/main/resources/", "test.xml"));
        document.getDocumentElement();
        DOMModifcator mod = new DOMModifcator(document);
        mod.print();
        mod.addElement("food", "aaa");
        mod.addElement("food", "aaa");
        mod.addElement("aaa", "bbb");
        mod.addElement("food", "ccc");
        mod.addElement("food", "aaa");
        mod.addElement("aaa", "ccc");
        mod.removeElement("bbb");
        mod.modeElement("ccc", 0,"hello");
        System.out.println("\n\t"+mod.getChildsCoin("food",4));
        mod.save("file.xml");
        mod.print();
    }
}
