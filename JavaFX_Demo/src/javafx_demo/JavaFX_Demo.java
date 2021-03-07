//bryan hennes lab assignment 3

package javafx_demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX_Demo extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        int width = 800;//width of entire stage
        int height = 800; //height of entire stage
        
        Rectangle sky = new Rectangle(0, 0, width, height/2-50);//create sky with rect object
        sky.setFill(Color.SKYBLUE); //color of sky to be sky blue
        
        Rectangle grass = new Rectangle(0, height/2-50, width, height);//creaste grass with rect object
        grass.setFill(Color.LIMEGREEN); //color of grass to be lime green
        
        Rectangle[] stairs = new Rectangle[11]; // create array of stairs with rectangle objects
        int stairHeight = 10;
       
        
        //use a for loop to iterate and initilialize each rectangle object that makes up the entire stairset
        for(int i = 0, startingWidth = 400, stairYPlacement = height-200, stairXPLacement = width/4; i < stairs.length; i++, startingWidth+=20, stairXPLacement-=10,stairYPlacement+=stairHeight ){
            stairs[i] = new Rectangle(stairXPLacement, stairYPlacement, startingWidth, stairHeight);
            stairs[i].setFill(Color.LIGHTGREY); //color of each stair
            stairs[i].setStroke(Color.BLACK); //outline color of each stair
        }
        
        Rectangle house = new Rectangle(120, height/4, width-240, height-300);
        house.setFill(Color.BROWN);
        house.setStroke(Color.BLACK);
        
        int houseWidth = width-240;
        
        Rectangle bottomRoof = new Rectangle(120, height/4, width-240, 40);
        bottomRoof.setFill(Color.WHITE);
        bottomRoof.setStroke(Color.BLACK);
        
        Polygon triangleRoof= new Polygon(90, height/4, width/2, 0, width-90, height/4); //polygon shape to form the top triangle shaped portion of roof
        triangleRoof.setFill(Color.WHITE);
        triangleRoof.setStroke(Color.BLACK);
        
        Rectangle underLeftPillar = new Rectangle(120, stairs[0].getY(),stairs[0].getX()-120, stairHeight/2);//small platform under far left pillar
        underLeftPillar.setFill(Color.LIGHTGREY);
        underLeftPillar.setStroke(Color.BLACK);
        
        Rectangle underRightPillar = new Rectangle(600, stairs[0].getY(), stairs[0].getX()-120, stairHeight/2);//small platform under far right pillar
        underRightPillar.setFill(Color.LIGHTGREY);
        underRightPillar.setStroke(Color.BLACK);
        
        int pillarWidth = 50; //every pillar to be this width
        int pillarY = height/4;
        int pillarHeight = (int)stairs[0].getY()-(height/4); //every pillar to be this height (cast as int as .getY must return double
        int pillarSpacing = houseWidth/4 + 20; //we want to place pillars evenly spaced throughout width of house
        
        Rectangle[] pillars = new Rectangle[4];
        
        //for loop to create 4 pillars
        for (int i = 0, startingPillarSpace = 135; i < pillars.length; i++, startingPillarSpace+=pillarSpacing){
            pillars[i] = new Rectangle(startingPillarSpace, pillarY, pillarWidth, pillarHeight);
            pillars[i].setFill(Color.WHITE);
            pillars[i].setStroke(Color.BLACK);
        }
        
        //set sizing of under pillars
        int underPillarY = (int)underLeftPillar.getY()-15;
        int underPillarWidth = 61;
        int underPillarHeight = 15;
        
        Rectangle[] underPillars = new Rectangle[4]; //creat an array of rectangle objects creating under the pillars
        
        //initialize all 4 under pillar objects
        for (int i = 0, startingX = 130; i < underPillars.length; i++, startingX+=pillarSpacing){
            underPillars[i] = new Rectangle(startingX, underPillarY, underPillarWidth, underPillarHeight);
            underPillars[i].setFill(Color.WHITE);
            underPillars[i].setStroke(Color.BLACK);
        }
        
        Ellipse[] pillarCircles = new Ellipse[8];//ceate array of all the circles on pillars
        int pillarCircleY = 255;
        int pillarCircleRadius = 15;
        
        //loop to initialize 8 pillarCircle objects, each inner loop creates 2
        for (int i = 0, startingX = 135; i < pillarCircles.length; i+=2, startingX+=160){
            for (int j =i, currentX = startingX; j < i+2; j++, currentX+=50){
                pillarCircles[j] = new Ellipse(currentX, pillarCircleY, pillarCircleRadius, pillarCircleRadius);
                pillarCircles[j].setFill(Color.WHITE);
                pillarCircles[j].setStroke(Color.BLACK);
            }
        }
        
        //creat 2 outer doors
        Rectangle outerDoor1 = new Rectangle(200, 440, 80, 200);
        outerDoor1.setFill(Color.WHITE);
        outerDoor1.setStroke(Color.BLACK);
        
        Rectangle outerDoor2 = new Rectangle(520, 440, 80, 200);
        outerDoor2.setFill(Color.WHITE);
        outerDoor2.setStroke(Color.BLACK);
        
        //create middle door
        Rectangle middleDoor = new Rectangle(360, 400, 80, 220);
        middleDoor.setFill(Color.WHITE);
        middleDoor.setStroke(Color.BLACK);
        
        Rectangle greyBox = new Rectangle(360, 280, 80, 50);
        greyBox.setFill(Color.LIGHTGREY);
        greyBox.setStroke(Color.BLACK);
        
        //create small black windows in doors
        Rectangle smallWindow = new Rectangle(215, 450, 15, 15);
        smallWindow.setFill(Color.BLACK);
        int smallWindowSize = 15;
        int smallWindowY = 450;
        
        Rectangle[] smallWindows = new Rectangle[6];
        
        for(int i =0, startingX = 215 ; i < smallWindows.length; i+=2, startingX+=160){
            for (int j = i, currentX = startingX; j < i+2; j++, currentX+=35){
                smallWindows[j] = new Rectangle(currentX, smallWindowY, smallWindowSize, smallWindowSize );
                smallWindows[j].setFill(Color.BLACK);
            }
        }
        
        int bigWindowWidth = smallWindowSize; //set the width of all the big black windows
        int bigWindowHeight = smallWindowSize+5; //set the size of height for all big black windows
        
        Rectangle[] bigWindows = new Rectangle[24];//create array of 24 bigWindow rectangle objects
       
        // loop through and initialize all 24 big windows spacing them out evenly across all 3 doors
        for(int i =0, startingX = 215, startingY = smallWindowY+30; i < bigWindows.length; i+=6, startingY+=30){
            int counter = 0; //count every doors for every window
            for (int j = i, currentX = startingX; j <i+6; j++, currentX+=35){
                if(counter == 2){
                    currentX+=90; //for every 2 big windows space out to the next door
                    counter = 0;
                }
                bigWindows[j] = new Rectangle(currentX, startingY, bigWindowWidth, bigWindowHeight);
                bigWindows[j].setFill(Color.BLACK);
                counter++;
            }
            
        }
        
        Group background = new Group(sky, grass);//group of background images
        Group building = new Group(house, triangleRoof, underLeftPillar, underRightPillar, pillars[0], pillars[1], pillars[2], pillars[3], underPillars[0], underPillars[1], underPillars[2], underPillars[3], 
         bottomRoof,pillarCircles[0], pillarCircles[1], pillarCircles[2], pillarCircles[3], pillarCircles[4], pillarCircles[5], pillarCircles[6], pillarCircles[7]);//outerDoor1, outerDoor2, middleDoor, greyBox);
        Group doors = new Group(outerDoor1, outerDoor2, middleDoor, greyBox, smallWindows[0], smallWindows[1], smallWindows[2], smallWindows[3], smallWindows[4], smallWindows[5], bigWindows[0],bigWindows[1],bigWindows[2],bigWindows[3],bigWindows[4],bigWindows[5],bigWindows[6],bigWindows[7],bigWindows[8],bigWindows[9],bigWindows[10],bigWindows[11],bigWindows[12],bigWindows[13],bigWindows[14],bigWindows[15],bigWindows[16],bigWindows[17],bigWindows[18],bigWindows[19],bigWindows[20],bigWindows[21],bigWindows[22],bigWindows[23]);
        Group stairset = new Group(stairs[0], stairs[1], stairs[2], stairs[3], stairs[4], stairs[5], stairs[6], stairs[7], stairs[8], stairs[9], stairs[10]); //group together stairs
        Group root = new Group(background, building, doors, stairset); //root group
        Scene scene = new Scene (root, width, height);
        
        stage.setScene(scene);
        stage.setTitle("Lab Assignment 3");
        stage.show();
    }
    
}
