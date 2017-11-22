package game;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	 
	@Test
	public void testAddBlackBag(){
		int[] numbersToFillBag = { 1, 2, 3, 4 };
        List<Integer> expectedBag = new ArrayList<Integer>();
        expectedBag.add(1);
        expectedBag.add(2);
        expectedBag.add(3);
        expectedBag.add(4);
        
        BlackBag actualBag = new BlackBag(numbersToFillBag, 0);
        assertEquals(expectedBag, actualBag.getBag());
	}
	
	@Test
	public void testRemoveBlackBag() {
		int[] numbersToFillBag = { 1, 2, 3, 4 };
        List<Integer> expectedBag = new ArrayList<Integer>();
        expectedBag.add(1);
        expectedBag.add(2);
        expectedBag.add(3);
        expectedBag.add(4);
        expectedBag.remove(2);
        BlackBag actualBag = new BlackBag(numbersToFillBag, 0);
        actualBag.removePebble();
        assertEquals(expectedBag.size(), actualBag.getBag().size());
	}
	 @Before
	 public void setup() {
	         System.setOut(new PrintStream(outContent));
	         System.setErr(new PrintStream(errContent));
	 }
	
	@Test
	public void testUnknownFileInput() {

		PebbleGame.getFile("unknownTest.txt", 3, 0);
		String expected = "File Not Found ";
		
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testwrongFileInput() {

		PebbleGame.getFile("wrongTest.txt", 3, 0);
		String expected = "Invalid value found in file";
		
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testTooShortFileInput() {

		PebbleGame.getFile("smallTest.txt", 3, 0);
		String expected = "Bagsize is insufficient";
		
		assertEquals(expected, outContent.toString());
	}
	
	@After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

}
