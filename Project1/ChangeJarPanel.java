package Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.text.NumberFormat;

/**
 * Creates a panel that calls various methods from the ChangeJar class
 *
 * @author Roger Ferguson
 */
public class ChangeJarPanel extends JPanel{

    private ChangeJar jar;

    NumberFormat fmt = NumberFormat.getCurrencyInstance();
    JButton takeOutButton, decButton, addButton, incButton, saveButton,
     loadButton, compareButton, equalButton, takeOutButton2, stringButton,
     createButton, createButton2;
    JTextField qField, dField, nField, pField, fileField, amountField;

    /** labels for message and credits */
    JLabel message, credits;

    public ChangeJarPanel(){

        // create the game object as well as the ChangeJarGUI Frame
        jar = new ChangeJar(100,2,3,4);
       new ChangeJar(100,2,3,4);

        // set the layout to GridBag
        setLayout(new GridLayout(14,2));
        setBackground(Color.lightGray);

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

        compareButton = new JButton("Compare");
        add(compareButton);

        equalButton = new JButton("Equate");
        add(equalButton);

        stringButton = new JButton("String");
        add(stringButton);

        createButton = new JButton("Create");
        add(createButton);

        fileField = new JTextField(".txt", 3);
        add(fileField);
        add(new JLabel("Filename:"));

        saveButton = new JButton("Save");
        add(saveButton);

        loadButton = new JButton("Load");
        add(loadButton);

        amountField = new JTextField("0.00",3);
        add(amountField);
        add(new JLabel("Amount:"));

        takeOutButton2 = new JButton("Take Out");
        add(takeOutButton2);

        createButton2 = new JButton("Create");
        add(createButton2);

        //add (new JLabel(" "));
        message = new JLabel("");
        add(message);

        add(new JLabel(""));
        credits = new JLabel();
        credits.setText(fmt.format(jar.getAmount()));
        add(new JLabel("Total:"));
        add(credits);

        takeOutButton.addActionListener(new ButtonListener());
        decButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        incButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
        compareButton.addActionListener(new ButtonListener());
        equalButton.addActionListener(new ButtonListener());
        takeOutButton2.addActionListener(new ButtonListener());
        stringButton.addActionListener(new ButtonListener());
        createButton.addActionListener(new ButtonListener());
        createButton2.addActionListener(new ButtonListener());
    }


    /****************************************************************
     Inner class to respond to the user action

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
                    JOptionPane.showMessageDialog(null,
                            "Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,
                            "Not enough specified coins for this operation");
                }
                message.setText("");
            }
            if(event.getSource() == decButton){
                try{
                    jar.dec();
                }
                catch (IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,
                            "No more pennies to take out");
                }
                message.setText("");
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
                    JOptionPane.showMessageDialog(null,
                            "Enter an integer in all fields");
                }
                message.setText("");
            }

            if(event.getSource() == incButton){
                jar.inc();
                message.setText("");
            }

            if(event.getSource() == saveButton){
                try {
                    jar.save(fileField.getText());
                }
                catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null,
                            "Unable to save with that name");
                }
                message.setText("");
            }

            if(event.getSource() == loadButton){
                try {
                    jar.load(fileField.getText());
                }
                catch(IllegalArgumentException i){
                    JOptionPane.showMessageDialog(null,
                            "Unable to load file");
                }
                catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null,
                            "Unable to find file");
                }
                message.setText("");
            }
            if(event.getSource() == compareButton) {
                try {
                    int result = jar.compareTo(ChangeJarPanelMain.staticJar);
                    if(result == 1)
                        message.setText("Jar is greater");
                    else if(result == -1)
                        message.setText("Jar is lesser");
                    else
                        message.setText("Jar is equal to");
                }
                catch(IllegalArgumentException i){
                    JOptionPane.showMessageDialog(null,
                            "Amount entered not valid");
                }
            }

            if(event.getSource() == equalButton) {
                try {
                    if(jar.equals(ChangeJarPanelMain.staticJar))
                        message.setText("Jar is equal to");
                    else
                        message.setText("Jar is not equal to");
                }
                catch(IllegalArgumentException i){
                    JOptionPane.showMessageDialog(null,
                            "Amount entered not valid");
                }
            }

            if(event.getSource() == takeOutButton2) {
                try {
                    double doubleAmount = Double.parseDouble(amountField.getText());
                    if(jar.takeOut(doubleAmount) == null){
                        JOptionPane.showMessageDialog(null,
                                "Unable to take out amount");
                    }

                }
                catch(IllegalArgumentException i){
                    JOptionPane.showMessageDialog(null,
                            "Unable to take out amount");
                }
                message.setText("");
            }

            if(event.getSource() == stringButton){
                message.setText(jar.toString());
            }

            if(event.getSource() == createButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar = new ChangeJar(quarters,dimes,nickels,pennies);
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,
                            "Unable to create jar with that change");
                }
                message.setText("");
            }

            if(event.getSource() == createButton2){
                try{
                    jar = new ChangeJar(amountField.getText());
                }

                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,
                            "Unable to create jar with that amount");
                }
                message.setText("");
            }

            credits.setText(fmt.format(jar.getAmount()));
        }
    }
}
