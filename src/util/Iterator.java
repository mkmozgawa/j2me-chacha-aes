/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import java.util.NoSuchElementException;

public interface Iterator
{
    public abstract boolean hasNext();
    public abstract Object next() throws NoSuchElementException;
    public abstract void remove() throws RuntimeException,IllegalStateException;
}

