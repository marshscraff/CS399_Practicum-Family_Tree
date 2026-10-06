import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class HomeParentsController {
    @FXML
    private ImageView pol1;
    @FXML
    private ImageView pol2;
    @FXML
    private ImageView proPic1;
    @FXML
    private ImageView proPic2;
    @FXML
    private ChoiceBox<String> relationship;
    @FXML
    private ChoiceBox<String> gender1;
    @FXML
    private ChoiceBox<String> gender2;
    @FXML
    private TextField name1;
    @FXML
    private TextField name2;
    @FXML
    private TextField lastName1;
    @FXML
    private TextField lastName2;
    @FXML
    private TextField birth1;
    @FXML
    private TextField birth2;
    @FXML
    private Button help;
    @FXML
    private Button next;
  /**
	 * initializes
	 */  
  public void initialize(){
    gender1.getItems().addAll("Woman","Man","Other");
    gender2.getItems().addAll("Woman","Man","Other");
    

    next.setOnAction(e->{
        String firstName1 = name1.getText();
        String firstName2 =name2.getText();
        String lastName1Str = lastName1.getText();
        String lastName2Str = lastName2.getText();
        String birth1Str =birth1.getText();
        String birth2Str = birth2.getText();
        String gender1Str = gender1.getValue();
        String gender2Str = gender2.getValue();
            if (gender1Str==null) {
                gender1Str="Other";
            }
            if (gender2Str==null) {
                gender2Str="Other";
            }
        Person parent1 = new Person();
        Person parent2 = new Person();
        parent1.setName(firstName1, lastName1Str);
        parent2.setName(firstName2, lastName2Str);
        parent1.setBirth(birth1Str);
        parent2.setBirth(birth2Str);
        parent1.setGender(gender1Str);
        parent2.setGender(gender2Str);
        
        FamilyTree tree = ViewSwitchController.getTree();
        tree.getHome().addParent(parent1);
        tree.getHome().addParent(parent2);
        tree.addPersonToTree(parent2);
        tree.addPersonToTree(parent1);
    ViewSwitchController.loadView("TreeView.fxml");});
    }  
}
