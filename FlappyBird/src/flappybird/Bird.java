/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author bryanhennes
 */
public class Bird extends Circle {
    private ImageView imageView;
    private Image image;
    private ImagePattern ip;
    private int velocity;
    private Circle bird;
    
    public Bird(int centerX, int centerY, int radius){
      
        this.bird = new Circle();
        this.imageView = new ImageView();
        this.image = new Image(getClass().getResourceAsStream("flappy_bird.png"));
        this.ip = new ImagePattern(this.image);
        this.bird.setCenterX(centerX);
        this.bird.setCenterY(centerY);
        this.bird.setRadius(radius);
        this.bird.setFill(this.ip);
        this.velocity = 50;
    }
    
    
    public Circle getBird(){
        return bird;
        
    }
    
    public void fall(){
        bird.setCenterY(bird.getCenterY()+12);
    }
    
    public void jump(){
        bird.setCenterY(bird.getCenterY()-60);
    }
    
}
