/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.math.field;

import math.BigInteger;

public interface FiniteField
{
    BigInteger getCharacteristic();

    int getDimension();
}

