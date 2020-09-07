/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.util;

import util.Iterator;

/**
 * Utility class to allow use of Iterable feature in JDK 1.5+
 */
public interface Iterable
{
    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    Iterator iterator();
}

