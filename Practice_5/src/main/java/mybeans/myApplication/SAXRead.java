package mybeans.myApplication;

import mybeans.table.DataSheet;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXRead {

    public static DataSheet XMLReadData(String fileName) {
        try {
            File file = new File(fileName);
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            Handler handler = new Handler();
            saxParser.parse(file, handler);
            DataSheet dataSheet = new DataSheet();
            dataSheet.setDataList(handler.getDataArrayList());

            return dataSheet;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
