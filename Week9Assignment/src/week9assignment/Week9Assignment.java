/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week9assignment;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    
    //fix this so it gives correct degrees
    public double calcAngleA(double side1, double side2, double side3){
        double radians = Math.acos(((side1*side1)+(side2*side2)-(side3*side3)) / (-2*side1*side2));
        double answer = radians * (180/Math.PI);//convert radians to degrees
        return answer;
    }
    
    public double line1Distance;
    public double line2Distance;
    public double line3Distance;
    public double aRadians;
    public double bRadians;
    public double cRadians;
    
  

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
        point1.setCenterX(circle.getCenterX());
        point1.setCenterY(circle.getCenterY()-circle.getRadius());
        point1.setRadius(pointRadius);
        point1.setFill(Color.BLACK);
        
        Circle point2 = new Circle();
        point2.setCenterX(circle.getCenterX());
        point2.setCenterY(circle.getCenterY()+circle.getRadius());
        point2.setRadius(pointRadius);
        point2.setFill(Color.BLACK);
        
        Circle point3 = new Circle();
        point3.setCenterX(circle.getCenterX()- circle.getRadius());
        point3.setCenterY(circle.getCenterY());
        point3.setRadius(pointRadius);
        point3.setFill(Color.BLACK);
        
        //create line between point 1 and point 2
        Line line1 = new Line();
        line1.setStroke(Color.RED);
        line1.setStartX(point1.getCenterX());
        line1.setStartY(point1.getCenterY());
        line1.setEndX(point2.getCenterX());
        line1.setEndY(point2.getCenterY());
       // double line1Distance = Math.sqrt(Math.pow((line1.getEndX()-line1.getStartX()), 2) + Math.pow((line1.getEndY() - line1.getStartY()), 2));
        
        //create line between point 1 and 3
        Line line2 = new Line();
        line2.setStroke(Color.GREEN);
        line2.setStartX(point1.getCenterX());
        line2.setStartY(point1.getCenterY());
        line2.setEndX(point3.getCenterX());
        line2.setEndY(point3.getCenterY());
       // double line2Distance = Math.sqrt(Math.pow((line2.getEndX()-line2.getStartX()), 2) + Math.pow((line2.getEndY() - line2.getStartY()), 2));
        
        //create line between point 2 and 3
        Line line3 = new Line();
        line3.setStroke(Color.BLUE);
        line3.setStartX(point2.getCenterX());
        line3.setStartY(point2.getCenterY());
        line3.setEndX(point3.getCenterX());
        line3.setEndY(point3.getCenterY());
        //double line3Distance = Math.sqrt(Math.pow((line3.getEndX()-line3.getStartX()), 2) + Math.pow((line3.getEndY() - line3.getStartY()), 2));
        
        Text angleA = new Text();
        angleA.setText("A");
        angleA.setX(line1.getStartX());
        angleA.setY(line1.getStartY());
        double angleADegrees = 0.0;
     
        Text angleB = new Text();
        angleB.setText("B");
        angleB.setX(line1.getEndX());
        angleB.setY(line1.getEndY());
        double angleBDegrees = 0.0;
        
        Text angleC = new Text();
        angleC.setText("C");
        angleC.setX(line2.getEndX());
        angleC.setY(line2.getEndY());
        double angleCDegrees = 0.0;
        
        Text displayAngleA = new Text();
        displayAngleA.setText("Angle A: "+angleADegrees);
        displayAngleA.setX(50);
        displayAngleA.setY(50);
        
        Text displayAngleB = new Text();
        displayAngleB.setText("Angle B: "+angleBDegrees);
        displayAngleB.setX(50);
        displayAngleB.setY(70);
        
        Text displayAngleC = new Text();
        displayAngleC.setText("Angle C: "+angleCDegrees);
        displayAngleC.setX(50);
        displayAngleC.setY(90);
        
        
        
        
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
                line1Distance = Math.sqrt(Math.pow((line1.getEndX()-line1.getStartX()), 2) + Math.pow((line1.getEndY() - line1.getStartY()), 2));
                line2Distance = Math.sqrt(Math.pow((line2.getEndX()-line2.getStartX()), 2) + Math.pow((line2.getEndY() - line2.getStartY()), 2));
                line3Distance = Math.sqrt(Math.pow((line3.getEndX()-line3.getStartX()), 2) + Math.pow((line3.getEndY() - line3.getStartY()), 2));
                
                //calculate angle for each point adn convert from radians to degrees. Then display in top left of screen
                aRadians = Math.acos(((line3Distance*line3Distance)-(line2Distance*line2Distance)-(line1Distance*line1Distance)) / (-2*(line1Distance*line2Distance)));
                displayAngleA.setText("Angle A Degrees: " + (int)(aRadians * (180/Math.PI)));
                bRadians = Math.acos(((line2Distance*line2Distance)-(line1Distance*line1Distance)-(line3Distance*line3Distance)) / (-2*(line1Distance*line3Distance)));
                displayAngleB.setText("Angle B Degrees: " + (int)(bRadians * (180/Math.PI)));
                cRadians = Math.acos(((line1Distance*line1Distance)-(line2Distance*line2Distance)-(line3Distance*line3Distance)) / (-2*(line3Distance*line2Distance)));
                displayAngleC.setText("Angle C Degrees: " + (int)(cRadians * (180/Math.PI)));
                
               
            }
            
               
               
           
        }      
    }; 
        
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
                
                //calculate length of each line using distance formula to use in angle calc formula
                line1Distance = Math.sqrt(Math.pow((line1.getEndX()-line1.getStartX()), 2) + Math.pow((line1.getEndY() - line1.getStartY()), 2));
                line2Distance = Math.sqrt(Math.pow((line2.getEndX()-line2.getStartX()), 2) + Math.pow((line2.getEndY() - line2.getStartY()), 2));
                line3Distance = Math.sqrt(Math.pow((line3.getEndX()-line3.getStartX()), 2) + Math.pow((line3.getEndY() - line3.getStartY()), 2));
                
                //calculate angle for each point adn convert from radians to degrees. Then display in top left of screen
                aRadians = Math.acos(((line3Distance*line3Distance)-(line2Distance*line2Distance)-(line1Distance*line1Distance)) / (-2*(line1Distance*line2Distance)));
                displayAngleA.setText("Angle A Degrees: " + (int)(aRadians * (180/Math.PI)));
                bRadians = Math.acos(((line2Distance*line2Distance)-(line1Distance*line1Distance)-(line3Distance*line3Distance)) / (-2*(line1Distance*line3Distance)));
                displayAngleB.setText("Angle B Degrees: " + (int)(bRadians * (180/Math.PI)));
                cRadians = Math.acos(((line1Distance*line1Distance)-(line2Distance*line2Distance)-(line3Distance*line3Distance)) / (-2*(line3Distance*line2Distance)));
                displayAngleC.setText("Angle C Degrees: " + (int)(cRadians * (180/Math.PI)));
                
                
                
            }
        }      //sets circle y axis to wherever mouse drags it 
               
    };
        
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
                line1Distance = Math.sqrt(Math.pow((line1.getEndX()-line1.getStartX()), 2) + Math.pow((line1.getEndY() - line1.getStartY()), 2));
                line2Distance = Math.sqrt(Math.pow((line2.getEndX()-line2.getStartX()), 2) + Math.pow((line2.getEndY() - line2.getStartY()), 2));
                line3Distance = Math.sqrt(Math.pow((line3.getEndX()-line3.getStartX()), 2) + Math.pow((line3.getEndY() - line3.getStartY()), 2));
   
                
                //calculate angle for each point adn convert from radians to degrees. Then display in top left of screen
                aRadians = Math.acos(((line3Distance*line3Distance)-(line2Distance*line2Distance)-(line1Distance*line1Distance)) / (-2*(line1Distance*line2Distance)));
                displayAngleA.setText("Angle A Degrees: " + (int)(aRadians * (180/Math.PI)));
                bRadians = Math.acos(((line2Distance*line2Distance)-(line1Distance*line1Distance)-(line3Distance*line3Distance)) / (-2*(line1Distance*line3Distance)));
                displayAngleB.setText("Angle B Degrees: " + (int)(bRadians * (180/Math.PI)));
                cRadians = Math.acos(((line1Distance*line1Distance)-(line2Distance*line2Distance)-(line3Distance*line3Distance)) / (-2*(line3Distance*line2Distance)));
                displayAngleC.setText("Angle C Degrees: " + (int)(cRadians * (180/Math.PI)));
                
               
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
