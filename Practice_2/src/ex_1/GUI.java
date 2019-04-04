package ex_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GUI extends JFrame {
    JPanel header, body, footer;
    JButton search, clear, exit;
    JTextArea textArea;
    JTextField textField;

    GUI(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        body = new JPanel();
        body.setLayout(new FlowLayout());
        textArea = new JTextArea(25,40);
        header = new JPanel();
        footer = new JPanel();
        search = new JButton("Анализ");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea.setText("");
                String name = textField.getText();
                textArea.append(inf(name));
            }
        });
        clear = new JButton("Очистить");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textField.setText("");
                textArea.setText("");
            }
        });
        exit = new JButton("Выход");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        footer.add(search);
        footer.add(clear);
        footer.add(exit);
        header.add(new JLabel("Введите полное имя класса:"));
        textField = new JTextField(10);
        header.add(textField);
        body.add(new JScrollPane(textArea));
        this.add(header,BorderLayout.NORTH);
        this.add(body,BorderLayout.CENTER);
        this.add(footer,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private String inf(String name){
        String result="";
        try {
            Class clazz = Class.forName(name);
            result+=("Имя: " + clazz.getName());
            if(clazz.isPrimitive()) return result+="\nЭто примитив!";
            result+=("\nПакет: " + clazz.getPackageName());
            result+=("\nСуперкласс: " + clazz.getSuperclass().getName());
            result+=("\nСписок полей:");
            for (Field f:clazz.getFields()) {
                result+=("\n  "+f.toGenericString());
            }
            result+=("\nСписок конструкторов:");
            for (Constructor c:clazz.getDeclaredConstructors()) {
                result+=("\n  "+c.toGenericString());
            }
            result+=("\nСписок методов:");
            for (Method m:clazz.getDeclaredMethods()) {
                result+=("\n  "+m.toGenericString());
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            textArea.append("Ничего не найдено!");
            return result;
        }
        return result;
    }

    public static void main(String[] args) {
        new GUI();
    }
}
