package mandala;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Priority;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Represents a MandalaApp.
 */
public class MandalaApp extends Application {
    VBox vbox;
    Scene scene;
    HBox hbox1;
    HBox hbox2;
    HBox hbox3;
    ImageView imgView;
    Text text1;
    Text text2;
    Text text3;
    ChoiceBox<String> color1;
    ChoiceBox<String> color2;
    ChoiceBox<String> color3;
    ChoiceBox<String> style1;
    ChoiceBox<String> style2;
    ChoiceBox<String> style3;

    /**
     * Constructs a MandalaApp.
     */
    public MandalaApp() {
        vbox = new VBox();
        hbox1 = new HBox(10);
        hbox2 = new HBox(10);
        hbox3 = new HBox(10);
        imgView = new ImageView();
        text1 = new Text("Layer 1");
        text2 = new Text("Layer 2");
        text3 = new Text("Layer 3");
        color1 = new ChoiceBox<String>();
        color2 = new ChoiceBox<String>();
        color3 = new ChoiceBox<String>();
        style1 = new ChoiceBox<String>();
        style2 = new ChoiceBox<String>();
        style3 = new ChoiceBox<String>();
    } // MandalaApp

    @Override
    public void init() {
        color1.getItems().addAll("Red", "Orange", "Yellow", "Green", "Blue", "Purple");
        color2.getItems().addAll("Red", "Orange", "Yellow", "Green", "Blue", "Purple");
        color3.getItems().addAll("Red", "Orange", "Yellow", "Green", "Blue", "Purple");
        style1.getItems().addAll("Wild Iris", "Snowdrop");
        style2.getItems().addAll("Wild Iris", "Snowdrop");
        style3.getItems().addAll("Wild Iris", "Snowdrop");
        hbox1.getChildren().addAll(text1, color1, style1);
        hbox2.getChildren().addAll(text2, color2, style2);
        hbox3.getChildren().addAll(text3, color3, style3);
        vbox.getChildren().addAll(hbox1, hbox2, hbox3, imgView);
    } // init

    @Override
    public void start(Stage stage) {
        // Adds initial values to the dropdown menus
        color1.setValue("Red");
        color2.setValue("Red");
        color3.setValue("Red");
        style1.setValue("Wild Iris");
        style2.setValue("Wild Iris");
        style3.setValue("Wild Iris");

        // Creates the initial mandala
        updateMandala();

        // Sets event handlers when an option is selected from a dropdown menu
        color1.setOnAction(e -> updateMandala());
        color2.setOnAction(e -> updateMandala());
        color3.setOnAction(e -> updateMandala());
        style1.setOnAction(e -> updateMandala());
        style2.setOnAction(e -> updateMandala());
        style3.setOnAction(e -> updateMandala());

        // Creates and adds the scene to the stage
        scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Mandala App!");
        stage.sizeToScene();
        stage.show();
    } // main

    /**
     * Creates a new image mandala based on dropdown options and update image view.
     */
    private void updateMandala() {
        try {
            // Loads local image files
            BufferedImage l1 = ImageIO.read(new File("layers", color1.getValue() + " 1.png"));
            BufferedImage l2 = ImageIO.read(new File("layers", color2.getValue() + " 2.png"));
            BufferedImage l3 = ImageIO.read(new File("layers", color3.getValue() + " 3.png"));
            BufferedImage s1 = ImageIO.read(new File("layers", style1.getValue() + " 1.png"));
            BufferedImage s2 = ImageIO.read(new File("layers", style2.getValue() + " 2.png"));
            BufferedImage s3 = ImageIO.read(new File("layers", style3.getValue() + " 3.png"));
            BufferedImage outline = ImageIO.read(new File("layers", "outline.png"));

            BufferedImage base = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = base.createGraphics();

            // Combines layers of the mandala on top of each other
            g2.drawImage(l1, 0, 0, null);
            g2.drawImage(l2, 0, 0, null);
            g2.drawImage(l3, 0, 0, null);
            g2.drawImage(s1, 0, 0, null);
            g2.drawImage(s2, 0, 0, null);
            g2.drawImage(s3, 0, 0, null);
            g2.drawImage(outline, 0, 0, null);
            g2.dispose();

            // Write the final image to an output stream
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(base, "png", bos);

            // Passes image in output stream to the image view
            InputStream is = new ByteArrayInputStream(bos.toByteArray());
            imgView.setImage(new Image(is));
        } catch (Exception e) {
            System.err.println(e.toString());
        } // try
    } // updateMandala
} // MandalaApp
