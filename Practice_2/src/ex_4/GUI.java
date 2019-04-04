package ex_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    JPanel header;
    JTextField type, size;
    JButton button;
    JTextArea textArea;

    GUI(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setLayout(new BorderLayout());
        textArea = new JTextArea(15,15);
        header=new JPanel();
        type = new JTextField(10);
        size = new JTextField(3);
        button = new JButton("Create");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea.setText("");
                Arr arr = new Arr(type.getText(), Integer.valueOf(size.getText()));
                textArea.setText(arr.toString());
            }
        });
        header.setLayout(new FlowLayout());
        header.add(type);
        header.add(size);
        header.add(button);
        this.add(header, BorderLayout.NORTH);
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
