package Project1;

import Lab1.Lock;

import java.io.*;
import java.util.*;

/**
 *
 * The purpose this class is to simulate a change Jar.
 *
 *
 * 	 NOTE: MUCH MORE CODING IS NEEDED IN THESE METHODS, and you
 * 	 will need to comply with the Java Style Guide.
 * 	 HOWEVER,  HERE IS SOME STARTING CODE.
 *
 * @suthor
 */

public class ChangeJar {

    /** The number of quarters in the current Jar */
    private int quarters;

    /** The number of dimes in the current Jar */
    private int dimes;

    /** The number of nickels in the current Jar */
    private int nickels;

    /** The number of pennies in the current Jar */
    private int pennies;

    /** Boolean that determines if the Jar can be altered or not */
    private static boolean mutation = true;

    /******************************************************************
     *  The is the default constuctor for ChangeJar
     */

    public ChangeJar() {
    }

    /******************************************************************
     *  This constructor creates a ChangeJar based on the amount being
     *   put in
     *
     * @param amount is the amount of money being put inside the ChangeJar
     */
    public ChangeJar(double amount){
        if(amount < 0)
            throw new IllegalArgumentException();
        quarters = (int) (amount / 0.25);
        amount -= quarters * 0.25;
        dimes = (int) (amount / 0.10);
        amount -= dimes * 0.10;
        nickels = (int) (amount / 0.05);
        amount -= nickels * 0.05;
        pennies = (int) (amount / 0.01);
    }

    /******************************************************************
     *
     *   This constructor creates a Change Jar from an existing
     *    Change Jar.
     *
     * @param other is an existing Change Jar
     */

    public ChangeJar(ChangeJar other) {
        quarters = other.quarters;
        dimes = other.dimes;
        nickels = other.nickels;
        pennies = other.pennies;
    }

    /******************************************************************
     *  This constructor creates a ChangeJar based on the amount being
     *   put in
     *
     * @param amount is the amount of money being put inside the ChangeJar
     */
    public ChangeJar(String amount){
        for (int i = 0; i < amount.length(); i++){
            if(Character.isDigit(amount.charAt(i)) || amount.charAt(i) != '.')
                throw new IllegalArgumentException();
        }

        double temp = Double.parseDouble(amount);

        if(temp < 0)
            throw new IllegalArgumentException();

        quarters = (int) (temp / 0.25);
        temp -= quarters * 0.25;
        dimes = (int) (temp / 0.10);
        temp -= dimes * 0.10;
        nickels = (int) (temp / 0.05);
        temp -= nickels * 0.05;
        pennies = (int) (temp / 0.01);
    }

    /******************************************************************
     *
     *   This constructor creates a Change Jar from with some
     *   initial values for Quarters, Dimes, Nickels, and Pennies.
     **
     * @param quarters is the number of quarters to start with.
     * @param dimes is the number of dimes to start with.
     * @param nickels is the number of nicels to start with.
     * @param pennies is the number of pennies to start with.
     *                 
     */
    public ChangeJar(int quarters, int dimes, int nickels, int pennies) {
        super();

        if (quarters < 0)
            throw new IllegalArgumentException();
        if (dimes < 0)
            throw new IllegalArgumentException();
        if (nickels < 0)
            throw new IllegalArgumentException();
        if (pennies < 0)
            throw new IllegalArgumentException();

        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

// REMEBER to use the Java Style Guide for the rest of your code.

    private static int convertToPennies (ChangeJar temp) {
        return (temp.quarters * 25) + (temp.dimes * 10) + (temp.nickels * 5) + temp.pennies;
    }

    public static void mutate(boolean selected) {
        mutation = selected;
    }

    public boolean equals(ChangeJar s) {
        return true;
    }

    public boolean equals(Object s){
        if (s != null) {
            if (s instanceof ChangeJar) {
                ChangeJar temp = (ChangeJar) s;
                return this.getAmount() == temp.getAmount();
            }
        }
        return false;
    }

    public static boolean equals(ChangeJar jar1, ChangeJar jar2) {
        return jar1.getAmount() == jar2.getAmount();
    }

    public int compareTo(ChangeJar s) {
        if(this.getAmount() > s.getAmount())
            return 1;
        else if(this.getAmount() < s.getAmount())
            return -1;
        return 0;
    }

    public static int compareTo(ChangeJar jar1, ChangeJar jar2) {
        if(jar1.getAmount() > jar2.getAmount())
            return 1;
        else if(jar1.getAmount() < jar2.getAmount())
            return -1;
        return 0;
    }

    public void takeOut(int quarters, int dimes, int nickels, int pennies) {
        if (quarters < 0)
            throw new IllegalArgumentException();
        if (dimes < 0)
            throw new IllegalArgumentException();
        if (nickels < 0)
            throw new IllegalArgumentException();
        if (pennies < 0)
            throw new IllegalArgumentException();

        if(mutation) {
            this.quarters -= quarters;
            this.dimes -= dimes;
            this.nickels -= nickels;
            this.pennies -= pennies;
        }

    }

    public void takeOut(ChangeJar other) {
        if (mutation) {
            quarters -= other.quarters;
            dimes -= other.dimes;
            nickels -= other.nickels;
            pennies -= other.pennies;
        }
    }

    public ChangeJar takeOut (double amount) {
        if(amount < 0)
            throw new IllegalArgumentException();
        if(mutation){

        }
        return null;
    }

    public void dec(){
        if(mutation)
            pennies -= 1;
    }

    public void add(int quarters, int dimes, int nickels, int pennies) {
        if (quarters < 0)
            throw new IllegalArgumentException();
        if (dimes < 0)
            throw new IllegalArgumentException();
        if (nickels < 0)
            throw new IllegalArgumentException();
        if (pennies < 0)
            throw new IllegalArgumentException();

        if (mutation) {
            this.quarters += quarters;
            this.dimes += dimes;
            this.nickels += nickels;
            this.pennies += pennies;
        }
    }

    public void add(ChangeJar other) {
        if (mutation) {
            quarters += other.quarters;
            dimes += other.dimes;
            nickels += other.nickels;
            pennies += other.pennies;
        }
    }

    public void inc(){
        if(mutation)
            pennies += 1;
    }

    public String toString() {

        // here is a hint
        //String s = this.quarters + " Quarter" + ((quarters != 1) ? "s" : "") + "\n";
        String s = "" + this.quarters + " quarter";
        if(quarters != 1)
            s += "s";
        s += " " + this.dimes + " dime";
        if(dimes != 1)
            s += "s";
        s += " " + this.nickels + " nickel";
        if(nickels != 1)
            s += "s";
        s += " " + this.pennies + " penn";
        if(pennies != 1)
            s += "ies";
        else
            s += "y";
        return s;
    }

    public void save(String fileName) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(quarters);
        out.println(dimes);
        out.println(nickels);
        out.println(pennies);
        out.close();
    }


    public void load(String fileName) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        quarters = scanner.nextInt();
        dimes = scanner.nextInt();
        nickels = scanner.nextInt();
        pennies = scanner.nextInt();

    }

    public double getAmount () {
        return convertToPennies(this) / 100.0;
    }


    public static void main(String[] args) {
        ChangeJar test = new ChangeJar(0,0,0,0);
        System.out.println(test);
    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }


}