package dev.bibbelventure.visual.content;


import dev.bibbelventure.visual.background.BackgroundService;
import javafx.scene.layout.Pane;

/**
 * TODO
 *
 * @author raschke
 */
public class ContentService
{
    private static ContentService instance;
    private ContentPane contentPane;

    /**
     * Get static singleton instance or create it, if it is not instantiated.
     *
     * @return singleton instance
     */
    public static ContentService getInstance()
    {
        if ( instance == null )
        {
            instance = new ContentService();
        }
        return instance;
    }

    public ContentPane createContentPane( Pane parent )
    {
        contentPane = new ContentPane( parent );
        return contentPane;
    }

    public void addPane( Pane pane )
    {
        contentPane.getChildren().add( pane );
    }

    public void clear(){
        contentPane.getChildren().clear();
    }
}
