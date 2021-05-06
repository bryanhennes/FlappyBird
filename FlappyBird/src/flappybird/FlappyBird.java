/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.io.File;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
    Image grass;
    ImagePattern grassIp;
    Rectangle ground;
    ImageView gameOverImageView;
    Image gameOverImg;
    Label highScoreTracker;
    String highScoreStr;
    int highScore;
    int previousScore;
    Text restart;
    ImageView titleImageView;
    Image titleImage;
   
    
    void addPipe(){
        int space =400;
        int width = 100;
        int height = 50+(int)(Math.random()*600);
        
        pipes.add(new Rectangle(W+width+(pipes.size()*200), stageHeight-height-140, width, height));
        pipes.add(new Rectangle(W+width+(pipes.size()-1)*200,0,width, stageHeight-height-space));
 
    }
    
  
     
     void hitsPipe(){
         
           for(int i =0; i < pipes.size(); i++){ 
            if(bird.getBoundsInParent().intersects(pipes.get(i).getBoundsInParent()) || bird.getCenterY() > 770 || bird.getCenterY() < 50){
                  gameOver = true;
                  
            }
            
           }
           
           if(gameOver){
               tl.pause();
           }
             
     
     }
     
     
     //run this method if game is not being launched for the first time
     void startNewGame(){
         flappyBird.fall();
         bird.setCenterX(50);
         bird.setCenterY(700);
         gameOver = false;
         tl.pause();
         highScore = previousScore;
         
         //check to see if current score was higher than prevous highscore
         if(score > highScore){
             highScore = score;
             previousScore = highScore;
         }
         highScoreStr = String.valueOf(highScore);
         highScoreTracker.setText("Best Score : " + highScoreStr); //display current highscore
         root.getChildren().add(highScoreTracker);
         
         
         
         scene.setOnKeyReleased(e -> {
                if(e.getCode() == KeyCode.ENTER){
                    startGame();
                    root.getChildren().remove(gameOverImageView);
                    root.getChildren().remove(highScoreTracker);
                    root.getChildren().remove(scoreCounter);
                    root.getChildren().remove(restart);
                    root.getChildren().add(pressEnterImageView);
                    root.getChildren().add(titleImageView);
                }
            });
         
     }
     
  
     
     void startGame(){
         bird.setCenterX(50);
         bird.setCenterY(700);
         gameOver = false;
         score = 0;
         highScore = 0;
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
                    root.getChildren().add(scoreCounter);
                    tl.play();
                    bird.setCenterY(400);
                    
                    root.getChildren().remove(pressEnterImageView);
                    root.getChildren().remove(titleImageView);
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
        pressEnterImageView.setFitHeight(200);
        pressEnterImageView.setFitWidth(300);
        pressEnterImageView.setX(stageWidth/2-150);
        pressEnterImageView.setY(200);
        
        //set game over image on screen when losing
        gameOverImg = new Image(getClass().getResourceAsStream("gameover.png"));
        gameOverImageView = new ImageView(gameOverImg);
        gameOverImageView.setFitHeight(300);
        gameOverImageView.setFitWidth(400);
        gameOverImageView.setX(stageWidth/2-200);
        gameOverImageView.setY(50);
        
        
        //create score label at the top to keep track of current score
        scoreCounter = new Label();
        counter = String.valueOf(score);
        scoreCounter.setText("Score: " + counter);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30);
        scoreCounter.setAlignment(Pos.CENTER);
        scoreCounter.setPrefHeight(100);
        scoreCounter.setPrefWidth(300);
        scoreCounter.setFont(font);
        
        //label to keep track of high score
        highScoreTracker = new Label();
        highScoreStr = String.valueOf(highScore);
        highScoreTracker.setText("Score: " + highScoreStr);
        Font font2 = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30);
        highScoreTracker.setAlignment(Pos.CENTER);
        highScoreTracker.setPrefHeight(100);
        highScoreTracker.setPrefWidth(300);
        highScoreTracker.setFont(font2);
        highScoreTracker.setLayoutY(100);
        
        
        restart = new Text();
        restart.setText("Press Enter to Play Again!");
        restart.setX(300);
        restart.setY(30);
        Font font3 = Font.font(40);
        restart.setFont(font3);
        
      
        
        titleImage = new Image(getClass().getResourceAsStream("flappy-bird-logo-png-transparent.png"));
        titleImageView = new ImageView(titleImage);
        titleImageView.setFitHeight(100);
        titleImageView.setFitWidth(500);
        titleImageView.setX(250);
        titleImageView.setY(70);
        
        
        
        //create ground
        grass = new Image(getClass().getResourceAsStream("grass.png"));
        grassIp = new ImagePattern(grass);
        ground = new Rectangle();
        ground.setX(0);
        ground.setY(700);
        ground.setWidth(1000);
        ground.setHeight(100);
        ground.setFill(grassIp);
        
     
        //pipes arrayList
        pipes = new ArrayList<Rectangle>();
        
        
     
    
        //this keyframe runs every 40 milliseconds..checks bird position every 40 milliseconds
        KeyFrame trackBird = new KeyFrame(Duration.millis(20), (ActionEvent event) -> {

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
                    
                    
                    if(gameOver){
                        root.getChildren().add(gameOverImageView);//add game over image to the screen when losing
                        root.getChildren().add(restart); //add 'Press Enter to Play Again' when losing
                        startNewGame(); //if game ends, run startNewGame method                            
                    }
                    
                });
        
        
        
        root = new Group();  
        
        root.getChildren().add(ground);
        root.getChildren().add(bird); //add bird object to root
        root.getChildren().add(pressEnterImageView); //add 'Press Enter to Play' to screen
        root.getChildren().add(titleImageView);

        
        
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

