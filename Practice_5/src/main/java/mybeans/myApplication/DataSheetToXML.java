package mybeans.myApplication;

import mybeans.table.DataSheet;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DataSheetToXML {

    static Logger log = Logger.getLogger(DataSheet.class);

    public static Element createDataSheetDOM(DataSheet dataSheet) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element datasheet = document.createElement("datasheet");
            for (int i = 0; i < dataSheet.size(); ++i) {
                Element data = document.createElement("data");
                data.setAttribute("date", dataSheet.getDataItem(i).getDate());
                Element x = document.createElement("x");
                Element y = document.createElement("y");
                x.setTextContent("" + dataSheet.getDataItem(i).getX());
                y.setTextContent("" + dataSheet.getDataItem(i).getY());
                data.appendChild(x);
                data.appendChild(y);
                datasheet.appendChild(data);
            }
            return datasheet;

        }catch (Exception e) {
            log.error("Ошибка создания DOM");
        }
        return null;
    }

    public static void saveXMLDoc(Element dataSheetDOM, String fileName) {
        File file = new File(fileName);

        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(dataSheetDOM), new StreamResult(file));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}
