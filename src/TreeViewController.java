import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
public class TreeViewController {
    @FXML
    private Pane treePane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button main;
    @FXML
    private Button save;
    @FXML
    private Button add;
    @FXML
    private Button help;
    private Map<Person,Cords> coordinates;
    private Map<Family, Cords> famCoordinates;
 
    /**
	 * initializes
	 * 
	 * @throws IOException
	 */
public void initialize() throws IOException{
    main.setOnAction(e->{ViewSwitchController.loadView("Main.fxml");});
    
    TreeLayout layout =new TreeLayout();
    FamilyTree tree = ViewSwitchController.getTree();
    Person home=tree.getHome();
    if (home==null&&!tree.getAllPeople().isEmpty()) {
        home=tree.getAllPeople().get(0);
        tree.setHome(home);
    }
    save.setOnAction(e->{
        try {
            tree.save(tree.getTreeName());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    });
    add.setOnAction(e->{ViewSwitchController.loadView("addPerson.fxml");});
    scrollPane.setPannable(true);
    coordinates=layout.layout(tree.getHome());
    famCoordinates=layout.getFamilyCords();
    System.out.println(coordinates.size());//remove at some point was testing
   drawTree();
   Platform.runLater(()->{
    scrollPane.setHvalue(0.5);
    scrollPane.setVvalue(1.0);
   });
}
    public void drawTree() throws IOException{
        for(Person p : coordinates.keySet()){//remove at some point was testing
    Cords c = coordinates.get(p);
    System.out.println(p.getName() + " -> " + c.x + "," + c.y);
}
for(Family famm: famCoordinates.keySet()){
    Cords c = famCoordinates.get(famm);
    System.out.println(famm.getChildrenString());
    
    System.out.println(famm.getFamID()+"->"+c.x+","+c.y);
}
        

        for(Family fam: famCoordinates.keySet()){
            Cords c = famCoordinates.get(fam);
            double x=c.x;
            double y= c.y;
            Person p1 = fam.getParent1();
            Person p2= fam.getParent2();
            Cords c1=coordinates.get(p1);
            Cords c2=coordinates.get(p2);
            if (c1!=null&&c2!=null) {
                Line line= new Line(c2.x, c2.y, c1.x, c1.y);
                treePane.getChildren().add(line);
            }
            
            int count=0;
            for(Person child: fam.getChildren()){
                Cords cc=coordinates.get(child);
                Line line=new Line(cc.x, cc.y, cc.x, cc.y-150);
                treePane.getChildren().add(line);
                count++;
            }
            if (count>0) {
                Line line=new Line(x, y, x, y+150);
                treePane.getChildren().add(line);
                Line line1=new Line(x, y+150, x+(100*((count-1)/2)),y+150);
                treePane.getChildren().add(line1);
                Line line2=new Line(x, y+150, x-(100*((count-1)/2)),y+150);
                treePane.getChildren().add(line2);
            }
        }
        for(Person p: coordinates.keySet()){
            Cords c = coordinates.get(p);
            FXMLLoader loader=new FXMLLoader(getClass().getResource("PersonCard.fxml"));
            StackPane card=loader.load();
            PersonCard controller=loader.getController();
            controller.setPerson(p);
            treePane.getChildren().add(card);
            card.setLayoutX(c.x-80);
            card.setLayoutY(c.y-90);
            }
        
        }
    }

