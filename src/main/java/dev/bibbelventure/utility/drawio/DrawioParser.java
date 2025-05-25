package dev.bibbelventure.utility.drawio;

import dev.bibbelventure.utility.exception.InternalServerException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Parser for .drawio files.
 * Extracts each custom object node and its metadata. Assumes each node to contain a id, title, label.
 *
 * @author raschke
 */
public class DrawioParser
{
    // target .drawio file
    private final String filePath;

    // map of all extracted nodes
    private final Map<String, DrawioNode> nodes;

    /**
     * Constructor, takes a ressource path to Drawio file to parse.
     *
     * @param filePath path to ressource .drawio file
     * @throws RuntimeException if resource file not found or parsing fails
     */
    public DrawioParser( String filePath )
    {
        this.filePath = filePath;
        nodes = new HashMap<>();
    }

    /**
     * Parses the .drawio file and extracts all nodes.
     *
     * @return map with all nodes, key=unique id and value={@link DrawioNode} data bean
     * @throws InternalServerException if parsing fails
     */
    public Map<String, DrawioNode> parse() throws InternalServerException
    {
        // access file as input stream
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream( filePath );
        if ( inputStream == null )
        {
            throw new InternalServerException( "Resource not found: " + filePath );
        }

        // parse .drawio file to fill nodes
        try
        {
            extractNodesFromFile( inputStream );
        }
        catch ( IOException | SAXException | ParserConfigurationException e )
        {
            String msg = "Could not parse the following file: " + filePath;
            throw new InternalError( msg, e );
        }

        return nodes;
    }

    private void extractNodesFromFile( InputStream fileStream ) throws IOException, SAXException, ParserConfigurationException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse( fileStream );
        doc.getDocumentElement().normalize();

        // Step 1: Extract all <object> nodes (with embedded <mxCell>)
        NodeList objectNodes = doc.getElementsByTagName( "object" );
        for ( int i = 0; i < objectNodes.getLength(); i++ )
        {
            Element objElem = (Element) objectNodes.item( i );
            DrawioNode node = parseElementToNode( objElem );
            nodes.put( node.getId(), node );
        }

        // Step 2: Extract edges and build connections
        NodeList mxCells = doc.getElementsByTagName( "mxCell" );
        for ( int i = 0; i < mxCells.getLength(); i++ )
        {
            Element cellElem = (Element) mxCells.item( i );
            if ( "1".equals( cellElem.getAttribute( "edge" ) ) )
            {
                String sourceId = cellElem.getAttribute( "source" );
                String targetId = cellElem.getAttribute( "target" );

                DrawioNode sourceNode = nodes.get( sourceId );
                if ( sourceNode != null )
                {
                    sourceNode.getConnectedNodeIds().add( targetId );
                }
            }
        }
    }

    /**
     * Parses a .drawio object element into a {@link DrawioNode} data bean.
     *
     * @param element - .drawio object element
     * @return node data bean, including all attributes as metadata
     */
    private DrawioNode parseElementToNode( Element element )
    {
        // create node with unique id
        String id = element.getAttribute( "id" );
        DrawioNode node = new DrawioNode( id );

        // add optional attributes as metadata
        NamedNodeMap attributes = element.getAttributes();
        for ( int i = 0; i < attributes.getLength(); i++ )
        {
            Node attribute = attributes.item( i );
            String attributeName = attribute.getNodeName();

            // do not add id as metadata
            if ( attributeName.equals( "id" ) )
            {
                continue;
            }

            node.addMetadata( attributeName, attribute.getNodeValue() );
        }

        return node;
    }

    /**
     * Get map of all parsed nodes.
     *
     * @return map with all nodes, key=unique id and value={@link DrawioNode} data bean
     */
    public Map<String, DrawioNode> getNodes()
    {
        return nodes;
    }

    /**
     * Fetches specific Drawio node.
     *
     * @param id - unqieu Drawio object id
     * @return Node if id exists, otherwise null
     */
    public DrawioNode getNode( String id )
    {
        return nodes.get( id );
    }
}
