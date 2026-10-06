import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FamilyTree {
    private Person homePerson;
    private String treeName;
    private List<Person> allPeople=new ArrayList<>();
   

/**
	 * Makes new family tree
	 */    
public FamilyTree(){
    treeName="Family Tree";
}
/**
	 * Adds a person to the list of all people in the family tree
	 * 
	 * @param person person to be added
	 */
public void addPersonToTree(Person person){
    allPeople.add(person);
}
/**
	 * Gets List of all people in the family tree
	 * 
	 * @return allPeople
	 */
public List<Person> getAllPeople(){
    return allPeople;
}
/**
	 * Sets tree name
	 * 
	 * @param name new name of tree
	 */
public void setTreeName(String name){
    treeName=name;
}
/**
	 * Gets tree name
	 * 
	 * @return treeName
	 */
public String getTreeName(){
    return treeName;
}
/**
	 * Sets homePerson
	 * 
	 * @param person new homePerson
	 */
public void setHome(Person person){
    homePerson=person;
}
/**
	 * Gets homePerson
	 * 
	 * @return homePerson
	 */
public Person getHome(){
    return homePerson;
}
/**
	 * Saves a family tree in a ged file
	 * 
	 * @param save save file name
     * @throws FileNotFoundException
	 */
public void save(String save) throws FileNotFoundException{
    File saves = new File("SavedTrees");
        if (!saves.exists()){
            saves.mkdirs();
        }
    File saveFile =new File(saves,save+".ged");
    FamilyTree tree = ViewSwitchController.getTree();
        try(PrintStream out= new PrintStream(saveFile)){
            out.println("0 HEAD");
            out.println("1 SOUR FamilyTreeApp");
            out.println("1 FILE "+tree.getTreeName());
            out.println("1 GEDC");
            out.println("2 VERS 5.5");
            out.println("2 FORM LINEAGE-LINKED");
            out.println("1 CHAR UTF-8");
            
            for (Person p: tree.getAllPeople()){
                out.println("0 @I"+p.getId()+"@ INDI");
                out.println("1 NAME "+p.getFirstName()+" /"+p.getSurname()+"/");
                out.println("2 GIVN "+p.getFirstName());
                out.println("2 SURN "+p.getSurname());
                String sex ="";
                switch (p.getGender()) {
                    case "Woman": sex="F";break;
                    case "Man": sex="M";break;
                    case "Other": sex="U";break;
                    default:sex="U";break;
                }
                out.println("1 SEX "+sex);
                out.println("1 BIRT");
                out.println("2 DATE "+p.getBirth());
                if (!p.getDeath().equals("Living")) {
                     out.println("1 DEAT");
                    out.println("2 DATE "+p.getDeath());
                }
                for(Family famc: p.getChildFam()){
                    out.println("1 FAMC @F"+famc.getFamID()+"@");
                }
                for(Family fams: p.getSpouseFam()){
                    out.println("1 FAMS @F"+fams.getFamID()+"@");
                }
                for(Map.Entry<String,String> e:p.getEvents().entrySet()){//maybe make an event class object thing
                    out.println("1 EVEN");
                    out.println("2 TYPE " + e.getValue());
                    out.println("2 DATE "+ e.getKey());
                }
            }

            for(Family fam:Family.getFamilies()){
                out.println("0 @F"+fam.getFamID()+"@ FAM");
                if (fam.getParent1().getGender().equals("Woman")) {
                    out.println("1 HUSB @I"+fam.getParent2().getId()+"@");
                    out.println("1 WIFE @I"+fam.getParent1().getId()+"@");
                }else{
                    out.println("1 HUSB @I"+fam.getParent1().getId()+"@");
                    out.println("1 WIFE @I"+fam.getParent2().getId()+"@");}
                for(Person child:fam.getChildren()){
                    out.println("1 CHIL @I"+child.getId()+"@");
                }
            }
            out.println("0 TRLR");
        }
}
/**
	 * Loads a ged file of a family tree
	 * 
	 * @param file ged file
     * @throws FileNotFoundException
	 */
public static void load(File file) throws FileNotFoundException{
    Scanner scan = new Scanner(file);
    FamilyTree tree = new FamilyTree();
    Map<String, Person> people = new HashMap<>();
    Map<String, Family> families = new HashMap<>();
    Person currPerson = null;
    Family currFamily= null;
    while (scan.hasNextLine()) {
        String line =scan.nextLine().trim();
        String[] parts = line.split(" ");
        if (parts.length<2) continue;
        if (parts[0].equals("0")&& parts.length>=3&& parts[2].equals("INDI")) {
            String id = parts[1].replace("@", "").replace("I", "");
            currPerson=new Person();
            currPerson.setID(Integer.parseInt(id));
            people.put(id, currPerson);
            tree.addPersonToTree(currPerson);
        }else if(parts[0].equals("1")&& parts.length>=3&& parts[1].equals("NAME")){
            String firstName = parts[2];
            String lastName="";
            if (!parts[3].startsWith("/")) {
                firstName=firstName+" "+parts[3];
                lastName = parts[4].replace("/", "");
            
            }else{lastName = parts[3].replace("/", "");
            }
            currPerson.setName(firstName, lastName);
        }else if(parts[0].equals("1")&& parts[1].equals("BIRT")){
            String next = scan.nextLine().trim();
            String[] p =next.split(" ");
            if (p[0].equals("2")&&p.length>=3&& p[1].equals("DATE")) {
                String birth = p[2];
                if (p.length>3) {
                    for(int i=3;i<p.length;i++){
                        birth=birth+" "+p[i];
                    }
                }currPerson.setBirth(birth);
            }
                
        }else if(parts[0].equals("1")&& parts[1].equals("DEAT")){
            String next = scan.nextLine().trim();
            String[] p =next.split(" ");
            if (p[0].equals("2")&&p.length>=3&& p[1].equals("DATE")) {
                String death = p[2];
                if (p.length>3) {
                    for(int i=3;i<p.length;i++){
                        death=death+" "+p[i];
                    }
                }currPerson.addDeath(death);
            } 
        }else if (parts[0].equals("1")&&parts.length>=3&& parts[1].equals("FAMC")) {
            String id =parts[2].replace("@","").replace("F", "");
            if (!families.containsKey(id)) {
                Family fam = new Family(null, null);
                fam.setID(Integer.parseInt(id));
                fam.addChild(currPerson);
                families.put(id, fam);
            }else{families.get(id).addChild(currPerson);}
        }else if (parts[0].equals("1")&&parts.length>=3&& parts[1].equals("FAMS")) {
            String id =parts[2].replace("@","").replace("F", "");
            if (!families.containsKey(id)) {
                Family fam = new Family(currPerson, null);
                fam.setID(Integer.parseInt(id));
                families.put(id, fam);
            }else{if (families.get(id).getParent1()==null) {
                families.get(id).setParent1(currPerson);
            }else{families.get(id).setParent2(currPerson);}}
        }else if(parts[0].equals("1")&& parts[1].equals("EVEN")){
            String next = scan.nextLine().trim();
            String[] p =next.split(" ");
            String eventType="";
            String eventDate="";
            if (p[0].equals("2")&&p.length>=3&& p[1].equals("TYPE")) {
                eventType = p[2];
            }String next1 = scan.nextLine().trim();
            String[] p1 =next1.split(" ");
            if (p1[0].equals("2")&&p1.length>=3&& p1[1].equals("DATE")) {
                eventDate = p1[2];
                if (p1.length>3) {
                    for(int i=3;i<p1.length;i++){
                        eventDate=eventDate+" "+p1[i];
                    }
                }
            }currPerson.addEvent(eventDate, eventType);
                
        }else if (parts[0].equals("0")&&parts.length >= 3&&parts[2].equals("FAM")) {
            String famId =parts[1].replace("@", "").replace("F", "");
                String currID="";
                if (!families.containsKey(famId)) {
                Family fam = new Family(null, null);
                fam.setID(Integer.parseInt(famId));
                currFamily=fam;
                families.put(famId, currFamily);
            }else{
                currFamily=families.get(famId);
               }
            String next = scan.nextLine().trim();
            String[] p =next.split(" ");
            if (p[0].equals("1")&&p[1].equals("HUSB")) {
                currID=p[2].replace("@", "").replace("I", "");
                if (!people.containsKey(currID)) {
                    Person husb = new Person();
                    currFamily.setParent1(husb);
                }else{currFamily.setParent1(people.get(currID));}
            }
            String next1 = scan.nextLine().trim();
            String[] p1 =next1.split(" ");
            if (p1[0].equals("1")&&p1[1].equals("WIFE")) {
                currID=p1[2].replace("@", "").replace("I", "");
                if (!people.containsKey(currID)) {
                    Person wife = new Person();
                    currFamily.setParent2(wife);
                }else{currFamily.setParent2(people.get(currID));}
            }while (scan.hasNextLine()) {
                String next2= scan.nextLine().trim();
                String[] p2=next2.split(" ");
                if (!p2[0].equals("1")) break;
                 if (p2[0].equals("1")&&p2[1].equals("CHIL")) {
                currID=p2[2].replace("@", "").replace("I", "");
                if (!people.containsKey(currID)) {
                    Person child = new Person();
                    currFamily.addChild(child);;
                }else{currFamily.addChild(people.get(currID));}
            }
        }
        }}ViewSwitchController.setTree(tree);
    }


}
