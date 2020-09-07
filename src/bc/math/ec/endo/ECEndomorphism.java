/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.math.ec.endo;

import bc.math.ec.ECPointMap;

public interface ECEndomorphism
{
    ECPointMap getPointMap();

    boolean hasEfficientPointMap();
}

