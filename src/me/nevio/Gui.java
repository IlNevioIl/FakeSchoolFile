package me.nevio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Gui extends JFrame {


    public Gui() {
        Var.jf1 = new JFrame();
        Var.jf1.setSize(400, 300);
        Var.jf1.setTitle("SchoolFileFaker by Nevio");
        Var.jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Var.jf1.setLayout(null);
        Var.jf1.setResizable(false);
        Var.jf1.setLocationRelativeTo(null);
        Var.jf1.requestFocus();
        Var.jf1.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel name = new JLabel();
        name.setText("File Name:");
        Var.jf1.add(name);

        JTextField jTextField= new JTextField(21);
        Var.jf1.add (jTextField);

        JLabel dot = new JLabel();
        dot.setText(".");
        Var.jf1.add(dot);

        String formats[] = {"pdf", "odt"};
        JComboBox cb = new JComboBox(formats);
        Var.jf1.add(cb);

        JLabel size = new JLabel();
        size.setText("Size:");
        Var.jf1.add(size);

        JTextField jSizeField= new JTextField(10);
        Var.jf1.add (jSizeField);

        String sizeString[] = {"mb", "gb"};
        JComboBox sizeBox = new JComboBox(sizeString);
        Var.jf1.add(sizeBox);

        Var.jf1.add(Var.startbutton);

        Var.jf1.setVisible(true);

        Var.startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int wantedSizeInt = 0;

                sizeBox.getSelectedItem();

                if (sizeBox.getSelectedItem().equals("mb")) {
                    wantedSizeInt = (int) 1e6;
                }

                if (sizeBox.getSelectedItem().equals("gb")) {
                    wantedSizeInt = (int) 1e9;
                }

                String FileName = jTextField.getText();
                String FileSize = jSizeField.getText();
                String desktop = System.getProperty("user.home") + "\\Desktop\\";

                double wantedSize = Double.parseDouble(System.getProperty("size", FileSize));

                Random random = new Random();
                File file = new File(desktop+FileName+"."+cb.getSelectedItem());
                long start = System.currentTimeMillis();
                PrintWriter writer1 = null;
                try {
                    writer1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")), false);
                } catch (UnsupportedEncodingException | FileNotFoundException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                int counter = 0;
                while (true) {
                    String sep = "";
                    for (int i = 0; i < 100; i++) {
                        int number = random.nextInt(1000) + 1;
                        writer1.print(sep);
                        writer1.print(number / 1e3);
                        sep = " ";
                    }
                    writer1.println();
                    //Check to see if the current size is what we want it to be
                    // 1666 means it checks after every mb. 1666x2 = checks after every 2mb and so on.
                    /// 333 means it checks after every 0.2 mb = able to create files that are not a full mb.
                    if (++counter == 330) {
                        System.out.printf("Size: %.3f GB%n", file.length() / 1e9);
                        if (file.length() >= wantedSize * wantedSizeInt) {
                            writer1.close();
                            break;
                        } else {
                            counter = 0;
                        }
                    }
                }
                long time = System.currentTimeMillis() - start;
                System.out.printf("Took %.1f seconds to create a file of %.3f GB", time / 1e3, file.length() / 1e9);
            }
            });
        }
}
