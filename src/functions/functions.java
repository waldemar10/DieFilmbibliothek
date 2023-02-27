package functions;
/**
 *
 * @author Waldemar Justus
 *
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class functions {
    public static int count = 0;
    public static void createWindow(Stage stage, String title, ActionEvent event,Scene scene) throws IOException {

        stage.setResizable(false);
        String pfad = "/Image/LogoFilmbibliothek.png";
        Image image = new Image(pfad);
        stage.getIcons().add(image);
        title = "Die Filmbibliothek";
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        if(count >= 1){
            Node source =(Node) event.getSource();
            Stage oldStage = (Stage) source.getScene().getWindow();
            oldStage.close();
        }
        count++;
    }
    public static void createWindowMouse(Stage stage, String title, MouseEvent event, Scene scene) throws IOException {

        stage.setResizable(false);
        String pfad = "/Image/LogoFilmbibliothek.png";
        Image image = new Image(pfad);
        stage.getIcons().add(image);
        title = "Die Filmbibliothek";
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        if(count >= 1){
            Node source =(Node) event.getSource();
            Stage oldStage = (Stage) source.getScene().getWindow();
            oldStage.close();
        }
        count++;
    }
}
