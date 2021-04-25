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
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;

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
    

    
   

    Rectangle[] pipes = new Rectangle[300];
    Rectangle[] pipeGaps = new Rectangle[300];
    TranslateTransition[] pipeTransitions = new TranslateTransition[pipes.length];
    TranslateTransition[] pipeGapTransitions = new TranslateTransition[pipes.length];


    public void setPipes(Rectangle[] rects, int width, int height){
        for (int i =0; i < rects.length; i++){
            rects[i] = new Rectangle();
            rects[i].setX(width + 50);
            rects[i].setY(0);
            rects[i].setWidth(40);
            rects[i].setHeight(height);
            rects[i].setFill(Color.GREEN);
        }
    }
    
    public void setPipeGaps(Rectangle[] rects, int width){
        int gapHeight = 120;
        for (int i =0; i < rects.length; i++){
            rects[i] = new Rectangle();
            rects[i].setX(width + 42);
            rects[i].setY((int)(Math.random()* 600) + 50); //whyyy when I run this it doubles or triples pipe gaps?????
            rects[i].setStrokeWidth(0);
            rects[i].setStroke(Color.SKYBLUE);
            rects[i].setWidth(60);
            rects[i].setHeight(gapHeight);//(int)(Math.random()* 200) + 50);
            rects[i].setFill(Color.SKYBLUE);
            
            
        }
    }
    
    public void setPipeAnimations(TranslateTransition[] animations, int width, Rectangle[] rects){
        
        
        for (int i = 0, delayTime= 1; i < rects.length; i++, delayTime+=2){
            
            animations[i] = new TranslateTransition();
            animations[i].setByX(-(width + 150));
            animations[i].setDuration(Duration.seconds(6));//5 seconds to move across the screen
            animations[i].setCycleCount(1);//TranslateTransition.INDEFINITE);//repeat indefinitely
            animations[i].setNode(rects[i]); //sets animation to move first rect object  
            animations[i].setDelay(Duration.seconds(delayTime));
            animations[i].play();
           
            
  
    }
    }
    
     public void setPipeGapAnimations(TranslateTransition[] animations, int width, Rectangle[] rects){
        for (int i = 0, delayTime= 1; i < rects.length; i++, delayTime+=2){
            animations[i] = new TranslateTransition();
            animations[i].setByX(-(width + 150));
            animations[i].setDuration(Duration.seconds(6));//5 seconds to move across the screen
            animations[i].setCycleCount(1);//repeat indefinitely
            animations[i].setNode(rects[i]); //sets animation to move first rect object  
            animations[i].setDelay(Duration.seconds(delayTime));
            animations[i].play();
          
         
        }
        
    }
     
     public TranslateTransition setGravity(Circle circle){
        TranslateTransition gravity = new TranslateTransition();
        gravity.setByY(700);
        gravity.setDuration(Duration.seconds(4));
        gravity.setCycleCount(TranslateTransition.INDEFINITE);
        gravity.setNode(circle);
        
        return(gravity);
     }
     
     public void playGravity(TranslateTransition gravity){
         gravity.play();
     } 
     
     public boolean hitsBird(Circle circle, Rectangle[] pipeGaps, TranslateTransition[] pipeGapAnimations){
         boolean result = false;
         for (int i =0; i< pipeGaps.length; i++){
             if(pipeGapAnimations[i].getStatus()==Status.RUNNING){
                 if(getCurrentPipeXPosition(pipeGaps[i]) == getCurrentBirdXPosition(circle) && getCurrentPipeYPosition(pipeGaps[i]) == getCurrentBirdYPosition(circle)){
                     result = true;
                 }
                 
             }
         }
         
         
         return result;
     }
     
     public double getCurrentBirdYPosition(Circle circle){
        Scene scene2 = circle.getScene();
        Point2D sceneCoord = new Point2D(scene2.getX(), scene2.getY());
        Point2D circleCoord = circle.localToScene(0.0,0.0); 
        return(Math.round(circleCoord.getY() + 700));
     }
     public double getCurrentBirdXPosition(Circle circle){
        Scene scene2 = circle.getScene();
        Point2D sceneCoord = new Point2D(scene2.getX(), scene2.getY());
        Point2D circleCoord = circle.localToScene(0.0,0.0); 
        return(Math.round(circleCoord.getX()));
     }
     
     public double getCurrentPipeYPosition(Rectangle pipe){
        Scene scene2 = pipe.getScene();
        Point2D sceneCoord = new Point2D(scene2.getX(), scene2.getY());
        Point2D pipeCoord = pipe.localToScene(0.0,0.0); 
        return(Math.round(pipeCoord.getY() + 700));
     }
     
     public double getCurrentPipeXPosition(Rectangle pipe){
        Scene scene2 = pipe.getScene();
        Point2D sceneCoord = new Point2D(scene2.getX(), scene2.getY());
        Point2D pipeCoord = pipe.localToScene(0.0,0.0); 
        return(Math.round(pipeCoord.getX()));
     }

     
     
    
    

    @Override
    public void start(Stage stage) throws Exception {
        int stageWidth = 1000;
        int stageHeight = 1000;
        
         //testing bird as a circle
        Circle circle = new Circle(15);
        circle.setCenterX(50);
        circle.setCenterY(700);
        
        setPipes(pipes, stageWidth, stageHeight); 
        setPipeAnimations(pipeTransitions, stageWidth, pipes);
        setPipeGaps(pipeGaps, stageWidth);
        setPipeGapAnimations(pipeGapTransitions, stageWidth, pipeGaps);

        
      
        
       
        //playPipeGapAnimations(pipeGapTransitions);
  
    
        //this TranslateTransition animation pushes circle to the bottom when not jumping (acting as gravity)
        TranslateTransition gravity = new TranslateTransition();
        gravity.setByY(700);
        gravity.setDuration(Duration.seconds(2));
        gravity.setCycleCount(TranslateTransition.INDEFINITE);
        gravity.setNode(circle);
        
        //animation to jump bird up by 30 pixels each time space bar is pressed
        TranslateTransition jump = new TranslateTransition();
        jump.setByY(-30);
        jump.setDuration(Duration.millis(1));
        jump.setCycleCount(1);
        jump.setNode(circle);
       
        
        

        
        Group root = new Group();//rect, circle);
        
        for (int i =0; i < pipes.length; i++){
            root.getChildren().add(pipes[i]);
        }
        
        for (int i =0; i < pipeGaps.length; i++){
            root.getChildren().add(pipeGaps[i]);
        }
        
        
        root.getChildren().add(circle);
        Scene scene = new Scene(root, stageHeight, stageWidth, Color.SKYBLUE);
        stage.setScene(scene);
        
        
        //when space bar is pushed play jump animation moving bird up each time it is pressed
        //each time space bar is pushed stop previous gravity animation if playing and restart gravity on current jump
      
        
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE){
                    
                    
                    gravity.stop();
                    jump.play();
                    
                    //this just printing current Y coordinate of bird as jump animation is playing
                    if(jump.getStatus()==Status.RUNNING)
                        System.out.println(getCurrentBirdYPosition(circle));
                    
            }


        });
        //when spacebar is released stop jump animation and play gravity animation pushing bird back down
       scene.setOnKeyReleased(e -> {
           jump.stop();
           gravity.play();
           
       
           
       });
        
      
      
        stage.show();
        
        
    }
        
    }
    

