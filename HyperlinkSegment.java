import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;


public class HyperlinkSegment extends AbstractSegment {

    private final String caption;
    private final String url;
    private Hyperlink hyperlink;

    public HyperlinkSegment(String caption, String url) {
        this.caption = caption;
        this.url = url;
        hyperlink = null;
    }

    @Override
    public Node createNode( String style ) {
        hyperlink = new Hyperlink(caption);
        hyperlink.getStyleClass().add("text");
        hyperlink.setStyle(style);  //.toCss());

        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.printf("hyperlink was clicked - url: '%s', hyperlink: %s, visited: %s\n", url, hyperlink, hyperlink.isVisited());
            }
        });

        System.out.printf("createNode/HyperLinkSegment:  caption: '%s'\n", caption);
        return hyperlink;
    }
}
