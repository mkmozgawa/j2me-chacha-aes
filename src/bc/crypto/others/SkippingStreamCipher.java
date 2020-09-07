/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.crypto.others;

/**
 * General interface for a stream cipher that supports skipping.
 */
public interface SkippingStreamCipher
    extends StreamCipher, SkippingCipher
{
}

