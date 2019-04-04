package mybeans.myApplication;

import mybeans.grafic.DataSheetGraph;
import mybeans.table.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Test extends JFrame {

    private DataSheetTable dataSheetTable = null;

    private DataSheet dataSheet;

    private DataSheetGraph dataSheetGraph;

    private JButton exitButton;

    private JButton clearButton;

    private JButton saveButton;

    private JPanel footer;

    private JButton readButton;

    private final JFileChooser fileChooser = new JFileChooser();

    Test() throws ParserConfigurationException, SAXException, IOException {
        footer = new JPanel();
        footer.setLayout(new FlowLayout());
        setLayout(new BorderLayout());
        dataSheet = new DataSheet();
        setSize(950,600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dataSheetTable = new DataSheetTable();
        dataSheetGraph = new DataSheetGraph();
        dataSheetGraph.setDataSheet(dataSheet);
//        dataSheetGraph.setConnected(true);
        add(dataSheetGraph, BorderLayout.CENTER);
        add(dataSheetTable, BorderLayout.WEST);
        dataSheetTable.getTableModel().setDataSheet(dataSheet);
        dataSheetTable.getTableModel().addDataSheetChangeListener(
                new DataSheetChangeListener() {
                    public void dataChanged(DataSheetChangeEvent e) {
                        dataSheetGraph.revalidate();
                        dataSheetGraph.repaint();
                    }
                });
        fileChooser.setCurrentDirectory(new java.io.File("."));

        exitButton = new JButton("Завершить");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });

        clearButton = new JButton("Очистить");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataSheet = new DataSheet();
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    ex.printStackTrace();
                }
                dataSheetTable.getTableModel().setDataSheet(dataSheet);
                dataSheetTable.revalidate();
                dataSheetGraph.setDataSheet(dataSheet);
            }
        });

        saveButton = new JButton("Сохранить");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                    String fileName = fileChooser.getSelectedFile().getPath();
                    DataSheetToXML.saveXMLDoc(DataSheetToXML.createDataSheetDOM(dataSheet), fileName);

                    JOptionPane.showMessageDialog(null,
                            "File " + fileName.trim() + " saved!", "Результаты сохранены",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        readButton = new JButton("Открыть");
        readButton.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                                                 String fileName = fileChooser.getSelectedFile().getPath();
                                                 dataSheet = SAXRead.XMLReadData(fileName);
                                                 dataSheetTable.getTableModel().setDataSheet(dataSheet);
                                                 dataSheetTable.revalidate();
                                                 dataSheetGraph.setDataSheet(dataSheet);
                                             }
                                         }
                                     });
        footer.add(readButton);
        footer.add(saveButton);
        footer.add(clearButton);
        footer.add(exitButton);
        add(footer, BorderLayout.PAGE_END);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            new Test();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
