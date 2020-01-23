package Project1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeJarTest {

	/**
	 *  Your assignment is to write many more test cases to
	 *  have a robust testing, more than just 100% coverage.
	 *
	 *  Some examples are provided to help you get started
	 */


	// Testing valid constructors with wide range of values
	@Test
	public void testConstructor() {
		ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);

		assertEquals (6, jar1.getQuarters());
		assertEquals (5, jar1.getDimes());
		assertEquals (4, jar1.getNickels());
		assertEquals (2, jar1.getPennies());

		ChangeJar jar2 = new ChangeJar();
		assertEquals (0, jar2.getQuarters());
		assertEquals (0, jar2.getDimes());
		assertEquals (0, jar2.getNickels());
		assertEquals (0, jar2.getPennies());

		ChangeJar jar3 = new ChangeJar(jar1);
		assertEquals (6, jar3.getQuarters());
		assertEquals (5, jar3.getDimes());
		assertEquals (4, jar3.getNickels());
		assertEquals (2, jar3.getPennies());
	}

	// Testing the constructor that takes a double
	@Test
	public void testDoubleConstructor() {
		ChangeJar test = new ChangeJar(1.34);
		assertEquals(5, test.getQuarters());
		assertEquals(0, test.getDimes());
		assertEquals(1, test.getNickels());
		assertEquals(4, test.getPennies());
	}

	// Testing the constructor that takes a string
	@Test
	public void testStringConstructor() {
		ChangeJar test = new ChangeJar("1.3");
		assertEquals(5, test.getQuarters());
		assertEquals(0, test.getDimes());
		assertEquals(1, test.getNickels());
		assertEquals(0, test.getPennies());
	}

	// Testing the constructor that takes a double with too many numbers beyond the decimal
	@Test (expected = IllegalArgumentException.class)
	public void testDoubleConstructorTooLong() {
		ChangeJar test = new ChangeJar(1.111);
	}

	// Testing the constructor that takes a string with too many numbers beyond the decimal
	@Test (expected = IllegalArgumentException.class)
	public void testStringConstructorTooLong() {
		ChangeJar test = new ChangeJar("1.111");
	}

	// Testing the constructor that takes a string with non digits
	@Test (expected = IllegalArgumentException.class)
	public void testStringConstructorNonDigit() {
		ChangeJar test = new ChangeJar("abc");
	}

	// Testing the constructor that takes a string with too many decimals
	@Test (expected = IllegalArgumentException.class)
	public void testStringConstructorTooManyDecimals() {
		ChangeJar test = new ChangeJar("1.3.4");
	}

	// Testing the constructor that takes a double with a negative amount
	@Test (expected = IllegalArgumentException.class)
	public void testDoubleConstructorNegative() {
		ChangeJar test = new ChangeJar(-1.34);
	}

	// Testing the constructor that takes a string with a negative amount
	@Test (expected = IllegalArgumentException.class)
	public void testStringConstructorNegative() {
		ChangeJar test = new ChangeJar("-1.34");
	}

	// testing valid takeOut with wide range of
	// quarters, dimes, nickels, pennies
	@Test
	public void testTakeOut1() {
		ChangeJar jar = new ChangeJar(3,3,2,2);
		jar.takeOut(1,1,1,1);
		assertEquals (2, jar.getQuarters());
		assertEquals (2, jar.getDimes());
		assertEquals (1, jar.getNickels());
		assertEquals (1, jar.getPennies());
	}

	// testing valid takeOut with wide range of amounts
	@Test
	public void testTakeOut2() {
		ChangeJar jar1 = new ChangeJar(5,3,4,3);
		ChangeJar jar2 = jar1.takeOut(1.22);

		assertEquals (1, jar1.getQuarters());
		assertEquals (1, jar1.getDimes());
		assertEquals (4, jar1.getNickels());
		assertEquals (1, jar1.getPennies());

		assertEquals (4, jar2.getQuarters());
		assertEquals (2, jar2.getDimes());
		assertEquals (0, jar2.getNickels());
		assertEquals (2, jar2.getPennies());
	}

	// Testing takeOut method with too many numbers beyond the decimal
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutTooLong() {
		ChangeJar jar1 = new ChangeJar(1.34);
		ChangeJar jar2 = jar1.takeOut(1.111);
	}

	// Testing takeOut method with a negative amount
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegativeAmount() {
		ChangeJar jar1 = new ChangeJar(1.34);
		ChangeJar jar2 = jar1.takeOut(-1);
	}

	// Testing takeOut method where it is impossible to take out the exact amount
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutImpossibleAmount() {
		ChangeJar jar1 = new ChangeJar(100, 0, 0, 0);
		ChangeJar jar2 = jar1.takeOut(0.05);
	}

	// Testing standard decrement
	@Test
	public void testDec(){
		ChangeJar jar = new ChangeJar(0,0,0,1);
		jar.dec();
		assertEquals(0, jar.getPennies());
	}

	// Testing decrement when there are no pennies left
	@Test (expected = IllegalArgumentException.class)
	public void testDecNoPennies(){
		ChangeJar jar = new ChangeJar(1,1,1,0);
		jar.dec();
	}

	// testing add for valid low numbers
	@Test
	public void testAdd() {
		ChangeJar jar = new ChangeJar();
		jar.add(2,3,4,5);
		assertEquals (2, jar.getQuarters());
		assertEquals (3, jar.getDimes());
		assertEquals (4, jar.getNickels());
		assertEquals (5, jar.getPennies());
	}

	// Testing add with another ChangeJar
	@Test
	public void testAdd2(){
		ChangeJar jar1 = new ChangeJar();
		ChangeJar jar2 = new ChangeJar(2,3,4,5);
		jar1.add(jar2);
		assertEquals (2, jar1.getQuarters());
		assertEquals (3, jar1.getDimes());
		assertEquals (4, jar1.getNickels());
		assertEquals (5, jar1.getPennies());
	}

	// Testing increment
	@Test
	public void testInc(){
		ChangeJar jar = new ChangeJar();
		jar.inc();
		assertEquals(1, jar.getPennies());
	}

	// Testing equals for valid numbers
	@Test
	public void testEqual () {
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 5, 4, 2);

		assertFalse(jar1.equals(jar2));
		assertTrue(jar1.equals(jar3));
	}

	// Testing the static equals
	@Test
	public void testEqual2(){
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 5, 4, 2);

		assertFalse(ChangeJar.equals(jar1, jar2));
		assertTrue(ChangeJar.equals(jar1, jar3));
	}

	// Testing equals with a null Object
	@Test(expected = IllegalArgumentException.class)
	public void testNullEqual(){
		ChangeJar jar1 = new ChangeJar(1.34);
		ChangeJar jar2 = null;
		jar1.equals(jar2);
	}

	//Testing equals with an Object that's not a ChangeJar
	@Test(expected = IllegalArgumentException.class)
	public void testOtherObjectEqual(){
		ChangeJar jar1 = new ChangeJar(1.45);
		String jar2 = "this is a ChangeJar";
		jar1.equals(jar2);
	}

	// testing compareTo all returns
	@Test
	public void testCompareTo () {
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 3, 4, 2);
		ChangeJar jar4 = new ChangeJar(2, 5, 4, 2);

		assertTrue(jar2.compareTo(jar1) > 0);
		assertTrue(jar3.compareTo(jar1) < 0);
		assertTrue(jar1.compareTo(jar4) == 0);
	}

	// testing static compareTo all returns
	@Test
	public void testCompareTo2 () {
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 3, 4, 2);
		ChangeJar jar4 = new ChangeJar(2, 5, 4, 2);

		assertTrue(ChangeJar.compareTo(jar2, jar1) > 0);
		assertTrue(ChangeJar.compareTo(jar3, jar1) < 0);
		assertTrue(ChangeJar.compareTo(jar1, jar4) == 0);
	}

	// load and save combined.
	@Test
	public void testLoadSave() {
		ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);

		jar1.save("file1");
		jar1 = new ChangeJar();  // resets to zero

		jar1.load("file1");
		assertTrue(jar1.equals(jar2));
	}

	// IMPORTANT: only one test per exception!!!

	// testing negative numbers for pennies in takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegPennies() {
		ChangeJar jar1 = new ChangeJar(2, 2, 2, 2);
		jar1.takeOut(1,1,1,-1);
	}

	// testing negative number for nickels in takeOut
	@Test
	(expected = IllegalArgumentException.class)
	public void testTakeOutNegNickels() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.takeOut(1,1,-1,1);
	}

	// testing negative number for dimes in takeOut
	@Test
	(expected = IllegalArgumentException.class)
	public void testTakeOutNegDimes() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.takeOut(1,-1,1,1);
	}

	// testing negative number of quarters in takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegQuarters() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.takeOut(-1,1,1,1);
	}

	// Testing take out more quarters than available
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutTooManyQuarters(){
		ChangeJar jar = new ChangeJar(5,5,5,5);
		jar.takeOut(6,1,1,1);
	}

	// Testing take out more quarters than available
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutTooManyDimes(){
		ChangeJar jar = new ChangeJar(5,5,5,5);
		jar.takeOut(1,6,1,1);
	}

	// Testing take out more quarters than available
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutTooManyNickels(){
		ChangeJar jar = new ChangeJar(5,5,5,5);
		jar.takeOut(1,1,6,1);
	}

	// Testing take out more quarters than available
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutTooManyPennies(){
		ChangeJar jar = new ChangeJar(5,5,5,5);
		jar.takeOut(1,1,1,6);
	}

	// testing negative numbers for pennies in add
	@Test(expected = IllegalArgumentException.class)
	public void testAddNegPennies() {
		ChangeJar jar1 = new ChangeJar(2, 2, 2, 2);
		jar1.add(1,1,1,-1);
	}

	// testing negative number for nickels in add
	@Test
			(expected = IllegalArgumentException.class)
	public void testAddNegNickels() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.add(1,1,-1,1);
	}

	// testing negative number for dimes in add
	@Test
			(expected = IllegalArgumentException.class)
	public void testAddOutNegDimes() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.add(1,-1,1,1);
	}

	// testing negative number of quarters in add
	@Test(expected = IllegalArgumentException.class)
	public void testAddNegQuarters() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.add(-1,1,1,1);
	}

	// testing negative number quarters for the constructor
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegQuarters() {
		new ChangeJar(-300, 0, 0, 0);
	}

	// testing negative number dimes for the constructor
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegDimes() {
		new ChangeJar(0,-300,0,0);
	}

	// testing negative number nickels for the constructor
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegNickels() {
		new ChangeJar(0,0,-300,0);
	}

	// testing negative number pennies for the constructor
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegPennies() {
		new ChangeJar(0,0,0,-300);
	}

	// Testing takeOut when mutation is off
	@Test
	public void testMutationOffTakeOut() {
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar(1,2,3,4);
		test.takeOut(1,1,1,1);
		assertEquals(1, test.getQuarters());
		assertEquals(2, test.getDimes());
		assertEquals(3, test.getNickels());
		assertEquals(4, test.getPennies());
		ChangeJar.mutate(true);
	}

	// Testing takeOut when mutation is off
	@Test
	public void testMutationOffTakeOut2() {
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar(1,2,3,4);
		ChangeJar test2 = new ChangeJar(1,1,1,1);
		test.takeOut(test2);
		assertEquals(1, test.getQuarters());
		assertEquals(2, test.getDimes());
		assertEquals(3, test.getNickels());
		assertEquals(4, test.getPennies());
		ChangeJar.mutate(true);
	}

	// Testing takeOut when mutation is off
	@Test(expected = IllegalArgumentException.class)
	public void testMutationOffTakeOut3() {
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar(1,2,3,4);
		ChangeJar test2 = test.takeOut(0.04);
		ChangeJar.mutate(true);
	}

	// Testing dec when mutation is off
	@Test
	public void testMutationOffDec() {
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar(1,2,3,4);
        test.dec();
		assertEquals(1, test.getQuarters());
		assertEquals(2, test.getDimes());
		assertEquals(3, test.getNickels());
		assertEquals(4, test.getPennies());
		ChangeJar.mutate(true);
	}

	// Testing add when mutation is off
	@Test
	public void testMutationOffAdd() {
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar(1,2,3,4);
		test.add(1,1,1,1);
		assertEquals(1, test.getQuarters());
		assertEquals(2, test.getDimes());
		assertEquals(3, test.getNickels());
		assertEquals(4, test.getPennies());
		ChangeJar.mutate(true);
	}

	// Testing add when mutation is off
	@Test
	public void testMutationOffAdd2() {
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar(1,2,3,4);
		ChangeJar test2 = new ChangeJar(1,1,1,1);
		test.add(test2);
		assertEquals(1, test.getQuarters());
		assertEquals(2, test.getDimes());
		assertEquals(3, test.getNickels());
		assertEquals(4, test.getPennies());
		ChangeJar.mutate(true);
	}

	// Testing inc when mutation is off
	@Test
	public void testMutationOffInc() {
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar(1,2,3,4);
		test.inc();
		assertEquals(1, test.getQuarters());
		assertEquals(2, test.getDimes());
		assertEquals(3, test.getNickels());
		assertEquals(4, test.getPennies());
		ChangeJar.mutate(true);
	}

	// Testing setQuarters when mutation is off
	@Test
	public void testMutationOffSetQuarters(){
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar();
		test.setQuarters(10);
		assertEquals(0, test.getQuarters());
		ChangeJar.mutate(true);
	}

	// Testing setDimes when mutation is off
	@Test
	public void testMutationOffSetDimes(){
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar();
		test.setDimes(10);
		assertEquals(0, test.getDimes());
		ChangeJar.mutate(true);
	}

	// Testing setNickels when mutation is off
	@Test
	public void testMutationOffSetNickels(){
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar();
		test.setNickels(10);
		assertEquals(0, test.getNickels());
		ChangeJar.mutate(true);
	}

	// Testing setPennies when mutation is off
	@Test
	public void testMutationOffSetPennies(){
		ChangeJar.mutate(false);
		ChangeJar test = new ChangeJar();
		test.setPennies(10);
		assertEquals(0, test.getPennies());
		ChangeJar.mutate(true);
	}

}
