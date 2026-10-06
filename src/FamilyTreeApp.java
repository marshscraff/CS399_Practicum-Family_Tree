
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class FamilyTreeApp extends Application{
 @Override
 /**
	 * Starts app
     * @param stage
     * @throws Exception
	 */
public void start(Stage stage) throws Exception {

    FXMLLoader loader = new FXMLLoader(
    getClass().getResource("/Main.fxml")
);

    Parent root = loader.load();

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
/**
	 * launches app
     * @param args
	 */public static void main(String[] args) {
        launch(args);
    }
}
