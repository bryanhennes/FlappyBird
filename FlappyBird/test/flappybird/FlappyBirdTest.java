/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bryanhennes
 */
public class FlappyBirdTest {
    
    public FlappyBirdTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class FlappyBird.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FlappyBird.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPipe method, of class FlappyBird.
     */
    @Test
    public void testAddPipe() {
        System.out.println("addPipe");
        FlappyBird instance = new FlappyBird();
        instance.addPipe();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hitsPipe method, of class FlappyBird.
     */
    @Test
    public void testHitsPipe() {
        System.out.println("hitsPipe");
        FlappyBird instance = new FlappyBird();
        instance.hitsPipe();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startNewGame method, of class FlappyBird.
     */
    @Test
    public void testStartNewGame() {
        System.out.println("startNewGame");
        FlappyBird instance = new FlappyBird();
        instance.startNewGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startGame method, of class FlappyBird.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        FlappyBird instance = new FlappyBird();
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class FlappyBird.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        Stage stage = null;
        FlappyBird instance = new FlappyBird();
        instance.start(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
