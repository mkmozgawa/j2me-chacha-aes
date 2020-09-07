/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.math.ec.endo;

import math.BigInteger;

public interface GLVEndomorphism extends ECEndomorphism
{
    BigInteger[] decomposeScalar(BigInteger k);
}

