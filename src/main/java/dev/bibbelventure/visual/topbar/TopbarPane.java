package dev.bibbelventure.visual.topbar;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * TODO
 *
 * @author raschke
 */
public class TopbarPane
  extends StackPane
{
    public TopbarPane( Region parent )
    {
        prefWidthProperty().bind( parent.widthProperty() );
        setPrefHeight( 60 );
        setMaxHeight(60);
        setMinHeight(60);

        setBackground( new Background( new BackgroundFill(
          Color.rgb( 0, 0, 0, 0.8 ),
          null,
          null
        ) ) );
    }
}