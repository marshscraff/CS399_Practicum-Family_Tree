import java.nio.Buffer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class indiPersonPageController {
  @FXML
  private ImageView proPic;
  @FXML
  private ImageView pol;
  @FXML
  private Label birth;
  @FXML
  private Label death;
  @FXML
  private Label name;
  @FXML
  private ListView events;
  @FXML
  private ListView parentsSiblings;
  @FXML
  private ListView spouseChildren;
  @FXML
  private TextArea notes;
  @FXML
  private Button addPic;
  @FXML
  private Button addEvent;
  @FXML
  private Button addParentSiblings;
  @FXML
  private Button addSpouseChild;
  @FXML
  private Menu home;
  @FXML
  private Menu save;
  @FXML
  private Menu tree;
  @FXML
  private Menu help;
  private Person person;
  
  /**
	 * sets person to be displayed on page
	 * 
	 * @param p person to be displayed
	 */
  public void setPerson(Person p){
    this.person =p;
    update();
  }

  /**
	 * Updates page
	 */
  public void update(){
    proPic.setImage(new Image(person.getProfileImage()));
    birth.setText(person.getBirth());
    death.setText(person.getDeath());
    name.setText(person.getName());
    ObservableList<String> eventList = FXCollections.observableArrayList();
    person.getEvents().forEach((date,type)-> eventList.add(date+": "+type));
    events.getItems().setAll(eventList);
    ObservableList<String> parentSibList = FXCollections.observableArrayList();
    for(Family fam: person.getChildFam()){
        if (fam.getParent1()!=null)parentSibList.add("Parent: "+fam.getParent1()); 
        if (fam.getParent2()!=null)parentSibList.add("Parent: "+fam.getParent2());
        for(Person sibling:fam.getChildren()){
            if (!sibling.equals(person))parentSibList.add("Sibling: "+sibling.getName());    
            }
        }
        parentsSiblings.getItems().setAll(parentSibList);
    ObservableList<String> spouseChildList =FXCollections.observableArrayList();
    for (Family fam: person.getSpouseFam()){
        if (fam.getParent1()!=null&&!fam.getParent1().equals(person))
            spouseChildList.add("Spouse: "+fam.getParent1().getName());
        if (fam.getParent2()!=null&&!fam.getParent2().equals(person))
            spouseChildList.add("Spouse: "+fam.getParent2().getName());
        for (Person child: fam.getChildren()){
            spouseChildList.add("Child: "+child.getName());
        }
         
    } spouseChildren.getItems().add(spouseChildList);  
            
        
    }
    

  }

