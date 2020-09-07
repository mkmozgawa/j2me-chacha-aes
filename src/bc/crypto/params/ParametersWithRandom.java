/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.crypto.params;

import security.SecureRandom;

import bc.crypto.others.CipherParameters;
import bc.crypto.others.CryptoServicesRegistrar;

public class ParametersWithRandom
    implements CipherParameters
{
    private SecureRandom        random;
    private CipherParameters    parameters;

    public ParametersWithRandom(
        CipherParameters    parameters,
        SecureRandom        random)
    {
        this.random = CryptoServicesRegistrar.getSecureRandom(random);
        this.parameters = parameters;
    }

    public ParametersWithRandom(
        CipherParameters    parameters)
    {
        this(parameters, null);
    }

    public SecureRandom getRandom()
    {
        return random;
    }

    public CipherParameters getParameters()
    {
        return parameters;
    }
}

