package com.wizglobal.vehicletracker.util;

/**
 *
 * @author kenny
 */
public class StringUtils {
    /**
     * Perform a null check and and trim trailing spaces to test if it is non-zero length.
     *
     * @param string test string.
     * @return true iff is not null and {@link String#length() > 0} after {@link String#trim()}
     */
    public static boolean isNonEmptyString(String string) {
	return string != null && string.trim().length() > 0 ? true : false;
    }
}
