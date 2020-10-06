/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cursednotepad;

import java.io.IOException;
import security.SecureRandom;

/**
 *
 * @author IEUser
 */
public class CipherManager {
    private byte[] iv;
    private int cipherType;

    public CipherManager(int cipherType)
    {
        SecureRandom sr = SecureRandom.getInstance("SHA256PRNG");
        if (cipherType == 0) { // Rijndael-256
            this.iv = new byte[32];
        }
        if (cipherType == 1) { // ChaCha20
            this.iv = new byte[8];
        }
        sr.nextBytes(this.iv);
        this.cipherType = cipherType;
    }

    byte[] encrypt(byte[] key, byte[] ibEnc, byte[] obEnc) {
        if (this.cipherType == 0) {
            try {
                AESManager cipher = new AESManager();
                cipher.encrypt(key, ibEnc, obEnc, this.iv);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (this.cipherType == 1) {
            try {
                ChaChaManager cipher = new ChaChaManager();
                cipher.encrypt(key, ibEnc, obEnc, this.iv);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return obEnc;
    }

    byte[] decrypt(byte[] key, byte[] ibEnc, byte[] obEnc) {
        if (this.cipherType == 0) {
            try {
                AESManager cipher = new AESManager();
                cipher.decrypt(key, ibEnc, obEnc, this.iv);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (this.cipherType == 1) {
            try {
                ChaChaManager cipher = new ChaChaManager();
                cipher.decrypt(key, ibEnc, obEnc, this.iv);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return obEnc;
    }

}
