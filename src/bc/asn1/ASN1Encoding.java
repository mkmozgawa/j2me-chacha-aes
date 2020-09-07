/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.asn1;

/**
 * Supported encoding formats.
 */
public interface ASN1Encoding
{
    /**
     * DER - distinguished encoding rules.
     */
    static final String DER = "DER";

    /**
     * DL - definite length encoding.
     */
    static final String DL = "DL";

    /**
     * BER - basic encoding rules.
     */
    static final String BER = "BER";
}

