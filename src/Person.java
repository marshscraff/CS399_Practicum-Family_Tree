
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Person {
    private static int nextId = 1;
    private int id;
    private String name;
    private String firstMiddle;
    private String surName;
    private String birth;
    private String death;
    private String gender;//some shit might be redundant w/ adding family ob
    private Set<Person> parents =new HashSet<>();//maybe want to change these to maps also layin the ground ork atp
    private Set<Person> spouse= new HashSet<>();
    private Set<Person> children= new HashSet<>();
    private Set<Family> childFamilies = new HashSet<>();
    private Set<Family> spouseFamilies = new HashSet<>();
    private Map<String,String> events = new TreeMap<>();
    FamilyTree tree = ViewSwitchController.getTree();
    private String profileImagePath;
/**
	 * Makes a new person object
	 */
public Person(){
    this.id=nextId++;
    name="";
    firstMiddle="";
    surName="";
    birth="";
    gender="";
    death="Living";
    if (tree!=null) {
        tree.addPersonToTree(this);
    }
    profileImagePath="src/pictures/defaultProPic.png";
}
/**
	 * Sets the path to the profile picture
	 * 
	 * @param path path to image
	 */
public void setProfileImage(String path){
    this.profileImagePath=path;
}
/**
	 * Gets path to profile picture
	 * 
	 * @return profileImagePath
	 */
public String getProfileImage(){
    if (profileImagePath==null) {
        return "pictures/defaultProPic.png";
    }
    return profileImagePath;
}
/**
	 * Set person ID
	 * 
	 * @param id person id
	 */
public void setID(int id){
    this.id=id;
}
/**
	 * Sets date of death
	 * 
	 * @param death date of death
	 */
public void addDeath(String death){
    this.death=death;
}
/**
	 * Gets date of death
	 * 
	 * @return death
	 */
public String getDeath(){
    return death;
}
/**
	 * Adds a Family to the list of childfamilys or familys where the person was the child
	 * 
	 * @param cfam family to be added to list
	 */
public void addChildFam(Family cfam){
    childFamilies.add(cfam);
}
/**
	 * Adds a Family to the list of spousefamilys or familys where the person was the spouse or parent
	 * 
	 * @param sfam family to be added to list
	 */
public void addSPouseFam(Family sfam){
    spouseFamilies.add(sfam);
}
/**
	 * gets the set of childfamilys or familys where the person was the child
	 * 
	 * @return childFamilies set of families
	 */
public Set<Family> getChildFam(){
    return childFamilies;
}
/**
	 * gets the set of spousefamilys or familys where the person was the spouse or parent
	 * 
	 * @return spouseFamilies set of families
	 */
public Set<Family> getSpouseFam(){
    return spouseFamilies;
}
/**
	 * gets persons id
	 * 
	 * @return id
	 */
public int getId(){
    return id;
}
/**
	 * sets the name of the person
	 * 
	 * @param fname first name
     * @param surn surname
	 */
public void setName(String fname,String surn){
    this.firstMiddle= fname;
    this.surName=surn;
    this.name=fname+" "+surn;
}
/**
	 * gets first name of person
	 * 
	 * @return firstMiddle
	 */
public String getFirstName(){
    return this.firstMiddle;
}
/**
	 * gets last name of person
	 * 
	 * @return surName
	 */
public String getSurname(){
    return this.surName;
}
/**
	 * gets full name of person
	 * 
	 * @return name
	 */
public String getName(){
    return this.name;
}
/**
	 * sets gender
	 * 
	 * @param genderStr
	 */
public void setGender(String genderStr){
    this.gender=genderStr;
}
/**
	 * gets gender
	 * 
	 * @return gender
	 */
public String getGender(){
    return this.gender;
}
/**
	 * sets date of birth
	 * 
	 * @param birth date of birth
	 */
public void setBirth(String birth){
    this.birth=birth;
}
/**
	 * gets date of birth
	 * 
	 * @return birth
	 */
public String getBirth(){
    return this.birth;
}
/**
	 * adds parent to parent list and sets person as a child to the parent
	 * 
	 * @param parent
	 */
public void addParent(Person parent){
    parents.add(parent);
    parent.children.add(this);
}
/**
	 * gets set of parents
	 * 
	 * @return parents
	 */
public Set<Person> getParents(){
    return parents;
}
/**
	 * gets list of parents as string
	 * 
	 * @return parentsStr
	 */
public String getParentsStr(){
    String parentsStr ="";
    for(Person p:parents){
        parentsStr += p.getName() +", ";
    }
    return parentsStr;
}
/**
	 * adds a Spouse
	 * 
	 * @param spouse
	 */
public void addSpouse(Person spouse){
    this.spouse.add(spouse);
}
/**
	 * gets spouse list as string
	 * 
	 * @return spouseStr
	 */
public String getSpouse(){
    String spouseStr ="";
     for(Person s:spouse){
        spouseStr += s.getName() +", ";
    }
    return spouseStr;
}
/**
	 * adds a child
	 * 
	 * @param child
	 */
public void addChild(Person child){
    this.children.add(child);
    child.parents.add(this);
}
/**
	 * gets set of children
	 * 
	 * @return children
	 */
public Set<Person> getChildren(){
    return children;
}
/**
	 * gets set of children as string
	 * 
	 * @return childStr
	 */
public String getChildrenStr(){
    String childStr ="";
     for(Person c:children){
        childStr += c.getName() +", ";
    }
    return childStr;
}
/**
	 * adds event
	 * 
	 * @param date date of event
     * @param event type or title of event
	 */
public void addEvent(String date, String event){
    this.events.put(date,event);
}
/**
	 * gets map of events
	 * 
	 * @return events
	 */
public Map<String,String> getEvents(){
    return events;
}


}