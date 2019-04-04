package mybeans.table;

import mybeans.table.DataSheet;
import org.xml.sax.SAXException;

import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataSheetTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private int columnCount = 3;
    private int rowCount = 1;
    private DataSheet dataSheet = null;

    private String[] columnNames = {"Date", "X Value", "Y Value"};

    public DataSheetTableModel() throws ParserConfigurationException, SAXException, IOException {
        super();
        dataSheet = new DataSheet();
        /*dataSheet.uploadFromFile(new File("/home/evgen/IdeaProjects/Practice_5/src/main/resources/data.xml"));*/

    }

    public DataSheet getDataSheet() {
        return dataSheet;
    }

    public void setDataSheet(DataSheet dataSheet) {
        this.dataSheet = dataSheet;
        rowCount = this.dataSheet.size();
        fireDataSheetChange();
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            double d;
            if (dataSheet != null) {
                if (columnIndex == 0) {
                    dataSheet.getDataItem(rowIndex).setDate((String) value);
                } else if (columnIndex == 1) {
                    d = Double.parseDouble((String) value);
                    dataSheet.getDataItem(rowIndex).setX(d);
                } else if (columnIndex == 2) {
                    d = Double.parseDouble((String) value);
                    dataSheet.getDataItem(rowIndex).setY(d);
                }
            }
            fireDataSheetChange();
        } catch (Exception ex) {}
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataSheet != null) {
            if (columnIndex == 0)
                return dataSheet.getDataItem(rowIndex).getDate();
            else if (columnIndex == 1)
                return dataSheet.getDataItem(rowIndex).getX();
            else if (columnIndex == 2)
                return dataSheet.getDataItem(rowIndex).getY();
        }
        return null;
    }

    public void setRowCount(int rowCount) {
        if (rowCount > 0)
            this.rowCount = rowCount;
    }

    // список слушателей
    private ArrayList<DataSheetChangeListener> listenerList = new
            ArrayList<DataSheetChangeListener>();

    // один универсальный объект-событие
    private DataSheetChangeEvent event = new DataSheetChangeEvent(this);

    // метод, присоединяющий слушателя события
    public void addDataSheetChangeListener(DataSheetChangeListener l) {
        listenerList.add(l);
    }

    // метод, отсоединяющий слушателя события
    public void removeDataSheetChangeListener(DataSheetChangeListener l) {
        listenerList.remove(l);
    }

    // метод, оповещающий зарегистрированных слушателей о событии
    protected void fireDataSheetChange() {
        Iterator<DataSheetChangeListener> i = listenerList.iterator();
        while ( i.hasNext() )
            (i.next()).dataChanged(event);
    }


}
