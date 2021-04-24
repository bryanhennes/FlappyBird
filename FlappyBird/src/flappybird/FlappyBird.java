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
import javafx.animation.PauseTransition;

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
        
        return((int)(Math.random()* 500) + 100);
    }
    
   

    Rectangle[] pipes = new Rectangle[30];
    Rectangle[] pipeGaps = new Rectangle[30];
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
        int gapHeight = 100;
        for (int i =0; i < rects.length; i++){
            rects[i] = new Rectangle();
            rects[i].setX(width + 42);
            rects[i].setY((int)(Math.random()* 800) + 50); //whyyy when I run this it doubles or triples pipe gaps?????
            //rects[i].setY(400);
            rects[i].setStrokeWidth(0);
            rects[i].setStroke(Color.SKYBLUE);
            rects[i].setWidth(60);
            rects[i].setHeight(100);//(int)(Math.random()* 200) + 50);
            rects[i].setFill(Color.SKYBLUE);
        }
    }
    
    public void setPipeAnimations(TranslateTransition[] animations, int width, Rectangle[] rects){
        for (int i = 0, delayTime= 1; i < rects.length; i++, delayTime+=2){
            animations[i] = new TranslateTransition();
            animations[i].setByX(-(width + 150));
            animations[i].setDuration(Duration.seconds(6));//5 seconds to move across the screen
            animations[i].setCycleCount(TranslateTransition.INDEFINITE);//repeat indefinitely
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
            animations[i].setCycleCount(TranslateTransition.INDEFINITE);//repeat indefinitely
            animations[i].setNode(rects[i]); //sets animation to move first rect object  
            animations[i].setDelay(Duration.seconds(delayTime));
            animations[i].play();
        }
        
    }
    
    

    @Override
    public void start(Stage stage) throws Exception {
        int stageWidth = 1000;
        int stageHeight = 1000;
        //int gapHeight = 100;
        int jumpHeight = 50;
        
       
        setPipes(pipes, stageWidth, stageHeight); //set 30 different pipes
        setPipeAnimations(pipeTransitions, stageWidth, pipes);
        setPipeGaps(pipeGaps, stageWidth);
        setPipeAnimations(pipeGapTransitions, stageWidth, pipeGaps);

        
        //this rect object is the one pipe moving across the screen
        /*Rectangle rect = new Rectangle();
        rect.setX(stageWidth);
        rect.setY(0);
        rect.setWidth(40);
        rect.setHeight(stageHeight);
        rect.setFill(Color.GREEN);*/
        
        //this rect is the gap on the main pipe that the bird must jump through
       /* Rectangle rect2 = new Rectangle(); //make a gap in a pipe
        rect2.setX(stageWidth);
        rect2.setY(getRandPlacement());
        rect2.setStrokeWidth(0);
        rect2.setStroke(Color.SKYBLUE);
        rect2.setWidth(40);
        rect2.setHeight(gapHeight);
        rect2.setFill(Color.SKYBLUE);*/
        
        //testing bird as a circle
        Circle circle = new Circle(15);
        circle.setCenterX(50);
        circle.setCenterY(700);
                
        
   
        //this TranslateTransition animates the green pipe from the right to the left of the screen
        /*TranslateTransition transition = new TranslateTransition();
        transition.setByX(-(stageWidth + 100));
        transition.setDuration(Duration.seconds(5));//5 seconds to move across the screen
        transition.setCycleCount(TranslateTransition.INDEFINITE);//repeat indefinitely
        transition.setNode(rect); //sets animation to move first rect object
        transition.play();*/
        
        
        //set array of animations for each pipe
        /*for(int i =0; i < pipeTransitions.length-1; i++){
            
            pipeTransitions[i].play();
            //pipeTransitions[i+1].setDelay(Duration.seconds(1));
        }*/
        
        
        
        /*TranslateTransition transitionTest = new TranslateTransition();
        transitionTest.setByX(-(stageWidth + 100));
        transitionTest.setDuration(Duration.seconds(5));//5 seconds to move across the screen
        transitionTest.setCycleCount(TranslateTransition.INDEFINITE);//repeat indefinitely
        transitionTest.setNode(pipes[5]); //sets animation to move first rect object
        transitionTest.setDelay(Duration.seconds(1));
        transitionTest.play();*/
     
        
        
        
        
        //this TranslateTransition animates pipe gap on top of green pipe animation
        /*TranslateTransition pipeGap = new TranslateTransition();
        pipeGap.setByX(-(stageWidth + 100));
        pipeGap.setDuration(Duration.seconds(5));
        pipeGap.setCycleCount(TranslateTransition.INDEFINITE);
        pipeGap.setNode(rect2);
        pipeGap.play();*/
        
        //this TranslateTransition animation pushes circle to the bottom when not jumping (acting as gravity)
        TranslateTransition gravity = new TranslateTransition();
        gravity.setByY(700);
        gravity.setDuration(Duration.seconds(4));
        gravity.setCycleCount(TranslateTransition.INDEFINITE);
        gravity.setNode(circle);
        //gravity.setDelay(Duration.millis(180));
        
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
            }
                    System.out.println(circle.getLocalToSceneTransform());


        });
        //when spacebar is released stop jump animation and play gravity animation pushing bird back down
       scene.setOnKeyReleased(e -> {
           jump.stop();
           gravity.play();
       });
      
        stage.show();
        
        
    }
        
    }
    

