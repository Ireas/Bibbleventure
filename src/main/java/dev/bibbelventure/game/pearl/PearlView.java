package dev.bibbelventure.game.pearl;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.Objects;

/**
 * Pearl representation.
 *
 * @author raschke
 */
public class PearlView
  extends HBox
{
    // icon
    private static final String ICON_PATH = "/pearl.png";
    // source data
    private final PearlData data;

    // components
    private Label label;
    private ImageView iconView;
    private Region spaceBefore;
    private Region spaceAfter;

    /**
     * Constructor
     *
     * @param data - source data
     */
    public PearlView( PearlData data )
    {
        // set source data
        this.data = data;

        // create components
        initializeLabel();
        initializeIcon();
        initializeSpace();
        getChildren().addAll( spaceBefore, label, iconView, spaceAfter );

        // layout adjustments
        this.setAlignment( Pos.CENTER );
        this.setSpacing( 5 );

        // update label once initially
        update();
    }

    /**
     * Updates the UI element, must be called after each data update.
     */
    public void update()
    {
        label.setText( data.getValue() + "" );
    }

    private void initializeLabel()
    {
        label = new Label();
        label.setMinHeight( 40 );
        label.setPrefHeight( 40 );
        label.setMaxHeight( 40 );
        label.setStyle( "-fx-text-fill: white; -fx-font-size:18; -fx-font-weight: bold;" );
    }

    private void initializeIcon()
    {
        Image icon = new Image( Objects.requireNonNull( getClass().getResourceAsStream( ICON_PATH ) ) );
        iconView = new ImageView( icon );
        iconView.setFitHeight( 15 );
        iconView.setPreserveRatio( true );
        iconView.setSmooth( true );
    }

    private void initializeSpace()
    {
        spaceBefore = new Region();
        HBox.setHgrow( spaceBefore, Priority.ALWAYS );

        spaceAfter = new Region();
        spaceAfter.setMinWidth( 50 );
        spaceAfter.setPrefWidth( 50 );
        spaceAfter.setMaxWidth( 50 );
    }
}