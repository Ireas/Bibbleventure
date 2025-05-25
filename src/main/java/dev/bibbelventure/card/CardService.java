package dev.bibbelventure.card;


import java.util.HashMap;
import java.util.Map;

/**
 * Service that is used for accessing cards.
 *
 * @author raschke
 */
public class CardService
{
    // singleton instance
    private static CardService instance;
    // card cache
    private final static Map<String, Card> CARD_CACHE = new HashMap<>();

    /**
     * Constructor, fills card cache with all defined cards using {@link CardLoaderService}.
     */
    public CardService()
    {
        CARD_CACHE.putAll( CardLoaderService.getInstance().fetchAllCards() );
    }

    /**
     * Get {@link Card} from card cache using the card id as identifier.
     *
     * @param id - card id
     * @return Card object or null if id is not used.
     */
    public Card cardFromId( String id )
    {
        Card card = CARD_CACHE.get( id );

        if ( card == null )
        {
            throw new IllegalArgumentException( "No Card found for id: " + id );
        }

        return card;
    }

    /**
     * Get static singleton instance or create it, if it is not instantiated.
     *
     * @return singleton instance
     */
    public static CardService getInstance()
    {
        if ( instance == null )
        {
            instance = new CardService();
        }
        return instance;
    }
}
