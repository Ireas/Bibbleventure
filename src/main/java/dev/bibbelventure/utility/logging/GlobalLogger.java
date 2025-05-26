package dev.bibbelventure.utility.logging;

/**
 * Global static logging utility class.
 *
 * @author raschke
 */
public class GlobalLogger
{
    // maximum log level
    private static final int MAX_LEVEL = 100;
    // available log levels
    private static final int ERROR = 1;
    private static final int WARNING = 2;
    private static final int INFO = 3;

    // ANSI colors taken from https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    /**
     * Prints a throwable with error styling to console.
     *
     * @param throwable - latest throwable, stack trace will also be printed
     */
    public static void error( Throwable throwable )
    {
        error( extractMessage( throwable ) );
    }

    /**
     * Prints a throwable with warning styling to console.
     *
     * @param throwable - latest throwable, stack trace will also be printed
     */
    public static void warning( Throwable throwable )
    {
        warning( extractMessage( throwable ) );
    }

    /**
     * Prints a throwable with info styling to console.
     *
     * @param throwable - latest throwable, stack trace will also be printed
     */
    public static void info( Throwable throwable )
    {
        info( extractMessage( throwable ) );
    }

    /**
     * Prints a message with error styling to console.
     *
     * @param message - message that will be shown in console
     */
    public static void error( String message )
    {
        printToConsole( ERROR, message );
    }

    /**
     * Prints a message with warning styling to console.
     *
     * @param message - message that will be shown in console
     */
    public static void warning( String message )
    {
        printToConsole( WARNING, message );
    }

    /**
     * Prints a message with info styling to console.
     *
     * @param message - message that will be shown in console
     */
    public static void info( String message )
    {
        printToConsole( INFO, message );
    }

    /**
     * Prints the message to console, if {@link #MAX_LEVEL} allows for message severity. Additionally, formats the message depending on
     * level.
     *
     * @param level - severity level, {@see #ERROR}, {@see #WARNING}, {@see #INFO}
     * @param message - message that get printed
     */
    private static void printToConsole( int level, String message )
    {
        // check configured level
        if ( level > MAX_LEVEL )
        {
            return;
        }

        // print in color
        switch ( level )
        {
            case ERROR -> System.out.println( ANSI_RED + ">>> ERROR\n" + message + ANSI_RESET );
            case WARNING -> System.out.println( ANSI_YELLOW + ">>> WARNING\n" + message + ANSI_RESET );
            case INFO -> System.out.println( ANSI_BLUE + ">>> INFO\n" + message + ANSI_RESET );
        }
    }

    /**
     * Converts an exception into a readable message including its stack trace.
     *
     * @param throwable - source throwable from which the message gets extracted
     * @return extracted message including stack trace
     */
    private static String extractMessage( Throwable throwable )
    {
        // get message
        StringBuilder message = new StringBuilder();

        Throwable cause = throwable;
        while ( cause != null )
        {
            message.append( formatThrowable( cause ) );
            cause = cause.getCause();
        }

        return message.toString();
    }

    /**
     * Formats a throwable into a readable string including its stack trace and cause.
     *
     * @param throwable - source throwable
     * @return formatted message including trace and cause
     */
    private static String formatThrowable( Throwable throwable )
    {
        StringBuilder output = new StringBuilder();
        output.append( "\t" );
        output.append( throwable.getMessage() );

        StackTraceElement[] stackTrace = throwable.getStackTrace();
        for ( StackTraceElement element : stackTrace )
        {
            output.append( formatStackTraceElement( element ) );
        }

        return output.toString();
    }

    /**
     * Formats a StackTraceElement into a readable string.
     *
     * @param element - source stack trace element
     * @return formatted message
     */
    private static String formatStackTraceElement( StackTraceElement element )
    {
        StringBuilder output = new StringBuilder();
        output.append( " at " );
        output.append( element.getClassName() );
        output.append( element.getMethodName() );
        output.append( " (line " ).append( element.getLineNumber() ).append( ")" );
        output.append( "\n" );
        return output.toString();
    }
}
