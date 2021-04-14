/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

/**
 *
 * @author bryanhennes
 */


public class FlappyBird extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //method to return random Y coordinate to place pipe gap randomly
    //this is just a test really I haven't finalized the use of this
    public int getRandPlacement(){
        Random rand = new Random();
        return(rand.nextInt(1000));
    }

    
    //double newX;
    //double newY;
    

    @Override
    public void start(Stage stage) throws Exception {
        int stageWidth = 1000;
        int stageHeight = 1000;
        int gapHeight = 100;
        int jumpHeight = 50;
        
        
        //this rect object is the one pipe moving across the screen
        Rectangle rect = new Rectangle();
        rect.setX(stageWidth);
        rect.setY(0);
        rect.setWidth(40);
        rect.setHeight(stageHeight);
        rect.setFill(Color.GREEN);
        
        //this rect is the gap on the main pipe that the bird must jump through
        Rectangle rect2 = new Rectangle(); //make a gap in a pipe
        rect2.setX(stageWidth);
        rect2.setY(getRandPlacement());
        rect2.setStrokeWidth(0);
        rect2.setStroke(Color.SKYBLUE);
        rect2.setWidth(40);
        rect2.setHeight(gapHeight);
        rect2.setFill(Color.SKYBLUE);
        
        //testing bird as a circle
        Circle circle = new Circle(15);
        circle.setCenterX(50);
        circle.setCenterY(700);
        
   
        //this TranslateTransition animates the green pipe from the right to the left of the screen
        TranslateTransition transition = new TranslateTransition();
        transition.setByX(-(stageWidth + 50));
        transition.setDuration(Duration.seconds(5));//5 seconds to move across the screen
        transition.setCycleCount(TranslateTransition.INDEFINITE);//repeat indefinitely
        transition.setNode(rect); //sets animation to move first rect object
        transition.play();
        
        
        //this TranslateTransition animates pipe gap on top of green pipe animation
        TranslateTransition pipeGap = new TranslateTransition();
        pipeGap.setByX(-(stageWidth + 50));
        pipeGap.setDuration(Duration.seconds(5));
        pipeGap.setCycleCount(TranslateTransition.INDEFINITE);
        pipeGap.setNode(rect2);
        pipeGap.play();
        
        //this TranslateTransition animation pushes circle to the bottom when not jumping (acting as gravity)
        TranslateTransition gravity = new TranslateTransition();
        gravity.setByY(700);
        gravity.setDuration(Duration.seconds(4));
        gravity.setCycleCount(TranslateTransition.INDEFINITE);
        gravity.setNode(circle);
        
        

        
        Group root = new Group(rect, rect2, circle);
        Scene scene = new Scene(root, stageHeight, stageWidth, Color.SKYBLUE);
        stage.setScene(scene);
        
        //when space bar is pushed make the circle jump by jumpHeight pixels each time (jumpHeight = 50 pixels)
        //each time space bar is pushed stop previous gravity animation if playing and restart gravity on current jump
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE){
                  
                    gravity.stop();
                    circle.setCenterY(circle.getCenterY()-jumpHeight);                    
                    gravity.play();
            }

        });

        stage.show();
        
        
    }
        
    }
    

