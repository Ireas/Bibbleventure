package dev.bibbelventure.card;

import dev.bibbelventure.game.pearl.PearlService;
import dev.bibbelventure.service.ChoiceService;
import dev.bibbelventure.vitality.VitalityService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * TODO
 *
 * @author raschke
 */
public class CardView
  extends StackPane
{
    private Card source;

    public CardView( String cardId )
    {
        this( CardService.getInstance().cardFromId( cardId ) );
    }

    public CardView( Card source )
    {
        this.source = source;

        // Styling
        this.setStyle( "-fx-background-color: rgb(255, 244, 234, 0.9); -fx-background-radius: 10; -fx-border-radius: 10;" );
        this.setMinWidth( 250 );
        this.setMaxWidth( 250 );
        this.setMinHeight( 400 );
        this.setMaxHeight( 400 );

        // Create content components
        StackPane artworkContainer = new StackPane();
        artworkContainer.setPrefSize( 250, 280 ); // Desired tall cutout size
        artworkContainer.setMaxSize( 250, 280 );
        artworkContainer.setMinSize( 250, 280 );

        // ImageView with preserved aspect ratio
        Image artworkImage = new Image( "/background.png" );
        ImageView artwork = new ImageView( artworkImage );
        artwork.setPreserveRatio( true );
        artwork.setFitHeight( 280 ); // Set desired height to fill vertically

        // Clip to rounded rectangle for masking
        Rectangle clip = new Rectangle( 250, 280 );
        clip.setArcWidth( 10 );
        clip.setArcHeight( 10 );
        artworkContainer.setClip( clip );
        artworkContainer.getChildren().add( artwork );

        // Colored horizontal line (colored as per your choice)
        Region line = new Region();
        line.setStyle( "-fx-background-color: #4CAF50; -fx-pref-height: 10;" ); // Green color, adjust as needed
        line.setTranslateY( -5 );

        // White box containing title and description
        VBox whiteBox = new VBox( 10 ); // 10px spacing between elements
        whiteBox.setStyle( "-fx-background-color: white; -fx-padding: 10; -fx-background-radius: 10;" );

        // Title
        Label title = new Label( source.getTitle() );
        title.setFont( Font.font( "System", FontWeight.BOLD, 18 ) );
        title.setAlignment( Pos.CENTER );
        title.setWrapText( true );

        // Description
        Label description = new Label( source.getDescription() );
        description.setFont( Font.font( "System", FontWeight.NORMAL, 14 ) );
        description.setAlignment( Pos.CENTER );
        description.setWrapText( true );

        // Create main VBox with artwork, line, and white box
        VBox content = new VBox( artworkContainer, line, title, description );
        content.setAlignment( Pos.TOP_CENTER );

        // Add the content to the CardView
        this.getChildren().add( content );

        // Mouse click event handler
        this.setOnMouseClicked( ( MouseEvent event ) -> {
            System.out.println( "StackPane clicked at: (" + event.getSceneX() + ", " + event.getSceneY() + ")" );
            ChoiceService.selectCard( source );

            if ( source.getMetadata().containsKey( "health" ) )
            {
                VitalityService.getInstance().addHealth( Integer.parseInt( source.getMetadata().get( "health" ) ) );
            }
            if ( source.getMetadata().containsKey( "stress" ) )
            {
                VitalityService.getInstance().addStress( Integer.parseInt( source.getMetadata().get( "stress" ) ) );
            }
            if ( source.getMetadata().containsKey( "pearl" ) )
            {
                PearlService.getInstance().addPearls( Integer.parseInt( source.getMetadata().get( "pearl" ) ) );
            }
        } );
    }
}
