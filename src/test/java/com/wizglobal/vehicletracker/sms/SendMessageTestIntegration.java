/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Otieno Lawrence
 * 
 */
public class SendMessageTestIntegration extends TestCase {

	private SendMessage sendMessage;
	private String receiver = "0717959557";
	private String message = "Wassup Mr. Pichie!";

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		sendMessage = new SendMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.sms.SendMessage#send(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSend() throws Exception {
		assertTrue( "SMS Must Be Sent!", sendMessage.send( receiver, message ) );
	}
}
