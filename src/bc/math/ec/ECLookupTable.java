/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.math.ec;

public interface ECLookupTable
{
    int getSize();
    ECPoint lookup(int index);
    ECPoint lookupVar(int index);
}

