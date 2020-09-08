/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cursednotepad;

import bc.crypto.engines.AESEngine;
import bc.crypto.modes.CBCBlockCipher;
import bc.crypto.others.DataLengthException;
import bc.crypto.paddings.PaddedBufferedBlockCipher;
import bc.crypto.params.KeyParameter;
import bc.crypto.params.ParametersWithIV;
import java.io.IOException;

public class AESManager {

    public void doAES(boolean encrypt, byte[] ib, byte[] ob, byte[] key, byte[] iv) throws IOException {
        try {
            AESEngine engine = new AESEngine();
            CBCBlockCipher blockCipher = new CBCBlockCipher(engine);
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(blockCipher);
            KeyParameter keyParam = new KeyParameter(key);
            ParametersWithIV keyParamWithIV = new ParametersWithIV(keyParam, iv, 0, 16);
            cipher.init(encrypt, keyParamWithIV);
        } catch (DataLengthException ex) {
            ex.printStackTrace();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        } 
    }

    public void encrypt(byte[] key, byte[] ib, byte[] ob, byte[] iv, String plaintext) throws IOException {
        doAES(true, ib, ob, key, iv);
    }

    public void decrypt(byte[] key, byte[] ib, byte[] ob, byte[] iv, String plaintext) throws IOException {
        doAES(false, ib, ob, key, iv);
    }
}
