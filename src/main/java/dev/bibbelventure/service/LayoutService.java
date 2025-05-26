package dev.bibbelventure.service;

import dev.bibbelventure.StartScreen;
import dev.bibbelventure.visual.background.BackgroundPane;
import dev.bibbelventure.visual.background.BackgroundService;
import dev.bibbelventure.visual.content.ContentPane;
import dev.bibbelventure.visual.content.ContentService;
import dev.bibbelventure.visual.topbar.TopbarPane;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * TODO
 *
 * @author raschke
 */
public class LayoutService
{
    // singleton
    private static LayoutService instance;
    // main window stage and pane
    private Stage mainStage;
    // panes
    private BorderPane mainPane;
    private TopbarPane topbarPane;
    private ContentPane contentPane;

    /**
     * Get static singleton instance or create it, if it is not instantiated.
     *
     * @return singleton instance
     */
    public static LayoutService getInstance()
    {
        if ( instance == null )
        {
            instance = new LayoutService();
        }
        return instance;
    }

    public void initialize( Stage stage )
    {
        // initialize main stage
        mainStage = stage;
        mainStage.setTitle( "Bibbleventure" ); // set window title
        mainStage.setFullScreenExitKeyCombination( KeyCombination.NO_MATCH ); // disable fullscreen message

        // initialize main pane
        mainPane = new BorderPane();

        // set background
        BackgroundPane backgroundPane = BackgroundService.getInstance().createBackgroundPane( mainPane );
        BackgroundService.getInstance().setBackground( "/background.png" );
        mainPane.getChildren().add( backgroundPane );

        // set content
        contentPane = ContentService.getInstance().createContentPane( mainPane );
        ContentService.getInstance().addPane( new StartScreen() );
        mainPane.setCenter( contentPane );

        topbarPane = new TopbarPane( mainPane );
        mainPane.setTop( topbarPane );

        // create scene from pane
        Scene scene = new Scene( mainPane, 1400, 800 );
        mainStage.setScene( scene );
        mainStage.show();
    }

    public void toggleFullscreen()
    {
        mainStage.setFullScreen( !mainStage.isFullScreen() );
    }

    public void addToTopbar( Pane pane )
    {
        topbarPane.getChildren().add( pane );
    }
}
