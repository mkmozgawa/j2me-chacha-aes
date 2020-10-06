/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cursednotepad;

import bc.crypto.engines.ChaChaEngine;
import bc.crypto.others.CipherParameters;
import bc.crypto.others.StreamCipher;
import bc.crypto.params.KeyParameter;
import bc.crypto.params.ParametersWithIV;
import java.io.IOException;

public class ChaChaManager {
    // it do be dancing

    public void doChaCha(boolean encrypt, byte[] ib, byte[] ob, byte[] key, byte[] iv) throws IOException {
        CipherParameters cp = new KeyParameter(key);
        ParametersWithIV params = new ParametersWithIV(cp, iv);
        StreamCipher engine = new ChaChaEngine();
        engine.init(encrypt, params);
        engine.processBytes(ib, 0, ib.length, ob, 0);
    }

    public void encrypt(byte[] key, byte[] ib, byte[] ob, byte[] iv) throws IOException {
        doChaCha(true, ib, ob, key, iv);
    }

    public void decrypt(byte[] key, byte[] ib, byte[] ob, byte[] iv) throws IOException {
        doChaCha(false, ib, ob, key, iv);
    }
}
