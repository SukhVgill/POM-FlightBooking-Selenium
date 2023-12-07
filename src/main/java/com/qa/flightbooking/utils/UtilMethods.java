package com.qa.flightbooking.utils;

import java.time.Instant;

/**
 * Util Methods --- No Element related methods in this class
 * @author sukhvirgill
 *
 */
public class UtilMethods {

	/**
	 * Gives Unique time stamp.
	 * @return long current time stamp
	 */
	public static long getUniqueTimeStamp() {
		return Instant.now().toEpochMilli();
	}
	
	/**
	 * Convert long value to String.
	 * @param value
	 * @return String value
	 */
	public static String convertLongToString(long value) {
		return String.valueOf(value);
	}
}
