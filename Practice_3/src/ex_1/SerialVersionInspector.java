package ex_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class SerialVersionInspector extends JFrame{
    JButton show, clear;
    JTextField textField;
    JTextField textField1;
    JPanel header;
    JPanel middle;
    SerialVersionInspector(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setName("Serial Version Inspector");
        this.setLayout(new BorderLayout());
        header=new JPanel();
        header.setLayout(new FlowLayout());
        header.add(new JLabel("Full class name: "));
        textField = new JTextField(15);
        textField1 = new JTextField(16);
        header.add(textField);
        show = new JButton("show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textField1.setText((String.valueOf(show(textField.getText()))));
            }
        });
        header.add(show);

        middle = new JPanel();
        middle.setLayout(new FlowLayout());
        middle.add(new JLabel("Serial Version: "));
        middle.add(textField1);
        clear = new JButton("clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textField1.setText("");
                textField.setText("");
            }
        });
        middle.add(clear);

        this.add(middle, BorderLayout.CENTER);
        this.add(header, BorderLayout.NORTH);
        this.setVisible(true);
    }

    public static long show(String className){
        try {
            Class clazz = Class.forName(className);
            Field field = clazz.getDeclaredField("serialVersionUID");
            field.setAccessible(true);
            return field.getLong(clazz.getClassLoader());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static void main(String[] args) {
        new SerialVersionInspector();
        System.out.println(show("ex_2.M"));
    }
}
