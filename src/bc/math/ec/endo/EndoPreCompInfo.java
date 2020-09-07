/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.math.ec.endo;

import bc.math.ec.ECPoint;
import bc.math.ec.PreCompInfo;

public class EndoPreCompInfo implements PreCompInfo
{
    protected ECEndomorphism endomorphism;

    protected ECPoint mappedPoint;

    public ECEndomorphism getEndomorphism()
    {
        return endomorphism;
    }

    public void setEndomorphism(ECEndomorphism endomorphism)
    {
        this.endomorphism = endomorphism;
    }

    public ECPoint getMappedPoint()
    {
        return mappedPoint;
    }

    public void setMappedPoint(ECPoint mappedPoint)
    {
        this.mappedPoint = mappedPoint;
    }
}

