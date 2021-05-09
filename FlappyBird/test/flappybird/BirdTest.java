/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import javafx.scene.shape.Circle;
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
public class BirdTest {
    
    public BirdTest() {
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
     * Test of getBird method, of class Bird.
     */
    @Test
    public void testGetBird() {
        System.out.println("getBird");
        Bird instance = null;
        Circle expResult = null;
        Circle result = instance.getBird();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fall method, of class Bird.
     */
    @Test
    public void testFall() {
        System.out.println("fall");
        Bird instance = null;
        instance.fall();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jump method, of class Bird.
     */
    @Test
    public void testJump() {
        System.out.println("jump");
        Bird instance = null;
        instance.jump();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
