[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/A70Xv2lU)
# FinalCS2

- This is a gui based family tree maker that sorta works. I had big dreams making this that did not pan out in 2 weeks. I ould like to think this is at least has better bones than my family tree maker from CS1. I used SceneBuilder this time around which was a bit of a learning curve. Definently easier for layout and being able to just drag and drop stuff but a lot of my stuff is on an anchorpane and I struggled to make stuff expand properly when I full screened it so maybe don't full screen anything but the Tree Viewer page.

- Used Java 21 and JavaFX 23

- Run FamilyTreeApp.java

- Repository
    - src/ Contains all source code and FXML files
      - src/pictures/ Gui assets I made and used
      - src/ProfilePics/ where profile pictures would go if that worked
    - SavedTrees/ where family trees save
- Software
    - Data
        - Person
        - Family
        - FamilyTree
    - User Interface
        - Any file that ends with Controller
        - fxml files


- I have some videos.
    - https://youtu.be/KDWJERQNsGk
      - FamilyTreeAppUsing shows me opening the app and going through the new tree option on the main page. The people I added are then seen on family tree viewer. I then go to the add a new person page that has a layout but nothing actually works. Then I open the save file that I think overwrote the old save file I had so that is an issue. Then I deminstrate that the load function sorta works.
  - https://youtu.be/5AyAEyhicNQ
      - UploadSaveFiletoAncestry shows me taking the savefile from my app that was made in the GEDCOM format and going into Ancestry.com and showing that it is readable as a GEDCOM file by other ancestry/ family tree software which is cool. Before I uploaded it I did go in and delete the extra copies of the people that my save function made, which is less cool.

- Citations:
    - Was having troubles with ListView in indiPersonPageController this lead me to ObservableList
      - https://stackoverflow.com/questions/29401458/javafx-listview-observe-map
    - Manual for GEDCOM that I refrenced for formating
      - https://lifelines.sourceforge.net/manual307/ll-doc.htm
    - I watched this video to help figure out how controllers work
      - https://www.youtube.com/watch?v=NpQU1xU6U8w
    
- General Notes
    - GEDCOM is the like industry standard for family tree/ geneology software file sharing 
      - BUT it was made by the LDS church (Mormons) and so somethings are less than ideal such as you can not have two wives or husbands in the family in the GEDCOM file and it is assumed that the two parents in a family are married or have been married
    - ON the main Page there are two buttons off to the side that don't work but I kept them because I would like to continue working on this
      - The corkboard thing would go to a page similiar to the new tree home person page but with an added section for a research question. This is basically just because I have always wanted to make one of the conspiracy boards and I've been working on figuring out who secret great grandpa was which has pissed off so many people. This would be the time to make a conspiracy board but everything is done online and like digitially so it would not really be functional and so I thought it would be fun to make a digitial option of a conspiracy board. 
      - Notes is just notes


