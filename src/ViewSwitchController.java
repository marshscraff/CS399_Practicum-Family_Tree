
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
	 * switches view of gui
	 */
public class ViewSwitchController {
     private static FamilyTree currentTree;
  @FXML
    private StackPane root;
    private static StackPane sroot;

    @FXML
    /**
	 * initializes
	 */
    public void initialize() {
        sroot=root;
        loadView("Home.fxml");
    }
    public static void setTree(FamilyTree tree){
    currentTree = tree;
}
/**
	 * gets current tree
	 * 
	 * @return currentTree
	 */
public static FamilyTree getTree(){
    return currentTree;
}

private static Object currentController;
/**
	 * loads view
	 * 
	 * @param fxml file name
	 */
    public static void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                ViewSwitchController.class.getResource(fxml)
            );
            Parent view=loader.load();
            sroot.getChildren().setAll(view);
            currentController =loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
	 * gets current controller being used
	 * 
	 * @return currentController
	 */
    public static <T> T getCurrentController(){
        return (T) currentController;
    }   
}
