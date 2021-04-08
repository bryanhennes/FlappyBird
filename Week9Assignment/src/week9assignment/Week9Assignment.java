/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week9assignment;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author bryanhennes
 */
public class Week9Assignment extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
    //method to calculate angle A using length of each side
    public int calcAngleA(double side1, double side2, double side3){
        double radians = Math.acos(((side3*side3)-(side2*side2)-(side1*side1)) / (-2*(side1*side2)));
        int degrees = (int)(radians * (180/Math.PI));
        return degrees;
        
    }
    
    //method to calculate angle B using length of each side
    public int calcAngleB(double side1, double side2, double side3){
        double radians = Math.acos(((side2*side2)-(side1*side1)-(side3*side3)) / (-2*(side1*side3)));
        int degrees = (int)(radians * (180/Math.PI));
        return degrees;
        
    }
    
    //method to calculate angle C using length of each side
    public int calcAngleC(double side1, double side2, double side3){
        double radians = Math.acos(((side1*side1)-(side2*side2)-(side3*side3)) / (-2*(side3*side2)));
        int degrees = (int)(radians * (180/Math.PI));
        return degrees;
        
    }
    
    //method to calculate length of each line of triangle using distance formula
    public double getLineDistance(Line line){
        return(Math.sqrt(Math.pow((line.getEndX()-line.getStartX()), 2) + Math.pow((line.getEndY() - line.getStartY()), 2)));
    }
    
    public double line1Distance;
    public double line2Distance;
    public double line3Distance;
    
    //method to place points randomly on the perimeter of the circle each time it is ran
    public void setRandomLocation(Circle point, Circle c) {
        double angle = Math.random() * 360;
        double x = c.getCenterX() + c.getRadius() * Math.cos(Math.toRadians(angle));
        double y = c.getCenterY() + c.getRadius() * Math.sin(Math.toRadians(angle));
        point.setCenterX(x);
        point.setCenterY(y);
    }


    @Override
    public void start(Stage stage) throws Exception {
        double stageSize = 500.0;
        double circleRadius = 100.0;
        
        double pointRadius = 5.0;
        
        //create main circle in the center
        Circle circle = new Circle();
        circle.setCenterX(stageSize/2);
        circle.setCenterY(stageSize/2);
        circle.setRadius(circleRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        
        
        
        Circle point1 = new Circle();
        setRandomLocation(point1, circle);
        point1.setRadius(pointRadius);
        point1.setFill(Color.BLACK);
        
        Circle point2 = new Circle();
        setRandomLocation(point2, circle);
        point2.setRadius(pointRadius);
        point2.setFill(Color.BLACK);
        
        Circle point3 = new Circle();
        setRandomLocation(point3, circle);
        point3.setRadius(pointRadius);
        point3.setFill(Color.BLACK);
        
       
        
        //create line between point 1 and point 2
        Line line1 = new Line();
        line1.setStroke(Color.RED);
        line1.setStartX(point1.getCenterX());
        line1.setStartY(point1.getCenterY());
        line1.setEndX(point2.getCenterX());
        line1.setEndY(point2.getCenterY());
        line1Distance = getLineDistance(line1);
        
        //create line between point 1 and 3
        Line line2 = new Line();
        line2.setStroke(Color.GREEN);
        line2.setStartX(point1.getCenterX());
        line2.setStartY(point1.getCenterY());
        line2.setEndX(point3.getCenterX());
        line2.setEndY(point3.getCenterY());
        line2Distance = getLineDistance(line2);
        
        //create line between point 2 and 3
        Line line3 = new Line();
        line3.setStroke(Color.BLUE);
        line3.setStartX(point2.getCenterX());
        line3.setStartY(point2.getCenterY());
        line3.setEndX(point3.getCenterX());
        line3.setEndY(point3.getCenterY());
        line3Distance = getLineDistance(line3);
        
        //create angle A label
        Text angleA = new Text();
        angleA.setText("A");
        angleA.setX(line1.getStartX());
        angleA.setY(line1.getStartY());
        double angleADegrees = calcAngleA(line1Distance, line2Distance, line3Distance);
        
        //create angle B label
        Text angleB = new Text();
        angleB.setText("B");
        angleB.setX(line1.getEndX());
        angleB.setY(line1.getEndY());
        double angleBDegrees = calcAngleB(line1Distance, line2Distance, line3Distance);
        
        //create angle C label
        Text angleC = new Text();
        angleC.setText("C");
        angleC.setX(line2.getEndX());
        angleC.setY(line2.getEndY());
        double angleCDegrees = calcAngleC(line1Distance, line2Distance, line3Distance);
        
        //display angle measurements at top left of screen
        Text displayAngleA = new Text();
        displayAngleA.setText("Angle A: "+ (int)angleADegrees + "°");
        displayAngleA.setX(50);
        displayAngleA.setY(50);
        
        Text displayAngleB = new Text();
        displayAngleB.setText("Angle B: "+ (int)angleBDegrees + "°");
        displayAngleB.setX(50);
        displayAngleB.setY(70);
        
        Text displayAngleC = new Text();
        displayAngleC.setText("Angle C: "+ (int)angleCDegrees + "°");
        displayAngleC.setX(50);
        displayAngleC.setY(90);
        

        //handle mouse dragging point A
        EventHandler<MouseEvent> pointOneOnMouseDraggedEventHandler = new EventHandler<MouseEvent>(){
        @Override
       public void handle(MouseEvent e){
            
          
            //when the circle is dragged by mouse do this:
            //on circle perimeter formula: (x- center_x)^2 + (y-center_y)^2 == radius^2
        
            if(Math.pow(e.getSceneX() - circle.getCenterX(), 2) + Math.pow(e.getSceneY() - circle.getCenterY(), 2) <= Math.pow(circle.getRadius(), 2) && Math.pow(e.getSceneX() - circle.getCenterX(), 2) + Math.pow(e.getSceneY() - circle.getCenterY(), 2) > Math.pow(circle.getRadius(), 2)-400){
                point1.setCenterX(e.getSceneX()); //this sets circle center x coordinate to wherever the mouse drags it along the screen
                point1.setCenterY(e.getSceneY()); 
                line1.setStartX(e.getSceneX()); //move line 1 wherever the point1 is moving
                line1.setStartY(e.getSceneY());   
                line2.setStartX(e.getSceneX()); //move line 2 wherever the point1 is moving
                line2.setStartY(e.getSceneY());
                angleA.setX(line1.getStartX()); //this moves the 'A' wherever the line moves so we know where angle A is at all times
                angleA.setY(line1.getStartY());
           
                //calculate length of each line using distance formula to use in angle calc formula
                line1Distance = getLineDistance(line1);  
                line2Distance = getLineDistance(line2);
                line3Distance = getLineDistance(line3);
                
                //calculate angle for each point adn convert from radians to degrees. Then display in top left of screen
                displayAngleA.setText("Angle A Degrees: " + calcAngleA(line1Distance, line2Distance, line3Distance) + "°");
                displayAngleB.setText("Angle B Degrees: " + calcAngleB(line1Distance, line2Distance, line3Distance) + "°");
                displayAngleC.setText("Angle C Degrees: " + calcAngleC(line1Distance, line2Distance, line3Distance) + "°");
                
               
            
  
        }  
       }
       
    }; 
        
        
        //handle mouse dragging point B
        EventHandler<MouseEvent> pointTwoOnMouseDraggedEventHandler = new EventHandler<MouseEvent>(){
        @Override
       public void handle(MouseEvent e){
           
            //when the circle is dragged by mouse do this:
            if(Math.pow(e.getSceneX() - circle.getCenterX(), 2) + Math.pow(e.getSceneY() - circle.getCenterY(), 2) <= Math.pow(circle.getRadius(), 2) && Math.pow(e.getSceneX() - circle.getCenterX(), 2) + Math.pow(e.getSceneY() - circle.getCenterY(), 2) > Math.pow(circle.getRadius(), 2)-400){
                point2.setCenterX(e.getSceneX()); //this sets circle center x coordinate to wherever the mouse drags it along the screen
                point2.setCenterY(e.getSceneY());
                line1.setEndX(e.getSceneX()); //move the end of line1 whereever point2 is moving
                line1.setEndY(e.getSceneY());
                line3.setStartX(e.getSceneX());//move start X coordinate of line 3 to whatever x value the mouse drags it
                line3.setStartY(e.getSceneY()); //move start Y coordinate of line 3 to whatever y vaue the mouse drags it
                angleB.setX(line1.getEndX()); //move 'B' whereever point2 is moving so we know where angle B is at all times
                angleB.setY(line1.getEndY());
                
                line1Distance = getLineDistance(line1); 
                line2Distance = getLineDistance(line2);
                line3Distance = getLineDistance(line3);
            
                displayAngleA.setText("Angle A Degrees: " + calcAngleA(line1Distance, line2Distance, line3Distance) + "°");
                displayAngleB.setText("Angle B Degrees: " + calcAngleB(line1Distance, line2Distance, line3Distance) + "°");
                displayAngleC.setText("Angle C Degrees: " + calcAngleC(line1Distance, line2Distance, line3Distance) + "°");
                
                
                
            }
        }    
               
    };
        
        //handle mouse dragging point C
        EventHandler<MouseEvent> pointThreeOnMouseDraggedEventHandler = new EventHandler<MouseEvent>(){
        @Override
       public void handle(MouseEvent e){
            //when the circle is dragged by mouse do this:
            if(Math.pow(e.getSceneX() - circle.getCenterX(), 2) + Math.pow(e.getSceneY() - circle.getCenterY(), 2) <= Math.pow(circle.getRadius(), 2) && Math.pow(e.getSceneX() - circle.getCenterX(), 2) + Math.pow(e.getSceneY() - circle.getCenterY(), 2) > Math.pow(circle.getRadius(), 2)-400){
                point3.setCenterX(e.getSceneX()); //this sets circle center x coordinate to wherever the mouse drags it along the screen
                point3.setCenterY(e.getSceneY());
                
                line2.setEndX(e.getSceneX());
                line2.setEndY(e.getSceneY()); 
                line3.setEndX(e.getSceneX());
                line3.setEndY(e.getSceneY()); 
                angleC.setX(line2.getEndX());
                angleC.setY(line2.getEndY());
                
                //calculate length of each line using distance formula to use in angle calc formula 
                line1Distance = getLineDistance(line1);
                line2Distance = getLineDistance(line2);
                line3Distance = getLineDistance(line3);
                
                //calculate angle for each point adn convert from radians to degrees. Then display in top left of screen
                displayAngleA.setText("Angle A Degrees: " + calcAngleA(line1Distance, line2Distance, line3Distance) + "°");
                displayAngleB.setText("Angle B Degrees: " + calcAngleB(line1Distance, line2Distance, line3Distance) + "°");
                displayAngleC.setText("Angle C Degrees: " + calcAngleC(line1Distance, line2Distance, line3Distance) + "°");
                
                
               
            }
        }        
    };
        
        point1.addEventFilter(MouseEvent.MOUSE_DRAGGED, pointOneOnMouseDraggedEventHandler); 
        point1.setOnMouseDragged(pointOneOnMouseDraggedEventHandler);
        
        point2.addEventFilter(MouseEvent.MOUSE_DRAGGED, pointTwoOnMouseDraggedEventHandler); 
        point2.setOnMouseDragged(pointTwoOnMouseDraggedEventHandler);
        
        point3.addEventFilter(MouseEvent.MOUSE_DRAGGED, pointThreeOnMouseDraggedEventHandler); 
        point3.setOnMouseDragged(pointThreeOnMouseDraggedEventHandler);

        
        
        
        
     
        Group root = new Group(circle, point1, point2, point3, line1, line2, line3, angleA, angleB, angleC, displayAngleA, displayAngleB, displayAngleC);
        
        
        
        Scene scene = new Scene(root, stageSize, stageSize);
        stage.setScene(scene);
        stage.show();
    }
    
}
