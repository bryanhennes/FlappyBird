/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

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
    

    int stageWidth = 1000;
    int stageHeight = 1000;
    Timeline tl;
    Scene scene;
    int W= 800;
    boolean gameOver = false;
    int score;
    Circle bird;
    Bird flappyBird;
    Group root;
    ArrayList<Rectangle> pipes;
    ImageView pressEnterImageView;
    Image pressEnter;
    Label scoreCounter;
    String counter;
    
    void addPipe(){
        int space =400;
        int width = 100;
        int height = 50+(int)(Math.random()*600);
        
        pipes.add(new Rectangle(W+width+(pipes.size()*200), stageHeight-height-140, width, height));
        pipes.add(new Rectangle(W+width+(pipes.size()-1)*200,0,width, stageHeight-height-space));
 
    }
    
  
     
     void hitsPipe(){
         
           for(int i =0; i < pipes.size(); i++){ 
            if(bird.getBoundsInParent().intersects(pipes.get(i).getBoundsInParent())){
                  gameOver = true;       
            }
            
           }
             
     
     }
     
  
     
     void startGame(){
         bird.setCenterX(50);
         bird.setCenterY(700);
         gameOver = false;
         score = 0;
         counter = String.valueOf(score);
         scoreCounter.setText("Score: " + counter);
         root.getChildren().removeAll(pipes);
         pipes.clear();
         
         int i = 0;
         while (i< 200){
             addPipe();
             i++;
                
         }
            tl.pause();
            
            
            
            //press enter key to start game
            scene.setOnKeyReleased(e -> {
                if(e.getCode() == KeyCode.ENTER){
                    root.getChildren().addAll(pipes);
                    tl.play();
                    root.getChildren().remove(pressEnterImageView);
                }
            });
            
    
     }   

         

    @Override
    public void start(Stage stage) throws Exception {
        
        flappyBird = new Bird(50, 700, 40); //create new Bird object
        bird = flappyBird.getBird(); //bird is a circle object returned from Bird class
        
        
        //set 'Press Enter to Play' image
        pressEnter = new Image(getClass().getResourceAsStream("pressEnter.png"));
        pressEnterImageView = new ImageView(pressEnter);
        pressEnterImageView.setFitHeight(300);
        pressEnterImageView.setFitWidth(400);
        pressEnterImageView.setX(stageWidth/2-200);
        pressEnterImageView.setY(50);
        
        
        //create score label at the top to keep track of current score
        scoreCounter = new Label();
        counter = String.valueOf(score);
        scoreCounter.setText("Score: " + counter);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30);
        scoreCounter.setAlignment(Pos.CENTER);
        scoreCounter.setPrefHeight(100);
        scoreCounter.setPrefWidth(300);
        scoreCounter.setFont(font);
        
        
      
       
        pipes = new ArrayList<Rectangle>();
        
        
     
    
        //this keyframe runs every 40 milliseconds..checks bird position every 40 milliseconds
        KeyFrame trackBird = new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
                    System.out.println(bird.getCenterY());
                    System.out.println(score);
                    
                   for(int i = 0; i < pipes.size(); i++){
                       Rectangle pipe = pipes.get(i);
                       pipe.setFill(Color.GREEN);
                       pipe.setX((pipe.getX()-5)); //every 40 milliseconds move pipe by 8 pixels to the left of screen
                       if(pipe.getY()==0&&bird.getCenterX()+bird.getRadius()>pipe.getX()+pipe.getWidth()/2-5&&bird.getCenterX()+bird.getRadius()<pipe.getX()+pipe.getWidth()/2+5){
                           score++;
                           counter = String.valueOf(score);

                           scoreCounter.setText("Score: " + counter);
                       }
                   }
                   
                   for(int i = 0; i < pipes.size(); i++){
                       Rectangle pipe = pipes.get(i);
                       if((pipe.getX()+pipe.getWidth())<0){
                           pipes.remove(i);
                       }
                   }
                   
                    
                });
        
         tl = new Timeline();
         tl.setCycleCount(Animation.INDEFINITE);
   
         
      
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), (ActionEvent event) -> {
                    if(bird.getCenterY() < 800)
                        flappyBird.fall(); //fall to the ground if not actively jumping and bird is not on the ground
                    
                    hitsPipe(); //check for bird hitting pipe
                    
                    //check for collision
                    if(gameOver){
                        root.getChildren().add(pressEnterImageView); //add 'Press Enter to Start' back if game has finished
                        startGame(); //if game is over restart original start() method
                    }
                    
                });
        
        
        
        root = new Group();     
        root.getChildren().add(bird); //add bird object to root
        root.getChildren().add(pressEnterImageView); //add 'Press Enter to Play' to screen
        root.getChildren().add(scoreCounter); //add score counter text at top of screen
        scene = new Scene(root, stageHeight, stageWidth, Color.SKYBLUE);
        stage.setScene(scene);
        
        tl.getKeyFrames().addAll(trackBird, keyFrame);
        
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE && !gameOver){
                    
                    flappyBird.jump(); //jump up 50 pixels each time space bar is pressed
                    
                    
            }

        });
        
  
        
        startGame();
        
  
        stage.show();
    
    
        
    }
        
    }

