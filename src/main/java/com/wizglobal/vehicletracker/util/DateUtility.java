package com.wizglobal.vehicletracker.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple date formatting utility class
 * @author Otieno Lawrence
 */

public class DateUtility {

	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	public static String getCurrentDateTime() {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date date = new Date();
            return dateFormat.format(date);
	}


}