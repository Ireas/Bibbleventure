package dev.bibbelventure.vitality;


/**
 * Interface for listening to vitality events.
 * Implement this to receive health, stress, and game over notifications.
 *
 * @author raschke
 */
public interface VitalityListener
{

    /**
     * Called when health value changes.
     *
     * @param newHealth - updated health value
     */
    void onHealthChanged( int newHealth );

    /**
     * Called when stress value changes.
     *
     * @param newStress - updated stress value
     */
    void onStressChanged( int newStress );

    /**
     * Called when game over condition is met (stress >= health).
     */
    void onGameOver();
}

