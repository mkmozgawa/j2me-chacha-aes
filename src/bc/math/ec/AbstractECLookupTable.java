/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.math.ec;

public abstract class AbstractECLookupTable
    implements ECLookupTable
{
    public ECPoint lookupVar(int index)
    {
        return lookup(index);
    }
}

