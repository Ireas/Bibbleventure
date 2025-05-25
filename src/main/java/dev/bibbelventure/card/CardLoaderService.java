package dev.bibbelventure.card;

import dev.bibbelventure.utility.drawio.DrawioNode;
import dev.bibbelventure.utility.drawio.DrawioParser;
import dev.bibbelventure.utility.exception.InternalServerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Loads, creates and links all Card objects from the hardcode set of .xml files.
 *
 * @author raschke
 */
public class CardLoaderService
{
    // singleton instance
    private static CardLoaderService instance;
    // source .xml files
    private final List<String> FILE_PATHS = List.of(
      "test.drawio"
    );
    // regex for splitting text in cells into title and description
    private final Pattern pattern = Pattern.compile( "^(.*?)<div>(.*)$", Pattern.DOTALL );

    /**
     * Fetch all card definitions from the predefined file paths.
     *
     * @return map with all cards, ids as keys and {@link Card} data objects as values
     */
    public Map<String, Card> fetchAllCards()
    {
        Map<String, Card> cardMap = new HashMap<>();
        for ( String filePath : FILE_PATHS )
        {
            DrawioParser parser = new DrawioParser( filePath );
            try
            {
                parser.parse();
            }
            catch ( InternalServerException e )
            {
                System.out.println( e.getMessage() );
                continue;
            }
            parser.getNodes().values().stream()
              .map( this::convertDrawioNodeToCard )
              .forEach( card -> cardMap.put( card.getId(), card ) );
        }
        return cardMap;
    }

    private Card convertDrawioNodeToCard( DrawioNode node )
    {
        return new Card(
          node.getId(),
          node.getMetadata().getOrDefault( "title", "" ),
          node.getMetadata().getOrDefault( "description", "" ),
          node.getConnectedNodeIds(),
          node.getMetadata()
        );
    }

    /**
     * Get static singleton instance or create it, if it is not instantiated.
     *
     * @return singleton instance
     */
    public static CardLoaderService getInstance()
    {
        if ( instance == null )
        {
            instance = new CardLoaderService();
        }
        return instance;
    }
}
