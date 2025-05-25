package dev.bibbelventure.card;

import java.util.*;

/**
 * Card data bean.
 *
 * @author raschke
 */
public class Card
{
    private final String id;
    private final String title;
    private final String description;
    private final Set<String> nextCardIds;
    private final Map<String, String> metadata;

    /**
     * Constructor.
     * @param id - unique Drawio id
     * @param title - card title
     * @param description card description
     * @param nextCardIds - set of unique successor card ids
     * @param metadata - optional map of metadata
     */
    public Card( String id, String title, String description, Set<String> nextCardIds, Map<String, String> metadata )
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nextCardIds = nextCardIds;
        this.metadata = metadata;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public Set<String> getNextCardIds()
    {
        return nextCardIds;
    }

    public Map<String, String> getMetadata()
    {
        return metadata;
    }
}
