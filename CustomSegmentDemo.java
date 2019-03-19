//package lightware.richtext;
import javafx.application.Platform;
import org.fxmisc.flowless.VirtualizedScrollPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.fxmisc.richtext.model.ReadOnlyStyledDocument;
import org.fxmisc.richtext.model.ReadOnlyStyledDocumentBuilder;
import org.fxmisc.richtext.model.StyledSegment;
import org.fxmisc.richtext.model.TextOps;

import java.util.ArrayList;
import java.util.List;


/**
 * This demo shows how to register custom objects with the RichTextFX editor.
 * It creates a sample document with some text and a custom node.
 */
public class CustomSegmentDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//    	Function<AbstractSegment,Node>  nodeFactory = seg -> seg.createNode();

        StyledSegmentTextArea textArea = new StyledSegmentTextArea();

        // flip this boolean to execute one or the other
        if (false) {

            textArea.replaceText(0, 0, "");

            // all in one paragraph

            textArea.appendText("text ");

            textArea.appendText("hyperlink ");
            //textArea.append( new LabelSegment("label") );
            textArea.append( new HyperlinkSegment("hyperlink1", "c:\\temp\\abc.html") );
            textArea.append(new TextSegment(" end "));

            textArea.appendText("hyperlink ");
            textArea.append( new HyperlinkSegment("hyperlink2", "c:\\temp\\abc.html") );
            textArea.appendText(" end ");

            textArea.appendText("image ");
            textArea.append( new ImageSegment("picture.jpg") );
            textArea.appendText(" end ");

            textArea.appendText("hyperlink ");
            textArea.append( new HyperlinkSegment("hyperlink3", "c:\\temp\\abc.html") );
            textArea.appendText(" end ");
        }

        else {

            final TextOps<AbstractSegment, String> segmentOps = new MySegmentOps();
            ReadOnlyStyledDocumentBuilder<String, AbstractSegment, String> builder =
                    new ReadOnlyStyledDocumentBuilder<>(segmentOps, "");
            ReadOnlyStyledDocument<String, AbstractSegment, String> doc = null;


            AbstractSegment segment = null;
            String style = null;
            StyledSegment<AbstractSegment, String> styledSegment = null;
            List<StyledSegment<AbstractSegment, String>> styledSegments = new ArrayList<>();


            // paragraphs

            segment = new TextSegment("text paragraph");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            builder.addParagraph(styledSegments);
            styledSegments = new ArrayList<>();


            segment = new TextSegment("hyperlink paragraph");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            segment = new HyperlinkSegment("hyperlink1", "c:\\temp\\abc.html");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            segment = new TextSegment("end");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            builder.addParagraph(styledSegments);
            styledSegments = new ArrayList<>();


            segment = new TextSegment("hyperlink paragraph");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            // this hyperlink is corrupted:
            //  - mouse-hovering over it produces lots of calls to createNode (see stdout)
            //  - hovering does not cause the link to become underlined
            //  - clicking the link does not execute its handler

                    segment = new HyperlinkSegment("hyperlink2", "c:\\temp\\abc.html");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            segment = new TextSegment("end");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            builder.addParagraph(styledSegments);
            styledSegments = new ArrayList<>();


            segment = new TextSegment("image paragraph");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            segment = new ImageSegment("picture.jpg");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            segment = new TextSegment("end");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            builder.addParagraph(styledSegments);
            styledSegments = new ArrayList<>();


            segment = new TextSegment("hyperlink paragraph");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            segment = new HyperlinkSegment("hyperlink3", "c:\\temp\\abc.html");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            segment = new TextSegment("end");
            styledSegment = new StyledSegment<>(segment, "");
            styledSegments.add(styledSegment);

            builder.addParagraph(styledSegments);
            styledSegments = new ArrayList<>();


            doc = builder.build();
            textArea.replaceSelection(doc);
        }



        Scene scene = new Scene(new StackPane(new VirtualizedScrollPane<>(textArea)), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Custom Object demo");
        primaryStage.show();

    }
}
