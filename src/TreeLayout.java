import java.util.*;

public class TreeLayout {
    private Map<Person, Integer> genMap= new HashMap<>();
    private Map<Integer, List<Person>> rows = new HashMap<>();
    private Map<Person, Cords> coordinates = new HashMap<>();
    private Map<Family,Cords> famCoordinates = new HashMap<>();

    /**
	 * sets generation of the people in the family tree based on the home person
	 * 
	 * @param p home person
     * @param gen generation of the home person, generally zero
	 */
public void assignGen(Person p, int gen){
    if (genMap.containsKey(p)) {
        return;
    }
    genMap.put(p, gen);
    if (rows.containsKey(gen)) {
        rows.get(gen).add(p);
    }else{
        List<Person> temp = new ArrayList<Person>();
        temp.add(p);
        rows.put(gen, temp);}
    
    for(Person parent: p.getParents()){
        assignGen(parent, gen-1);
    }
    for(Person child: p.getChildren()){
        assignGen(child, gen+1);
    }
}
/**
	 * sets coordates of family nodes that sit between parents
	 */
public void assignFam(){
    List<Integer> gens = new ArrayList<>(rows.keySet());
    Collections.sort(gens);
    Collections.reverse(gens);
    for(int gen:gens){
      List<Person> ppl= rows.get(gen);
      for (Person p:ppl){
        List<Person> parents =new ArrayList<>(p.getParents());
        if (parents.size()>=2) {
            Person p1=parents.get(0);
            Person p2=parents.get(1);
            Family exists = null;
            for(Family f: p1.getSpouseFam()){
                if (f.getParent1()==p2||f.getParent2()==p2) {
                    exists=f;
                    break;
                }
            }
            if (exists!=null) {
                exists.addChild(p);
            }else{
             Family fam= new Family(p1,p2);
             fam.addChild(p);
             p.addChildFam(fam);
             p1.addSPouseFam(fam);
             p2.addSPouseFam(fam);
             famCoordinates.put(fam, new Cords(0,0));
            }
            }
        }
           
    }}
/**
	 * determines playcement of the people in the family tree
	 */
public void placement(){
    double x;
    double y;
    int minGen = Collections.min(rows.keySet());
    int xspacing=300;
    int yspacing=300;//might change spacing value
    List<Integer> gens = new ArrayList<>(rows.keySet());
    Collections.sort(gens);
    Collections.reverse(gens);
    for(int gen: gens){
        List<Person> ppl= rows.get(gen);
        for(int i=0;i<ppl.size();i++){
            Person p=ppl.get(i);
            y=(gen-minGen)*yspacing;
            if (!p.getChildren().isEmpty()){
                double sum = 0;
                int count= 0;
                for(Person child: p.getChildren()){
                    Cords c = coordinates.get(child);
                    if (c!= null) {
                        sum+=c.x;
                        count++;
                    }
                }
                if (count>0) {
                    x=(sum/count)+(i-(ppl.size()-1)/2.0)*xspacing;
                }else{x=(i-(ppl.size()-1)/2.0)*xspacing;}
            }else{x=(i-(ppl.size()-1)/2.0)*xspacing;}
                coordinates.put(p, new Cords(x, y));
            }}
            for(Family fam: famCoordinates.keySet()){
                double sum=0;
                int count=0;
                for(Person child: fam.getChildren()){
                    Cords c = coordinates.get(child);
                    System.out.println(c.x+","+c.y);
                    if (c!=null){
                        sum+=c.x;
                        count++;
                    }
                }
                if (count>0){
                    double x1=sum/count;
                    double y1=(genMap.get(fam.getParent1())-minGen)*yspacing;
                    famCoordinates.put(fam, new Cords(x1, y1));

                }
            }
        }
    
   

/**
	 * creates layout of the family tree
	 * 
	 * @return coordinates
	 */
public Map<Person, Cords> layout(Person home){
    genMap.clear();
    rows.clear();
    coordinates.clear();
    famCoordinates.clear();
    assignGen(home, 0);
    assignFam();
    placement();
    Cords homeCords = coordinates.get(home);
    double canvasW=3000;
    double canvasH =3000;
    double targX = canvasW/2;
    double targY= canvasH*.7;
    double shiftX=targX-homeCords.x;
    double shiftY =targY-homeCords.y;

    for(Cords c: coordinates.values()){
        c.x+=shiftX;
        c.y+=shiftY;
    }
    for(Cords c: famCoordinates.values()){
        c.x+=shiftX;
        c.y+=shiftY;
    }
    return coordinates;
}
/**
	 * gets map of family coordinates
	 * 
	 * @return famCoordinates
	 */
public Map<Family, Cords> getFamilyCords(){
    return famCoordinates;
}
}
