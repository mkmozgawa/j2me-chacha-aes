/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.math.field;

public interface ExtensionField extends FiniteField
{
    FiniteField getSubfield();

    int getDegree();
}

