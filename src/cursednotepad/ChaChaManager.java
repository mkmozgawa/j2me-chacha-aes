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
import java.io.InputStream;
import java.io.OutputStream;

public class ChaChaManager {
    // it do be dancing
    public void doChaCha(boolean encrypt, InputStream is, OutputStream os, byte[] key, byte[] iv) throws IOException {
        CipherParameters cp = new KeyParameter(key);
        ParametersWithIV params = new ParametersWithIV(cp, iv);
        StreamCipher engine = new ChaChaEngine();
        engine.init(encrypt, params);

        byte in[] = new byte[8192];
        byte out[] = new byte[8192];
        int len = 0;
        while (-1 != (len = is.read(in))) {
            len = engine.processBytes(in, 0, len, out, 0);
            os.write(out, 0, len);
        }
    }

    public void encrypt(InputStream is, OutputStream os, byte[] key, byte[] iv) throws IOException {
        doChaCha(true, is, os, key, iv);
    }

    public void decrypt(InputStream is, OutputStream os, byte[] key, byte[] iv) throws IOException {
        doChaCha(false, is, os, key, iv);
    }
}
