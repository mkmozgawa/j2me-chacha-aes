/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bc.util.encoders;

/**
 * Exception thrown if an attempt is made to decode invalid data, or some other failure occurs.
 */
public class DecoderException
    extends IllegalStateException
{
    private Throwable cause;

    DecoderException(String msg, Throwable cause)
    {
        super(msg);

        this.cause = cause;
    }

    public Throwable getCause()
    {
        return cause;
    }
}

