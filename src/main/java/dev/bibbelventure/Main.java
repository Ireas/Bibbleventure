package dev.bibbelventure;

import dev.bibbelventure.service.LayoutService;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main
  extends Application
{
    /**
     * Main Java entry pints
     *
     * @param args - arguments from console start
     */
    public static void main( String[] args )
    {
        launch( args );
    }

    /**
     * Application start method, after initializing JavaFX.
     *
     * @param stage - main window
     */
    @Override
    public void start( Stage stage )
    {
        // initialize visual layout
        LayoutService.getInstance().initialize( stage );

        // set window icon
        Image appIcon = new Image( "/logo.png" ); // Or use a URL for remote resources
        stage.getIcons().add( appIcon );
    }
}
