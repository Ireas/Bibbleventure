package dev.bibbelventure.visual.background;


import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * TODO
 *
 * @author raschke
 */
public class BackgroundPane
  extends Pane
{
    private final ImageView backgroundImageView;

    public BackgroundPane( Region parent )
    {
        backgroundImageView = new ImageView();
        backgroundImageView.setPreserveRatio( false );
        backgroundImageView.setSmooth( true );
        backgroundImageView.setEffect(new GaussianBlur(6)); // Adjust radius as needed

        backgroundImageView.fitWidthProperty().bind( widthProperty() );
        backgroundImageView.fitHeightProperty().bind( heightProperty() );

        getChildren().add( backgroundImageView );

        prefWidthProperty().bind( parent.widthProperty() );
        prefHeightProperty().bind( parent.heightProperty() );
    }

    public void setBackgroundImage( String resourcePath )
    {
        try
        {
            Image image = new Image( getClass().getResource( resourcePath ).toExternalForm() );
            backgroundImageView.setImage( image );
        }
        catch ( Exception e )
        {
            System.err.println( "Failed to load background image: " + resourcePath );
            e.printStackTrace();
        }
    }
}
