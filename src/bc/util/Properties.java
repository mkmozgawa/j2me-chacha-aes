/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.util;

import math.BigInteger;
import util.Collections;
import util.HashMap;
import util.HashSet;
import util.Map;
import util.Set;
import util.StringTokenizer;

/**
 * Utility method for accessing system properties.
 */
public class Properties
{
    private Properties()
    {
    }

    /**
     * Return whether a particular override has been set to true.
     *
     * @param propertyName the property name for the override.
     * @return true if the property is set to "true", false otherwise.
     */
    public static boolean isOverrideSet(String propertyName)
    {
            String p = getPropertyValue(propertyName);

            return "true".equalsIgnoreCase(p);
    }

    public static BigInteger asBigInteger(String propertyName)
    {
        String p = getPropertyValue(propertyName);

        if (p != null)
        {
            return new BigInteger(p);
        }

        return null;
    }

    public static Set asKeySet(String propertyName)
    {
        Set set = new HashSet();

        String p = getPropertyValue(propertyName);

        if (p != null)
        {
            StringTokenizer sTok = new StringTokenizer(p, ",");
            while (sTok.hasMoreElements())
            {
                set.add(Strings.toLowerCase(sTok.nextToken()).trim());
            }
        }

        return Collections.unmodifiableSet(set);
    }

    public static String getPropertyValue(final String propertyName)
    {
	return System.getProperty(propertyName);
    }
}

