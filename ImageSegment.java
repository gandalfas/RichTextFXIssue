import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageSegment extends AbstractSegment {

    private final String imagePath;

    public ImageSegment(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public Node createNode(String style) {
        Image image = new Image("file:" + imagePath); // XXX: No need to create new Image objects each time -
        ImageView imageView = new ImageView(image);
        System.out.printf("createNode/ImageSegment:  imagePath: '%s'\n", imagePath);
        return imageView;
    }
}
