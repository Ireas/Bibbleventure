package dev.bibbelventure.game.pearl;

/**
 * Data bean for current pearls the player holds.
 *
 * @author raschke
 */
public class PearlData
{
    private int value = 0;

    public void add( int value )
    {
        this.value += value;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue( int value )
    {
        this.value = value;
    }
}
