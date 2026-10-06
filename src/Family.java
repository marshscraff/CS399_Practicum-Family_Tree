import java.util.ArrayList;
import java.util.List;

public class Family {
    private static int nextFamId=1;
    private int famId;
    private Person parent1;
    private Person parent2;
    private List<Person> children=new ArrayList<>();
    private static List<Family> allFamilies =new ArrayList<>();

 /**
	 * Creates a new family object
	 * 
	 * @param p1 parent1 of the family
	 * @param p2 parent2 of the family
	 */   
public Family(Person p1, Person p2){
    this.famId=nextFamId++;
    if (p1!=null) {
        parent1=p1;
        p1.addSPouseFam(this);
    }
    if (p2!=null) {
        parent2=p2;
        p2.addSPouseFam(this);
    }
    
    addFamily(this);
}
/**
	 * Sets parent1 in the family object
	 * 
	 * @param p parent1 of the family
	 */
public void setParent1(Person p){
    parent1=p;
    p.addSPouseFam(this);
}
/**
	 * Sets parent2 in the family object
	 * 
	 * @param p parent2 of the family
	 */
public void setParent2(Person p){
    parent1=p;
    p.addSPouseFam(this);
}
/**
	 * Sets famId in the family object
	 * 
	 * @param id int family id
	 */
public void setID(int id){
    this.famId=id;
}
/**
	 * Adds a family to the List of all families in the tree
	 * 
	 * @param fam family being added
	 */
public void addFamily(Family fam){
    allFamilies.add(fam);
}
/**
	 * gets the list of all families in the tree
	 * 
	 * @return allFamilies
	 */
public static List<Family> getFamilies(){
    return allFamilies;
}
/**
	 * Adds a person to the children list of the family
	 * 
	 * @param kid Person to be added as a child
	 */
public void addChild(Person kid){
    children.add(kid);
    kid.addChildFam(this);
}
/**
	 * Gets parent1 of the family
	 * 
	 * @return parent1
	 */
public Person getParent1(){
    return parent1;
}
/**
	 * Gets parent2 of the family
	 * 
	 * @return parent2
	 */
public Person getParent2(){
    return parent2;
}
/**
	 * Gets List of children of the family
	 * 
	 * @return children
	 */
public List<Person> getChildren(){
    return children;
}
/**
	 * Gets List of children of the family as a string
	 * 
	 * @return nameString
	 */
public String getChildrenString(){
    String nameString="";
    for(Person child: getChildren()){
        nameString=child.getName()+", ";
    }
    return nameString;
}
/**
	 * Gets famId
	 * 
	 * @return famId
	 */
public int getFamID(){
    return famId;
}
}
