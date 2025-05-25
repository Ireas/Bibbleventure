package dev.bibbelventure.utility.exception;

/**
 * General internal server exception.
 *
 * @author raschke
 */
public class InternalServerException
  extends Exception
{
    /**
     * Constructor.
     *
     * @param message - exception message
     */
    public InternalServerException( String message )
    {
        super( message );
    }

    /**
     * Constructor, allows for stack trace
     *
     * @param message - exception message
     * @param throwable - cause for exception
     */
    public InternalServerException( String message, Throwable throwable )
    {
        super( message, throwable );
    }
}

