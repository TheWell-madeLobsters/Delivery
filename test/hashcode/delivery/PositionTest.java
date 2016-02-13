/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode.delivery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Terrinoni
 */
public class PositionTest {
    
    public PositionTest() {
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
     * Test of getRow method, of class Position.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Position instance = new Position(2, 3);
        int expResult = 2;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRow method, of class Position.
     */
    @Test
    public void testSetRow() {
        System.out.println("setRow");
        int row = 4;
        Position instance = new Position();
        instance.setRow(row);
        assertEquals(row, instance.getRow());
    }

    /**
     * Test of getColumn method, of class Position.
     */
    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        Position instance = new Position(2, 0);
        int expResult = 0;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    /**
     * Test of setColumn method, of class Position.
     */
    @Test
    public void testSetColumn() {
        System.out.println("setColumn");
        int column = 5;
        Position instance = new Position();
        instance.setColumn(column);
        assertEquals(column, instance.getColumn());
    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Position position = new Position(5, 3);
        Position instance = new Position(5, 3);
        boolean expResult = true;
        boolean result = instance.equals(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEqualsFalse1() {
        System.out.println("equals");
        Position position = new Position(5, 2);
        Position instance = new Position(5, 3);
        boolean expResult = false;
        boolean result = instance.equals(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEqualsFalse2() {
        System.out.println("equals");
        Position position = new Position(5, 3);
        Position instance = new Position(6, 3);
        boolean expResult = false;
        boolean result = instance.equals(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEqualsFalse3() {
        System.out.println("equals");
        Position position = new Position(5, 3);
        Position instance = new Position(6, 6);
        boolean expResult = false;
        boolean result = instance.equals(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistance method, of class Position.
     */
    @Test
    public void testGetDistance1() {
        System.out.println("getDistance");
        Position position = new Position(5, 3);
        Position instance = new Position(5, 3);
        int expResult = 0;
        int result = instance.getDistance(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistance method, of class Position.
     */
    @Test
    public void testGetDistance2() {
        System.out.println("getDistance");
        Position position = new Position(6, 5);
        Position instance = new Position(7, 0);
        int expResult = 6;
        int result = instance.getDistance(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistance method, of class Position.
     */
    @Test
    public void testGetDistance3() {
        System.out.println("getDistance");
        Position position = new Position(6, 0);
        Position instance = new Position(7, 0);
        int expResult = 1;
        int result = instance.getDistance(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistance method, of class Position.
     */
    @Test
    public void testGetDistanceStatic1() {
        System.out.println("getDistance");
        Position a = new Position(5, 3);
        Position b = new Position(5, 3);
        int expResult = 0;
        int result = Position.getDistance(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistance method, of class Position.
     */
    @Test
    public void testGetDistanceStatic2() {
        System.out.println("getDistance");
        Position a = new Position(6, 5);
        Position b = new Position(7, 0);
        int expResult = 6;
        int result = Position.getDistance(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistance method, of class Position.
     */
    @Test
    public void testGetDistanceStatic3() {
        System.out.println("getDistance");
        Position a = new Position(6, 0);
        Position b = new Position(7, 0);
        int expResult = 1;
        int result = Position.getDistance(a, b);
        assertEquals(expResult, result);
    }
    
}
