/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.crypto.others;

public interface ExtendedDigest
    extends Digest
{
    /**
     * Return the size in bytes of the internal buffer the digest applies it's compression
     * function to.
     *
     * @return byte length of the digests internal buffer.
     */
    public int getByteLength();
}

