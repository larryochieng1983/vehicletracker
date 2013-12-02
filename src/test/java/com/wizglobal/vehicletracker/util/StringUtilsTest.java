package com.wizglobal.vehicletracker.util;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kenny
 */
public class StringUtilsTest {
    
    public StringUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsNonEmptyString() {
	System.out.println("isNonEmptyString");
	String string = "";
	boolean expResult = false;
	boolean result = StringUtils.isNonEmptyString(string);
	assertEquals(expResult, result);
	string = null;
	assertFalse("supposed to be an empty string.", StringUtils.isNonEmptyString(string));
	assertTrue(StringUtils.isNonEmptyString("some string"));
	assertFalse(StringUtils.isNonEmptyString("  "));
	assertFalse(StringUtils.isNonEmptyString(""));
    }
}
