package game;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * JUnit 5 (with JUnitPlatform.class)
 *
 */
@RunWith(JUnitPlatform.class)
@DisplayName("Testing using JUnit 5")
class JUnit5Test {

	private PebbleGame classUnderTest;

	  @BeforeAll
	  public static void init() {
	    // Do something before ANY test is run in this class
	  }

	  @AfterAll
	  public static void done() {
	    // Do something after ALL tests in this class are run
	  }

	  @BeforeEach
	  public void setUp() throws Exception {
	    classUnderTest = new PebbleGame(3);
	  }

	  @AfterEach
	  public void tearDown() throws Exception {
	    classUnderTest = null;
	  }

	  @Test
	  @DisplayName("Adding pebbles > 0 to a black bag")
	  public void testAdd() {
	    assertAll(
	        () -> {
	          //
	          // Test #1
	          int[] numbersToFillBag = { 1, 2, 3, 4 };
	          List<Integer> expectedBag = new ArrayList<Integer>();
	          expectedBag.add(1);
	          expectedBag.add(2);
	          expectedBag.add(3);
	          expectedBag.add(4);
	          
	          BlackBag actualBag = new BlackBag(numbersToFillBag, 0);
	          assertEquals(expectedBag, actualBag.getBag());
	        },
	        () -> {
	          //
	          // Test #2
	          int[] numbersToFillBag = { 290, 111, 1, 2, 3, 4, 5, 6, 7, 8 };
	          List<Integer> expectedBag = new ArrayList<Integer>();
	          expectedBag.add(290);
	          expectedBag.add(111);
	          expectedBag.add(1);
	          expectedBag.add(2);
	          expectedBag.add(3);
	          expectedBag.add(4);
	          expectedBag.add(5);
	          expectedBag.add(6);
	          expectedBag.add(7);
	          expectedBag.add(8);
	          BlackBag actualBag = new BlackBag(numbersToFillBag, 0);
	          assertEquals(expectedBag, actualBag.getBag());
	        },
	        () -> {
	          //
	          // Test #3
	          int[] numbersToFillBag = { 1000 };
	          List<Integer> expectedBag = new ArrayList<Integer>();
	          expectedBag.add(1000);	          
	          BlackBag actualBag = new BlackBag(numbersToFillBag, 0);
	          assertEquals(expectedBag, actualBag.getBag());
	        });
	  }
	  
	  @Test
	  @DisplayName("Removing pebbles from black bag")
	  public void testRemove() {
	    assertAll(
	        () -> {
	          //
	          // Test #1
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
	        },
	        () -> {
	          //
	          // Test #2
	          int[] numbersToFillBag = { 290, 111, 1, 2, 3, 4, 5, 6, 7, 8 };
	          List<Integer> expectedBag = new ArrayList<Integer>();
	          expectedBag.add(290);
	          expectedBag.add(111);
	          expectedBag.add(1);
	          expectedBag.add(2);
	          expectedBag.add(3);
	          expectedBag.add(4);
	          expectedBag.add(5);
	          expectedBag.add(6);
	          expectedBag.add(7);
	          expectedBag.add(8);
	          expectedBag.remove(5);
	          BlackBag actualBag = new BlackBag(numbersToFillBag, 0);
	          actualBag.removePebble();
	          assertEquals(expectedBag.size(), actualBag.bagSize());
	        },
	        () -> {
	          //
	          // Test #3
	          int[] numbersToFillBag = { 1000 };
	          List<Integer> expectedBag = new ArrayList<Integer>();
	          expectedBag.add(1000);
	          expectedBag.remove(0);
	          BlackBag actualBag = new BlackBag(numbersToFillBag, 0);
	          actualBag.removePebble();
	          assertEquals(expectedBag.size(), actualBag.bagSize());
	        });
	  }
	  
	  

}
