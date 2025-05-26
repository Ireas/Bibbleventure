package dev.bibbelventure.game.pearl;

/**
 * Pearl currency service.
 *
 * @author raschke
 */
public class PearlService
{
    // singleton instance
    private static PearlService instance;
    // instances
    private final PearlData data;
    private final PearlView view;

    /**
     * Constructor.
     */
    private PearlService()
    {
        data = new PearlData();
        view = new PearlView( data );
    }

    /**
     * Checks whether current pearls are sufficient.
     *
     * @param threshold - threshold for sufficiency
     * @return true if pearl value is at least threshold, otherwise false
     */
    public boolean sufficientPearls( int threshold )
    {
        return threshold <= data.getValue();
    }

    /**
     * Adds (or removes) pearl currency.
     *
     * @param value - value to be added, can be negative for reduction
     */
    public void addPearls( int value )
    {
        data.add( value );
        view.update();
    }

    /**
     * Fetch singleton instance
     *
     * @return singleton instance
     */
    public static PearlService getInstance()
    {
        if ( instance == null )
        {
            instance = new PearlService();
        }
        return instance;
    }

    public PearlData getData()
    {
        return data;
    }

    public PearlView getView()
    {
        return view;
    }
}
