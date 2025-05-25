package dev.bibbelventure.vitality;


import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * JavaFX component that visualizes health and stress values as stacked vertical bars.
 * The stress bar overlays the health bar, which overlays a dark background.
 * Numeric values are displayed near the top edge of each bar.
 *
 * @author raschke
 */
public class VitalityView
  extends Pane
  implements VitalityListener
{
    // constants
    private static final double BAR_WIDTH = 30;
    private static final double LABEL_OFFSET = 5;

    // bars
    private final Rectangle background;
    private final Rectangle healthBar;
    private final Rectangle stressBar;

    // label
    private final Label healthLabel;
    private final Label stressLabel;

    /**
     * Constructs the VitalityView and sets up visuals.
     */
    public VitalityView()
    {
        background = new Rectangle();
        background.setFill( Color.web( "#222" ) );

        healthBar = new Rectangle();
        healthBar.setFill( Color.rgb( 201, 104, 104 ) );

        stressBar = new Rectangle();
        stressBar.setFill( Color.rgb( 255, 244, 234 ) );

        Font font = Font.font( "Roboto", FontWeight.BLACK, 14 );
        healthLabel = new Label();
        healthLabel.setFont( font );
        stressLabel = new Label();
        stressLabel.setFont( font );

        getChildren().addAll( background, healthBar, stressBar, healthLabel, stressLabel );

        VitalityService.getInstance().addListener( this );
    }

    /**
     * Called when health value changes.
     *
     * @param newHealth - updated health value
     */
    @Override
    public void onHealthChanged( int newHealth )
    {
        updateBars();
    }

    /**
     * Called when stress value changes.
     *
     * @param newStress - updated stress value
     */
    @Override
    public void onStressChanged( int newStress )
    {
        updateBars();
    }

    /**
     * Called when game over condition is met.
     */
    @Override
    public void onGameOver()
    {
        // no-op for view
    }

    /**
     * Updates bar sizes and label positions based on current health and stress values.
     */
    private void updateBars()
    {
        double height = getHeight();
        double width = getWidth();
        double healthHeight = (VitalityService.getInstance().getHealth() / 100.0) * height;
        double stressHeight = (VitalityService.getInstance().getStress() / 100.0) * height;

        background.setX( width - BAR_WIDTH );
        background.setY( 0 );
        background.setWidth( BAR_WIDTH );
        background.setHeight( height );

        healthBar.setX( width - BAR_WIDTH );
        healthBar.setY( height - healthHeight );
        healthBar.setWidth( BAR_WIDTH );
        healthBar.setHeight( healthHeight );

        stressBar.setX( width - BAR_WIDTH );
        stressBar.setY( height - stressHeight );
        stressBar.setWidth( BAR_WIDTH );
        stressBar.setHeight( stressHeight );

        // text content
        healthLabel.setText( String.valueOf( VitalityService.getInstance().getHealth() ) );
        stressLabel.setText( String.valueOf( VitalityService.getInstance().getStress() ) );

        // positioning
        healthLabel.setLayoutX( healthBar.getX() + healthBar.getWidth() / 2 - healthLabel.getWidth() / 2 );
        healthLabel.setLayoutY( healthBar.getY() + LABEL_OFFSET );
        stressLabel.setLayoutX( stressBar.getX() + stressBar.getWidth() / 2 - stressLabel.getWidth() / 2 );
        stressLabel.setLayoutY( stressBar.getY() + LABEL_OFFSET );
    }

    /**
     * Ensures bars resize correctly when parent is resized.
     */
    @Override
    protected void layoutChildren()
    {
        super.layoutChildren();
        updateBars();
    }
}
