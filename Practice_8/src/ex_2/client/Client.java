package ex_2.client;

import ex_2.remote.ConfereeAdded;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;

public class Client extends JFrame {
    private JTextField portTextField;
    private JTextField hostTextField;
    private JTextField participant;
    private JTextField nameTextField;
    private JTextField familyNameTextField;
    private JTextField placeOfWorkTextField;
    private JTextField reportTitleTextField;
    private JTextField emailTextField;
    private JButton register;
    private JButton clear;
    private JButton getInfo;
    private JButton finish;


    Client(){
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900,600);
        setResizable(false);


        setVisible(true);
    }


    public static void main(String[] args) throws NamingException {
        Context context = new InitialContext();

        ConfereeAdded confereeAdded = (ConfereeAdded) context.lookup("rmi://localhost:1099/add");
    }
}
