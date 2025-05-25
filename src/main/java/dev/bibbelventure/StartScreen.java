package dev.bibbelventure;

import dev.bibbelventure.service.ChoiceService;
import dev.bibbelventure.service.LayoutService;
import dev.bibbelventure.visual.content.ContentService;
import dev.bibbelventure.vitality.VitalityService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartScreen
  extends StackPane
{

    public StartScreen()
    {
        // Left panel
        VBox leftPanel = new VBox( 10 );
        leftPanel.setPadding( new Insets( 50 ) );
        leftPanel.setPrefWidth( 200 );
        leftPanel.setAlignment( Pos.TOP_LEFT );
        leftPanel.setBackground( new Background( new BackgroundFill( Color.rgb( 33, 33, 33, 0.8 ), CornerRadii.EMPTY, Insets.EMPTY ) ) );

        // Buttons
        Button startButton = createButton( "START" );
        Button fullscreenButton = createButton( "FULLSCREEN" );
        Button closeButton = createButton( "CLOSE" );

        startButton.setOnAction( e -> startGame() );
        fullscreenButton.setOnAction( e -> LayoutService.getInstance().toggleFullscreen() );
        closeButton.setOnAction( e -> System.exit( 0 ) );

        leftPanel.getChildren().addAll( startButton, fullscreenButton, closeButton );
        getChildren().add( leftPanel );
    }

    private Button createButton( String text )
    {
        Button button = new Button( text );
        button.setFont( Font.font( "Segoe UI", 16 ) );
        button.setTextFill( Color.WHITE );
        button.setAlignment( Pos.CENTER_LEFT );
        button.setMaxWidth( Double.MAX_VALUE );

        // Default button style
        button.setStyle(
          "-fx-background-color: transparent;" +
            "-fx-background-insets: 0;" +
            "-fx-padding: 0;" +
            "-fx-border-radius: 0;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 16;" +
            "-fx-text-fill: white;" // Ensuring text is white by default
        );
        // Disable any state changes by overriding default mouse events
        button.setOnMouseEntered( e -> {} );   // No action on hover
        button.setOnMouseExited( e -> {} );    // No action on mouse exit
        button.setOnMousePressed( e -> {} );   // No action on press
        button.setOnMouseReleased( e -> {} );  // No action on release

        button.setFocusTraversable( false );
        return button;
    }

    private void startGame()
    {
        ContentService.getInstance().clear();
        ContentService.getInstance().addPane( VitalityService.getInstance().getView() );
        ChoiceService.getInstance().addFirstCard();
    }
}
