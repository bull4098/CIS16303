package Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.text.NumberFormat;

/**
 * Write a description of class ChangeJarPanel here.
 *
 * @author Roger Ferguson
 */
public class ChangeJarPanel extends JPanel{

    private ChangeJar jar;

    NumberFormat fmt = NumberFormat.getCurrencyInstance();
    JButton takeOutButton, decButton, addButton, incButton, saveButton, loadButton;
    JTextField qField, dField, nField, pField, fileField;

    /** labels for message and credits */
    JLabel message, credits;

    public ChangeJarPanel(){

        // create the game object as well as the ChangeJarGUI Frame
        jar = new ChangeJar(100,2,3,4);

        // set the layout to GridBag
        setLayout(new GridLayout(9,2));
        setBackground(Color.lightGray);

        // get Die #2 from game and place on ChangeJarGUI
        qField = new JTextField("0", 3);
        add(qField);
        add(new JLabel("Quarters:"));

        dField = new JTextField("0", 3);
        add(dField);
        add(new JLabel("Dimes:"));

        nField = new JTextField("0", 3);
        add(nField);
        add(new JLabel("Nickels:"));

        pField = new JTextField("0", 3);
        add(pField);
        add(new JLabel("Pennies:"));

        takeOutButton = new JButton("Take Out");
        add(takeOutButton);

        decButton = new JButton("Decrement");
        add(decButton);

        addButton = new JButton("Add");
        add(addButton);

        incButton = new JButton("Increment");
        add(incButton);

        credits = new JLabel();
        credits.setText(fmt.format(jar.getAmount()));
        //add (new JLabel(" "));
        add(new JLabel("Total:"));
        add(credits);

        fileField = new JTextField(".txt", 3);
        add(fileField);
        add(new JLabel("Filename:"));

        saveButton = new JButton("Save");
        add(saveButton);

        loadButton = new JButton("Load");
        add(loadButton);

        // register the listeners
        takeOutButton.addActionListener(new ButtonListener());
        decButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        incButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
    }


    /****************************************************************
     Inner class to repond to the user action

     ****************************************************************/
    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent event){

            int quarters, dimes, nickels, pennies;

            if (event.getSource() == takeOutButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.takeOut(quarters,dimes,nickels,pennies);
                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if(event.getSource() == decButton){
                try{
                    jar.dec();
                }
                catch (IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null, "No more pennies to take out");
                }
            }
            if(event.getSource() == addButton){
                try {
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.add(quarters, dimes, nickels, pennies);
                }
                catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }
            }

            if(event.getSource() == incButton){
                jar.inc();
            }

            if(event.getSource() == saveButton){
                jar.save(fileField.getText());
            }

            if(event.getSource() == loadButton){
//                try {
                    jar.load(fileField.getText());
//                }
//                catch(FileNotFoundException o){
//                    JOptionPane.showMessageDialog(null, "Unable to find file");
//                }
            }
            // update the labels
            credits.setText(fmt.format(jar.getAmount()));
        }

    }
}
