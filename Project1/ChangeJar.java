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
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
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

        //Throws an error if the amount is not all digits or a decimal point
        for (int i = 0; i < amount.length(); i++){
            int decimalCount = 0;
            if(!Character.isDigit(amount.charAt(i)) && amount.charAt(i) != '.')
                throw new IllegalArgumentException();
            if(amount.charAt(i) == '.')
                decimalCount++;
            //Cannot have more than one decimal point
            if(decimalCount > 1)
                throw new IllegalArgumentException();

        }

        //Throws an error if there are too many digits beyond the decimal point
        if(amount.contains(".") && amount.indexOf('.') - amount.length() > 3)
            throw new IllegalArgumentException();

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
     * @param nickels is the number of niclels to start with.
     * @param pennies is the number of pennies to start with.
     *                 
     */
    public ChangeJar(int quarters, int dimes, int nickels, int pennies) {
        super();

        errorFunds(quarters, dimes, nickels, pennies);

        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

// REMEBER to use the Java Style Guide for the rest of your code.


    /******************************************************************
     *    Helper method tha converts all the change in a jar into pennies
     * @param temp ChangeJar converting into pennies
     * @return amount of pennies the ChangeJar is worth
     */
    private static int convertToPennies (ChangeJar temp) {
        return (temp.quarters * 25) + (temp.dimes * 10) + (temp.nickels * 5) + temp.pennies;
    }

    /******************************************************************
     *   Either turns on or off the ability to mutate ChangeJars
     * @param selected true if mutation is enabled, false if mutation is disabled
     */
    public static void mutate(boolean selected) {
        mutation = selected;
    }

    //public boolean equals(ChangeJar s) {
    //    return true;
    //}

    /******************************************************************
     *   Determines if "this" ChangeJar has the same amount as the input Object
     * @param s is an Object (presumably a ChangeJar) that is being equated to "this" ChangeJar
     * @return true or false depending on if "this" ChangeJar and the passed Object are equal
     */
    public boolean equals(Object s){
        if (s != null) {
            if (s instanceof ChangeJar) {
                ChangeJar temp = (ChangeJar) s;
                return this.getAmount() == temp.getAmount();
            }
        }
        return false;
    }

    /******************************************************************
     * Tells whether or not two ChangeJars are equal in amount
     * @param jar1 is the first Jar being compared
     * @param jar2 is the second Jar being compared
     * @return true or false depending on if jar1 has the same amount as jar2
     */
    public static boolean equals(ChangeJar jar1, ChangeJar jar2) {
        return jar1.getAmount() == jar2.getAmount();
    }

    /******************************************************************
     *  Compares "this" ChangeJar to another ChangeJar
     * @param s is the second Jar being compared with
     * @return 1 if "this" Jar is greater than s, -1 if "this" Jar is less than s
     * and 0 if they are equal
     */
    public int compareTo(ChangeJar s) {
        if(this.getAmount() > s.getAmount())
            return 1;
        else if(this.getAmount() < s.getAmount())
            return -1;
        return 0;
    }

    /******************************************************************
     *   Compares the amount of two ChangeJars
     * @param jar1 is the first Jar being compared
     * @param jar2 is the second Jar being compared
     * @return 1 if jar1 is greater than jar2, -1 if jar1 is less than jar2
     * and 0 if they are equal
     */
    public static int compareTo(ChangeJar jar1, ChangeJar jar2) {
        if(jar1.getAmount() > jar2.getAmount())
            return 1;
        else if(jar1.getAmount() < jar2.getAmount())
            return -1;
        return 0;
    }

    /******************************************************************
     *   Takes change out of the Jar
     * @param quarters is the number of quarters being taken out
     * @param dimes is the number of dimes being taken out
     * @param nickels is the number of nickels being taken out
     * @param pennies is the number of pennies being taken out
     */
    public void takeOut(int quarters, int dimes, int nickels, int pennies) {
        if(mutation) {
            errorFunds(quarters, dimes, nickels, pennies);
            //Throws error if you try to take out more change than there is the Jar
            if(this.quarters - quarters < 0 || this.dimes - dimes < 0 ||
                    this.nickels - nickels < 0 || this.pennies - pennies < 0)
                throw new IllegalArgumentException();

            this.quarters -= quarters;
            this.dimes -= dimes;
            this.nickels -= nickels;
            this.pennies -= pennies;
        }

    }

    /******************************************************************
     *  Takes out change of "this" Jar based on the change in another Jar
     * @param other Jar that holds the amount that is being taken out
     */
    public void takeOut(ChangeJar other) {
        this.takeOut(other.quarters, other.dimes, other.nickels, other.pennies);
    }

    /******************************************************************
     *  Takes an amount out of "this" Jar to create another Jar
     * @param amount is the amount of money being taken out
     * @return a new ChangeJar based on the change taken out of the original Jar
     */
    public ChangeJar takeOut (double amount) {
        if(amount < 0)
            throw new IllegalArgumentException();
        if(mutation){

        }
        return null;
    }

    /******************************************************************
     * Decreases the amount in the Jar by 1 penny
     */
    public void dec(){
        if(mutation) {
            //Cannot take out a penny if there are no pennies
            if(pennies == 0)
                throw new IllegalArgumentException();
            pennies -= 1;
        }
    }

    /******************************************************************
     *   Adds change to "this" Jar
     * @param quarters is the number of quarters being added
     * @param dimes is the number of dimes being added
     * @param nickels is the number of nickels being added
     * @param pennies is the number of pennies being added
     */
    public void add(int quarters, int dimes, int nickels, int pennies) {
        errorFunds(quarters, dimes, nickels, pennies);
        if (mutation) {
            this.quarters += quarters;
            this.dimes += dimes;
            this.nickels += nickels;
            this.pennies += pennies;
        }
    }

    /******************************************************************
     *   Adds change to "this" Jar based off of change in the other Jar
     * @param other is the Jar whose change is being added
     */
    public void add(ChangeJar other) {
        if (mutation) {
            quarters += other.quarters;
            dimes += other.dimes;
            nickels += other.nickels;
            pennies += other.pennies;
        }
    }

    /******************************************************************
     *    Adds 1 penny to the Jar
     */
    public void inc(){
        if(mutation)
            pennies += 1;
    }

    /******************************************************************
     *
     * @return A String summary of the change in the Jar
     */
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

    /******************************************************************
     *   Saves the data of a Jar to a file
     * @param fileName is the name of the file the Jar is being saved to
     */
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

    /******************************************************************
     *   Loads the data of a previously saved Jar
     * @param fileName is the name of the file being loaded from
     */
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

    /******************************************************************
     *
     * @return amount of money in a Jar
     */
    public double getAmount () {
        return convertToPennies(this) / 100.0;
    }

    /******************************************************************
     *   Helper method that throws out an error if the input parameters are not allowed
     * @param q is the number of quarters being tested
     * @param d is the number of dimes being tested
     * @param n is the number of nickels being tested
     * @param p is the number of pennies being tested
     */
    private void errorFunds(int q, int d, int n, int p){
        if (quarters < 0)
            throw new IllegalArgumentException();
        if (dimes < 0)
            throw new IllegalArgumentException();
        if (nickels < 0)
            throw new IllegalArgumentException();
        if (pennies < 0)
            throw new IllegalArgumentException();

    }


    public static void main(String[] args) {
        ChangeJar s = new ChangeJar("2.82");
        System.out.println("2.82 Amount: \n" + s);

        s = new ChangeJar("8");
        System.out.println("8 Amount: \n" + s);

        s = new ChangeJar(".28");
        System.out.println(".28 Amount: \n" + s);

        ChangeJar s1 = new ChangeJar();
        System.out.println("0 Amount: \n" + s1);

        s1.add(1,1,1,100);
        System.out.println("1,1,1,100 Amount: \n" + s1);

        ChangeJar s2 = new ChangeJar(41.99);
        s2.add(0,0,0,99);
        for (int i = 0; i < 100; i++)
            s2.dec();
        System.out.println("amount: \n" + s2);

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