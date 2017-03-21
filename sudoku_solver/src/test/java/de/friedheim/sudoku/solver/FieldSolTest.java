/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.sudoku.solver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 *
 * @author friedheim
 */
public class FieldSolTest {
    
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetRegionMin4() {
        System.out.println("getRegion");
        FieldSol instance = new FieldSol((byte)4, (byte)1, (byte)1, (byte)1);
        byte expResult = 1;
        byte result = instance.getRegion();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRegionMax4() {
        System.out.println("getRegion");
        FieldSol instance = new FieldSol((byte)4, (byte)3, (byte)4, (byte)4);
        byte expResult = 4;
        byte result = instance.getRegion();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRegionMiddle4() {
        System.out.println("getRegionMiddle");
        FieldSol instance = new FieldSol((byte)4, (byte)2, (byte)2, (byte)3);
        byte expResult = 2;
        byte result = instance.getRegion();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testThrowsErrorInvalNValue()
    {
        thrown.expect(IllegalArgumentException.class);
        new FieldSol((byte)5, (byte)2, (byte)2, (byte)2);
    }
        
    @Test
    public void testThrowsErrorHighCandValue()
    {
        thrown.expect(IllegalArgumentException.class);
        new FieldSol((byte)4, (byte)5, (byte)2, (byte)2);
    }
    
    @Test
    public void testThrowsErrorLowCandValue()
    {
        thrown.expect(IllegalArgumentException.class);
        new FieldSol((byte)4, (byte)0, (byte)2, (byte)2);
    }
            
    @Test
    public void testThrowsErrorHighRowValue()
    {
        thrown.expect(IllegalArgumentException.class);
        new FieldSol((byte)4, (byte)4, (byte)5, (byte)1);
    }
    
    @Test
    public void testThrowsErrorLowRowValue()
    {
        thrown.expect(IllegalArgumentException.class);
        new FieldSol((byte)4, (byte)1, (byte)0, (byte)4);
    }
                
    @Test
    public void testThrowsErrorHighColValue()
    {
        thrown.expect(IllegalArgumentException.class);
        new FieldSol((byte)4, (byte)4, (byte)1, (byte)5);
    }
    
    @Test
    public void testThrowsErrorLowColValue()
    {
        thrown.expect(IllegalArgumentException.class);
        new FieldSol((byte)4, (byte)1, (byte)4, (byte)0);
    }
    
    @Test
    public void testThrowsNoErrorMinValues()
    {
        new FieldSol((byte)4, (byte)1, (byte)1, (byte)1);
    }
    
    @Test
    public void testThrowsNoErrorMaxValues()
    {
        new FieldSol((byte)4, (byte)4, (byte)4, (byte)4);
    }
}
