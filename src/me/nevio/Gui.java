package me.nevio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Gui extends JFrame {

    public Gui() {

        Var.jf1 = new JFrame();
        Var.jf1.setSize(Var.screenwidth, Var.screenheight);
        Var.jf1.setTitle("SchoolFileFaker by Nevio");
        Var.jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Var.jf1.setLayout(null);
        Var.jf1.setResizable(false);
        Var.jf1.setLocationRelativeTo(null);
        Var.jf1.requestFocus();
        Var.jf1.setLayout(new FlowLayout(FlowLayout.CENTER));

        JTextField jTextField= new JTextField(20);
        Var.jf1.add (jTextField);

        String formats[] = {"pdf", "odt"};
        JComboBox cb = new JComboBox(formats);
        Var.jf1.add(cb);
        Var.jf1.add(Var.jb3);
        Var.jf1.setVisible(true);

        Var.jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String FileName = jTextField.getText();
                String desktop = System.getProperty("user.home") + "\\Desktop\\";
                File file = new File(desktop+FileName+"."+cb.getSelectedItem());
                try {
                    FileWriter writer = new FileWriter(file);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}