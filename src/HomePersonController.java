import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class HomePersonController {
    
    @FXML
    private Button help;
    @FXML
    private Button addPic;
    @FXML
    private Button next;
    @FXML
    private Label homePerson;
    @FXML
    private TextField birth;
    @FXML
    private ImageView profilePic;
    @FXML
    private ImageView pol;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private ChoiceBox<String> gender;
    private Person home;
/**
	 *initializes
	 */
    public void initialize(){
        gender.getItems().addAll("Woman","Man","Other");
        addPic.setOnAction(e->{
            FileChooser chooser =new FileChooser();
            chooser.setTitle("Select Profile Picture");
            chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.png","*.jpg","*.jpeg")
            );
            File file = chooser.showOpenDialog(addPic.getScene().getWindow());
            if(file!=null){
                home.setProfileImage(file.getAbsolutePath());
            }
        });
        next.setOnAction(e->{
            FamilyTree tree = new FamilyTree();
            ViewSwitchController.setTree(tree);
            String nameStr =firstName.getText();
            String lastNameStr = lastName.getText();
            String birthStr = birth.getText();
            String genderStr =gender.getValue(); 
            if ((nameStr==null||nameStr.isEmpty())&&(lastNameStr==""||lastNameStr.isEmpty())) {
                return;//add pop up saying you need at least a name for home person
            }
            if (genderStr==null) {
                genderStr="Other";
            }
            home = new Person();
            home.setName(nameStr,lastNameStr);
            home.setBirth(birthStr);
            home.setGender(genderStr);
            tree.setHome(home);
            tree.addPersonToTree(home);
            
            ViewSwitchController.loadView("HomeParents.fxml");});
        
    }
}
