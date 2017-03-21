/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.sudoku.solver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author friedheim
 */
public class FieldSolTest {
    
//    public FieldSolTest() {
//    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRegion method, of class FieldSol.
     */
    @Test
    public void testGetRegionMin4() {
        System.out.println("getRegion");
        FieldSol instance = new FieldSol((byte)4, (byte)1, (byte)1, (byte)1);
        byte expResult = 1;
        byte result = instance.getRegion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    /**
     * Test of getRegion method, of class FieldSol.
     */
    @Test
    public void testGetRegionMax4() {
        System.out.println("getRegion");
        FieldSol instance = new FieldSol((byte)4, (byte)3, (byte)4, (byte)4);
        byte expResult = 4;
        byte result = instance.getRegion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    /**
     * Test of getRegion method, of class FieldSol.
     */
    @Test
    public void testGetRegionMiddle4() {
        System.out.println("getRegionMiddle");
        FieldSol instance = new FieldSol((byte)4, (byte)2, (byte)2, (byte)3);
        byte expResult = 2;
        byte result = instance.getRegion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
