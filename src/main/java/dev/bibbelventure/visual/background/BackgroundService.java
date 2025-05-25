package dev.bibbelventure.visual.background;


import javafx.scene.layout.Pane;

/**
 * TODO
 *
 * @author raschke
 */
public class BackgroundService
  extends Pane
{
    // singleton
    private static BackgroundService instance;
    private BackgroundPane backgroundPane;

    /**
     * Get static singleton instance or create it, if it is not instantiated.
     *
     * @return singleton instance
     */
    public static BackgroundService getInstance()
    {
        if ( instance == null )
        {
            instance = new BackgroundService();
        }
        return instance;
    }

    public BackgroundPane createBackgroundPane( Pane parent )
    {
        backgroundPane = new BackgroundPane( parent );
        return backgroundPane;
    }

    public void setBackground( String filePath )
    {
        backgroundPane.setBackgroundImage( filePath );
    }
}
