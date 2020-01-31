package Project1;

import Lab1.Lock;

import java.io.*;
import java.util.*;

/**
 *
 * The purpose this class is to simulate a change jar. Jars can hold
 * quarters, dimes, nickels, and pennies. Various methods allow for
 * the ability to add or takeout change from the jar and comparing
 * amount with other jars.
 *
 *
 * @author Tim Nguyen (in collaboration with Ethan Grant)
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
     * @throws IllegalArgumentException when there are too many digits
     * beyond the decimal point
     */
    public ChangeJar(double amount){
        String stringAmount = "" + amount;

        //Throws an error if there are too many digits beyond the decimal point
        if(stringAmount.contains(".") && stringAmount.length() -
                stringAmount.indexOf('.') > 3)
            throw new IllegalArgumentException();

        int temp = (int) (amount * 100);
        this.convertToChange(temp);
    }

    /******************************************************************
     *
     *   This constructor creates a Change Jar from an existing
     *    Change Jar.
     *
     * @param other is an existing Change Jar
     * @throws IllegalArgumentException if the other jar fails changeCheck
     */

    public ChangeJar(ChangeJar other) {
        changeCheck(other.quarters, other.dimes, other.nickels, other.pennies);
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
     * @throws IllegalArgumentException when the entered String is not a
     * proper double or has too many digits beyond the decimal point
     */
    public ChangeJar(String amount){

        //Throws an error if the amount is not all digits or a decimal point
        int decimalCount = 0;
        for (int i = 0; i < amount.length(); i++){
            if(!Character.isDigit(amount.charAt(i)) && amount.charAt(i) != '.')
                throw new IllegalArgumentException();
            if(amount.charAt(i) == '.')
                decimalCount++;

            //Cannot have more than one decimal point
            if(decimalCount > 1)
                throw new IllegalArgumentException();
        }

        //Throws an error if there are too many digits beyond the decimal point
        if(amount.contains(".") && amount.length() - amount.indexOf('.') > 3)
            throw new IllegalArgumentException();

        //Throws an error if there is a decimal point right at the
        // end of the string
        if(amount.contains(".") && amount.indexOf('.') == amount.length() - 1)
            throw new IllegalArgumentException();

        int temp = (int) (Double.parseDouble(amount) * 100);
        this.convertToChange(temp);
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
        changeCheck(quarters, dimes, nickels, pennies);
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

    /******************************************************************
     *    Helper method tha converts all the change in a jar into pennies
     * @param temp ChangeJar converting into pennies
     * @return amount of pennies the ChangeJar is worth
     */
    private static int convertToPennies (ChangeJar temp) {
        return (temp.quarters * 25) + (temp.dimes * 10) +
                (temp.nickels * 5) + temp.pennies;
    }

    /******************************************************************
     *
     * @return amount of money in a Jar
     */
    public double getAmount () {
        return convertToPennies(this) / 100.0;
    }

    /******************************************************************
     *   Helper method to help construct a new jar
     * @param amount the amount of change being added (in pennies worth)
     * @throws IllegalArgumentException if it is a negative amount
     */
    private void convertToChange (int amount){
        if(amount < 0)
            throw new IllegalArgumentException();
        quarters = amount / 25;
        amount -= quarters * 25;
        dimes = amount / 10;
        amount -= dimes * 10;
        nickels = amount / 5;
        amount -= nickels * 5;
        pennies = amount;
    }

    /******************************************************************
     *   Helper method that throws out an error if the input parameters
     *   are not allowed
     * @param q is the number of quarters being tested
     * @param d is the number of dimes being tested
     * @param n is the number of nickels being tested
     * @param p is the number of pennies being tested
     */
    private void changeCheck(int q, int d, int n, int p){
        if (q < 0)
            throw new IllegalArgumentException();
        if (d < 0)
            throw new IllegalArgumentException();
        if (n < 0)
            throw new IllegalArgumentException();
        if (p < 0)
            throw new IllegalArgumentException();
    }

    /******************************************************************
     *   Either turns on or off the ability to mutate ChangeJars
     * @param selected true if mutation is enabled, false if mutation is disabled
     */
    public static void mutate(boolean selected) {
        mutation = selected;
    }

    /******************************************************************
     *   Determines if "this" ChangeJar has the same amount as the
     *   input Object
     * @param s is an Object (presumably a ChangeJar) that is being
     * equated to "this" ChangeJar
     * @return true or false depending on if "this" ChangeJar and
     * the passed Object are equal
     * @throws IllegalArgumentException if the Object is null or not
     * a ChangeJar
     */
    public boolean equals(Object s){
        if (s != null) {
            if (s instanceof ChangeJar) {
                ChangeJar temp = (ChangeJar) s;
                return this.getAmount() == temp.getAmount();
            }
            else
                throw new IllegalArgumentException();
        }
        else
            throw new IllegalArgumentException();
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
     * @return 1 if "this" Jar is greater than s, -1 if "this" Jar is
     * less than s and 0 if they are equal
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
     * @return 1 if jar1 is greater than jar2, -1 if jar1 is less than
     * jar and 0 if they are equal
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
     * @throws IllegalArgumentException if you try to take out more
     * than is available or changeCheck fails
     */
    public void takeOut(int quarters, int dimes, int nickels, int pennies) {
        if(mutation) {
            changeCheck(quarters, dimes, nickels, pennies);

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
     * @return a new ChangeJar based on the change taken out of
     * the original Jar
     * @throws IllegalArgumentException if the amount is negative,
     * is impossible to create a jar with the entered amount and
     * current jar, or there are too many digits beyond the decimal
     */
    public ChangeJar takeOut (double amount) {
        String stringAmount = "" + amount;

        //Throws an error if there are too many digits beyond the
        // decimal point
        if(stringAmount.contains(".") && stringAmount.length() -
                stringAmount.indexOf('.') > 3)
            throw new IllegalArgumentException();
        if(amount < 0)
            throw new IllegalArgumentException();
        if(mutation) {
            int intAmount = (int) (amount * 100);

            //Tests every possible combination of change in the
            // current jar to get the desired amount
            for(int p = 0; p <= this.pennies; p++)
                for (int n = 0; n <= this.nickels; n++)
                    for (int d = 0; d <= this.dimes; d++)
                        for (int q = 0; q <= this.quarters; q++) {
                            int currentAmount = (q * 25) +
                                    (d * 10) + (n * 5) + p;
                            if (currentAmount == intAmount) {
                                this.quarters -= q;
                                this.dimes -= d;
                                this.nickels -= n;
                                this.pennies -= p;
                                return new ChangeJar(q, d, n, p);
                            }
                        }
        }
        //If mutation is off, null is returned or if it is
        // impossible to get the amount.
        return null;
    }

    /******************************************************************
     * Decreases the amount in the Jar by 1 penny
     * @throws IllegalArgumentException if there are no more
     * pennies to take out
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
        changeCheck(quarters, dimes, nickels, pennies);
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
     *  Allows the jar to be printed as a String
     * @return A String summary of the change in the Jar
     */
    public String toString() {
        String s = "" + this.quarters + " quarter";

        //If quarters is not singular
        if(quarters != 1)
            s += "s";
        s += ", " + this.dimes + " dime";

        //If dimes is not singular
        if(dimes != 1)
            s += "s";
        s += ", " + this.nickels + " nickel";

        //If nickels is not singular
        if(nickels != 1)
            s += "s";
        s += ", " + this.pennies + " penn";

        //If pennies is not singular
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
            out = new PrintWriter(
                    new BufferedWriter(new FileWriter(fileName)));
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
     * @throws IllegalArgumentException there are no ints or
     * they are negative
     */
    public void load(String fileName) {
        if(!mutation)
            return;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Tries to find ints in the file but throws an
        // IllegalArgumentException if there are none.
        try {
            int tempQuarters = scanner.nextInt();
            int tempDimes = scanner.nextInt();
            int tempNickels = scanner.nextInt();
            int tempPennies = scanner.nextInt();

            //Need to test to make sure the values didn't
            // somehow become negative
            changeCheck(tempQuarters, tempDimes, tempNickels, tempPennies);
            quarters = tempQuarters;
            dimes = tempDimes;
            nickels = tempNickels;
            pennies = tempPennies;
        }
        catch(NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
    }

    /******************************************************************
     *  Gets the amount of quarters
     * @return the amount of quarters in the jar
     */
    public int getQuarters() {
        return quarters;
    }

    /******************************************************************
     *  Sets the amount of quarters
     * @param quarters is amount of quarters desired to be set to
     * @throws IllegalArgumentException if you try to set to a
     * negative value
     */
    public void setQuarters(int quarters) {
        if(quarters < 0)
            throw new IllegalArgumentException();
        if(mutation)
            this.quarters = quarters;
    }

    /******************************************************************
     *  Gets the amount of dimes
     * @return the amount of dimes in the jar
     */
    public int getDimes() {
        return dimes;
    }

    /******************************************************************
     *  Sets the amount of dimes
     * @param dimes is amount of dimes desired to be set to
     * @throws IllegalArgumentException if you try to set to a
     * negative value
     */
    public void setDimes(int dimes) {
        if(dimes < 0)
            throw new IllegalArgumentException();
        if(mutation)
            this.dimes = dimes;
    }

    /******************************************************************
     *   Gets the amount of nickels
     * @return the amount of nickels in the jar
     */
    public int getNickels() {
        return nickels;
    }

    /******************************************************************
     *   Sets the amount of nickels
     * @param nickels is amount of nickels desired to be set to
     * @throws IllegalArgumentException if you try to set to a
     * negative value
     */
    public void setNickels(int nickels) {
        if(nickels < 0)
            throw new IllegalArgumentException();
        if(mutation)
            this.nickels = nickels;
    }

    /******************************************************************
     *   Gets the amount of pennies
     * @return the amount of pennies in the jar
     */
    public int getPennies() {
        return pennies;
    }

    /******************************************************************
     *  Sets the amount of pennies
     * @param pennies is amount of pennies desired to be set to
     * @throws IllegalArgumentException if you try to set to a
     * negative value
     */
    public void setPennies(int pennies) {
        if(pennies < 0)
            throw new IllegalArgumentException();
        if(mutation)
            this.pennies = pennies;
    }

    /******************************************************************
     *   Returns status of mutation (primarily used to help with GUI)
     * @return whether mutation is true (on) or false (off)
     */
    public static boolean getMutationStatus(){
        return mutation;
    }
    
    /******************************************************************
     *   Main method used to run and test other methods
     * @param args
     */
    public static void main(String[] args) {
        ChangeJar s = new ChangeJar("2.82");
        System.out.println("2.82 Amount: \n" + s);

        s = new ChangeJar("8");
        System.out.println("8 Amount: \n" + s);

        s = new ChangeJar(".28");
        System.out.println(".28 Amount: \n" + s);

        s = new ChangeJar(1);
        System.out.println("1 Amount: \n" + s);

        s = new ChangeJar();
        System.out.println("0 Amount: \n" + s);

        s = new ChangeJar(1.22);
        System.out.println("1.22 Amount: \n" + s);

        ChangeJar s1 = new ChangeJar(s);
        System.out.println("1.22 Amount: \n" + s1);

        System.out.println("Equals True: \n" + s.equals(s1));

        s1 = new ChangeJar();
        System.out.println("Equals False: \n" + s.equals(s1));

        s = new ChangeJar();
        s1 = new ChangeJar();
        System.out.println("Equals True: \n" + ChangeJar.equals(s,s1));

        s = new ChangeJar(0.01);
        System.out.println("Equals False: \n" + ChangeJar.equals(s,s1));

        s = new ChangeJar(1.00);
        s1 = new ChangeJar();
        System.out.println("Compare 1: \n" + s.compareTo(s1));

        s = new ChangeJar(1.00);
        s1 = new ChangeJar(1.00);
        System.out.println("Compare 0: \n" + s.compareTo(s1));

        s = new ChangeJar();
        s1 = new ChangeJar(1.00);
        System.out.println("Compare -1: \n" + s.compareTo(s1));

        s = new ChangeJar(1,1,1,1);
        s.takeOut(1,1,1,1);
        System.out.println("0 TakeOut: \n" + s);

        s = new ChangeJar(1.00);
        s1 = new ChangeJar(1.00);
        s.takeOut(s1);
        System.out.println("0 TakeOut ChangeJar: \n" + s);

        s = new ChangeJar();
        s.add(1,1,1,1);
        System.out.println("0.41 Add: \n" + s.getAmount());

        s = new ChangeJar();
        s1 = new ChangeJar(1,1,1,1);
        s.add(s1);
        System.out.println("0.41 Add ChangeJar: \n" + s.getAmount());

        s = new ChangeJar();
        for (int i = 0; i < 100; i++) {
            s.inc();
        }
        System.out.println("100 Pennies Inc: \n" + s);

        s = new ChangeJar();
        s1 = new ChangeJar(1,1,1,1);
        s1.save("file");
        s.load("file");
        System.out.println("0.41 Save and Load \n" + s);

        s = new ChangeJar(1,1,1,1);
        s.takeOut(0.41);
        System.out.println("0 TakeOut Double \n" + s);

        s1 = new ChangeJar();
        System.out.println("0 Amount: \n" + s1);

        s1.add(1,1,1,100);
        System.out.println("1,1,1,100 Amount: \n" + s1);

        ChangeJar s2 = new ChangeJar(4,0,0,0);
        s2.add(0,0,0,100);
        for (int i = 0; i < 100; i++)
            s2.dec();
        System.out.println("amount: \n" + s2);
    }
}