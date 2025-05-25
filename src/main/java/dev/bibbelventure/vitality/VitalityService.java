package dev.bibbelventure.vitality;


import java.util.ArrayList;
import java.util.List;

/**
 * Singleton service that manages player's health and stress levels.
 * Values are clamped between 0 and 100. Notifies listeners on changes
 * and emits a game over signal when stress is greater than or equal to health.
 *
 * @author raschke
 */
public class VitalityService
{
    // singleton
    private final static VitalityService instance = new VitalityService();

    // internal state
    private int health = 100;
    private int stress = 0;

    // listeners
    private final List<VitalityListener> listeners = new ArrayList<>();

    // view
    private VitalityView view;

    /**
     * Returns the singleton instance of VitalityService.
     *
     * @return singleton instance
     */
    public static VitalityService getInstance()
    {
        return instance;
    }

    /**
     * Adds a listener to receive vitality events.
     *
     * @param listener - listener to register
     */
    public void addListener( VitalityListener listener )
    {
        if ( !listeners.contains( listener ) )
        {
            listeners.add( listener );
        }
    }

    /**
     * Removes a listener from receiving vitality events.
     *
     * @param listener - listener to remove
     */
    public void removeListener( VitalityListener listener )
    {
        listeners.remove( listener );
    }

    /**
     * Returns the current health value.
     *
     * @return current health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets the health value (clamped between 0 and 100).
     * Notifies listeners and checks for game over condition.
     *
     * @param value - new health value
     */
    public void setHealth( int value )
    {
        int clamped = clamp( value );
        if ( clamped != health )
        {
            health = clamped;
            notifyHealthChanged( health );
            checkForGameOver();
        }
    }

    /**
     * Convenience method to increase or decrease current health.
     *
     * @param value - added value, can be negative for reducing health
     */
    public void addHealth( int value )
    {
        setHealth( getHealth() + value );
    }

    /**
     * Returns the current stress value.
     *
     * @return current stress
     */
    public int getStress()
    {
        return stress;
    }

    /**
     * Sets the stress value (clamped between 0 and 100).
     * Notifies listeners and checks for game over condition.
     *
     * @param value - new stress value
     */
    public void setStress( int value )
    {
        int clamped = clamp( value );
        if ( clamped != stress )
        {
            stress = clamped;
            notifyStressChanged( stress );
            checkForGameOver();
        }
    }

    /**
     * Convenience method to increase or decrease current stress.
     *
     * @param value - added value, can be negative for reducing stress
     */
    public void addStress( int value )
    {
        setStress( getStress() + value );
    }

    /**
     * Returns the JavaFX VitalityView component.
     *
     * @return vitality view instance
     */
    public VitalityView getView()
    {
        if ( view == null )
        {
            view = new VitalityView();
        }
        return view;
    }

    // clamps value to range 0-100
    private int clamp( int value )
    {
        return Math.max( 0, Math.min( 100, value ) );
    }

    // notifies listeners of health change
    private void notifyHealthChanged( int newHealth )
    {
        for ( VitalityListener listener : listeners )
        {
            listener.onHealthChanged( newHealth );
        }
    }

    // notifies listeners of stress change
    private void notifyStressChanged( int newStress )
    {
        for ( VitalityListener listener : listeners )
        {
            listener.onStressChanged( newStress );
        }
    }

    // checks if stress >= health and emits game over if true
    private void checkForGameOver()
    {
        if ( stress >= health )
        {
            notifyGameOver();
        }
    }

    // notifies listeners of game over event
    private void notifyGameOver()
    {
        for ( VitalityListener listener : listeners )
        {
            listener.onGameOver();
        }
    }
}
