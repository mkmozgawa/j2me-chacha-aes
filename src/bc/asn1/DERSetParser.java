/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.asn1;

import java.io.IOException;

/**
 * @deprecated Use DLSetParser instead
 */
public class DERSetParser
    implements ASN1SetParser
{
    private ASN1StreamParser _parser;

    DERSetParser(ASN1StreamParser parser)
    {
        this._parser = parser;
    }

    /**
     * Return the next object in the SET.
     *
     * @return next object in SET.
     * @throws IOException if there is an issue loading the object.
     */
    public ASN1Encodable readObject()
        throws IOException
    {
        return _parser.readObject();
    }

    /**
     * Return an in memory, encodable, representation of the SET.
     *
     * @return a DERSet.
     * @throws IOException if there is an issue loading the data.
     */
    public ASN1Primitive getLoadedObject()
        throws IOException
    {
        return new DLSet(_parser.readVector());
    }

    /**
     * Return a DERSet representing this parser and its contents.
     *
     * @return a DERSet
     */
    public ASN1Primitive toASN1Primitive()
    {
        try
        {
            return getLoadedObject();
        }
        catch (IOException e)
        {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }
}

