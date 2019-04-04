package mybeans.table;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DataSheetTable extends JPanel {

    private JPanel panel;
    private JTable table;
    private DataSheetTableModel dataSheetTableModel;
    private JScrollPane scrollPane;
    private JButton addButton;
    private JButton delButton;

    public DataSheetTable() throws ParserConfigurationException, SAXException, IOException {
        this.setLayout(new BorderLayout());

        table = new JTable();
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        dataSheetTableModel = new DataSheetTableModel();
        table.setModel(dataSheetTableModel);
//        table.setFocusable(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(200,200);
        add(scrollPane);

        panel = new JPanel();
        addButton = new JButton("Add (+)");
        delButton = new JButton("Del (-)");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dataSheetTableModel.setRowCount(dataSheetTableModel.getRowCount()+1);
                dataSheetTableModel.getDataSheet().addDataItem(new Data());
                table.revalidate();
                dataSheetTableModel.fireDataSheetChange();
            }
        });

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (dataSheetTableModel.getRowCount() > 1) {
                    dataSheetTableModel.setRowCount(dataSheetTableModel.getRowCount() - 1);
                    dataSheetTableModel.getDataSheet().removeDataItem(
                            dataSheetTableModel.getDataSheet().size()-1);
                    table.revalidate();
                    dataSheetTableModel.fireDataSheetChange();
                } else {
                    dataSheetTableModel.getDataSheet().getDataItem(0).setDate("");
                    dataSheetTableModel.getDataSheet().getDataItem(0).setX(0);
                    dataSheetTableModel.getDataSheet().getDataItem(0).setY(0);
                    table.revalidate();
                    table.repaint();
                    dataSheetTableModel.fireDataSheetChange();
                }
            }
        });

        panel.setLayout(new FlowLayout());
        panel.add(addButton);
        panel.add(delButton);

        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void revalidate() {
        if (table != null) table.revalidate();
    }


    public DataSheetTableModel getTableModel() {
        return dataSheetTableModel;
    }
}
