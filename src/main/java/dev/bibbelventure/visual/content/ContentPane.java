package dev.bibbelventure.visual.content;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * TODO
 *
 * @author raschke
 */
public class ContentPane
  extends StackPane
{
    public ContentPane( Region parent )
    {
        prefWidthProperty().bind( parent.widthProperty() );
        prefHeightProperty().bind( parent.heightProperty() );
    }
}
