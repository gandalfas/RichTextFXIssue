//package lightware.richtext;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class LabelSegment extends AbstractSegment
{
	private final String label;

	public LabelSegment( String label )
	{
		//super( data );
		this.label = label;
	}

	@Override
	public Node createNode( String style )
	{
    	Label  item = new Label( label );
    	item.setStyle( "-fx-border-width: 1; -fx-border-style: solid; -fx-border-color: lightgrey; -fx-padding: 0 2 0 2; -fx-font-weight: normal; -fx-font-size: 10px;" );
		if ( style != null &&  ! style.isEmpty() ) item.getStyleClass().add( style );
		item.setUserData( label );
    	return item;
	}
}
