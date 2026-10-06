
import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private VBox root;
    @FXML
    private Button newTree;
    @FXML
    private Button exit;
    @FXML
    private Button loadTree;
    @FXML
    private Button cBoard;
    @FXML
    private Button notes;
    @FXML
    private ImageView newTreeLeaf;
    @FXML
    private ImageView loadTreeLeaf;
    @FXML
    private ImageView exitLeaf;
    @FXML
    private ImageView notesLeaf;
    @FXML
    private ImageView cBoardLeaf;
    @FXML
    private ImageView titleTree;

    @FXML
    /**
	 * Initializes
	 * 
	 */
    public void initialize(){
        newTree.setOnAction(e->ViewSwitchController.loadView("homeperson.fxml"));
        loadTree.setOnAction(e->{
            FileChooser chooser= new FileChooser();
            chooser.setTitle("Load Family Tree");
            chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("GEDCOM Files", "*.ged")
            );
            File savedTrees = new File("SavedTrees");
            if (!savedTrees.exists()) {
                savedTrees.mkdirs();
            }
            chooser.setInitialDirectory(savedTrees);
            Stage stage = (Stage) root.getScene().getWindow();
            File file=chooser.showOpenDialog(stage);
            if (file!=null){
                try{
                FamilyTree.load(file);
                }catch(FileNotFoundException ex){
                    ex.printStackTrace();
                }ViewSwitchController.loadView("treeview.fxml");
            }

        });
        exit.setOnAction(e -> System.exit(0));
        cBoard.setOnAction(null);
        notes.setOnAction(null);
    
    }

}
