/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.asn1;

/**
 * General interface implemented by ASN.1 STRING objects for extracting the content String.
 */
public interface ASN1String
{
    /**
     * Return a Java String representation of this STRING type's content.
     * @return a Java String representation of this STRING.
     */
    public String getString();
}
