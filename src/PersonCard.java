import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PersonCard extends StackPane{
    private Person person;
    @FXML
    private ImageView pol;
    @FXML
    private ImageView proPic;
    @FXML
    private Label name;
    @FXML
    private StackPane root;

    /**
	 * sets person to be displayed on card
	 * 
	 * @param person to be displayed
	 */
    public void setPerson(Person person){
        this.person=person;
        name.setText(person.getName());
        String picPath = person.getProfileImage();
        if (picPath!=null&& !picPath.isEmpty()) {
            proPic.setImage(new Image("file:"+picPath));
        }else{
            proPic.setImage(new Image("file:src/pictures/defaultProPic.png"));
        }
    }
    @FXML
    /**
	 * actions that happen on click
	 */
    public void onClick(){
        ViewSwitchController.loadView("indiPersonPage.fxml");
        indiPersonPageController controller =ViewSwitchController.getCurrentController();
        controller.setPerson(person);
    }
}
