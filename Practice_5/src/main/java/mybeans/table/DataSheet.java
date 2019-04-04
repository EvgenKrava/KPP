package mybeans.table;

import org.xml.sax.SAXException;
import mybeans.myApplication.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataSheet {

    private List<Data> dataList;

    private SAXParser saxParser;

    private Handler handler;

    public DataSheet() throws ParserConfigurationException, SAXException {
        dataList = new ArrayList<Data>();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParser = saxParserFactory.newSAXParser();
        handler = new Handler();
        dataList.add(new Data());
    }

    public void uploadFromFile(File file) throws IOException, SAXException {
        saxParser.parse(file,handler);
        dataList = handler.getDataArrayList();
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataItem(int rowIndex, Data data){
        dataList.set(rowIndex, data);
    }

    public Data getDataItem(int rowIndex) {
        if(dataList.size()!=0)
            return dataList.get(rowIndex);
        return null;
    }

    public void addDataItem(Data data){
        dataList.add(data);
    }

    public int size() {
        return dataList.size();
    }

    /*public void addRow(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date=""+day+"."+month+"."+year;
        dataList.add(new Data(date,0,0));
    }*/

    public void removeDataItem(int i) {
        dataList.remove(i);
    }
}
