package dev.bibbelventure.service;

import dev.bibbelventure.card.Card;
import dev.bibbelventure.card.CardView;
import dev.bibbelventure.visual.content.ContentService;
import dev.bibbelventure.vitality.VitalityService;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author raschke
 */
public class ChoiceService
{
    private static ChoiceService instance;

    private HBox frame;

    public static ChoiceService getInstance()
    {
        if ( instance == null )
        {
            instance = new ChoiceService();
        }

        return instance;
    }

    public static void selectCard( Card card )
    {
        List<CardView> cardViews = card.getNextCardIds().stream()
          .map( CardView::new )
          .toList();

        instance.frame.getChildren().setAll( cardViews );
    }

    public void addFirstCard()
    {
        CardView cardView = new CardView( "YlOm4VDiYgaY15W4KOD3-2" );

        frame = new HBox( 0 );
        frame.setAlignment( Pos.CENTER );
        frame.setSpacing( 20 );
        frame.getChildren().addAll( cardView );

//        LayoutService.getInstance().loadAndSetBackgroundImage( "/s01.png" );
        ContentService.getInstance().addPane( frame );
    }
}
