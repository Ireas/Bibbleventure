package dev.bibbelventure;

import dev.bibbelventure.utility.exception.InternalServerException;
import dev.bibbelventure.utility.logging.GlobalLogger;

/**
 * TODO
 *
 * @author raschke
 */
public class QuickTest
{
    public static void main( String[] args )
    {
        try
        {
            try
            {

                throw new InternalServerException( "a" );
            }
            catch ( InternalServerException e )
            {
                throw new InternalServerException( "b", e );
            }
        }
        catch ( Exception e )
        {
            GlobalLogger.error( e );
        }

    }
}
