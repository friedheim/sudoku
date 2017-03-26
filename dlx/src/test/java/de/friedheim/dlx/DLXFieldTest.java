/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.dlx;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 *
 * @author friedheim
 */
public class DLXFieldTest 
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    
    private final byte[][] values1= new byte[][]{
        new byte[]{0,0,1,0,1,1,0},
        new byte[]{1,0,0,1,0,0,1},
        new byte[]{0,1,1,0,0,1,0},
        new byte[]{1,0,0,1,0,0,0},
        new byte[]{0,1,0,0,0,0,1},
        new byte[]{0,0,0,1,1,0,1}
    };
    
    private final byte[][] values2 = new byte[][]{
        new byte[]{1,1,0,0,0},
        new byte[]{1,1,1,0,0},
        new byte[]{1,1,0,1,0},
        new byte[]{0,0,1,0,1}
    };
    

    
    private DLXField out1;
    private DLXField out2;

    @Before
    public void setUp()
    {
        out1 = new DLXField(values1[0].length,values1.length,values1);
        out2 = new DLXField(values2[0].length,values2.length,values2);
    }
    
    @Test 
    public void testInitializeCorrectly() 
    {
        new DLXField(values1[0].length,values1.length,values1);
    }
    
    @Test 
    public void testInitializeThrowsExc()
    {
        thrown.expect(IllegalArgumentException.class);
        new DLXField(values1[0].length-1,values1.length,values1);
    }
    
    @Test 
    public void testInitializeColCaps()
    {
        assertEquals("initialization colCaps invalid", 2 , out1.getColHeaderNode(0).getColCap());
        assertEquals("initialization colCaps invalid", 3 , out1.getColHeaderNode(3).getColCap());
        assertEquals("initialization colCaps invalid", 3 , out1.getColHeaderNode(6).getColCap());
    }
    
    @Test
    public void testToByteMatrix()
    {
        for(byte[] row: out1.toMatrix())
        {
            for(byte el: row)
            {
                System.out.print(el + ",");
            }
            System.out.println("");
        }
        Assert.assertTrue("matrixes are not Equal: \n" + toString(out1.toMatrix()) + " not equals \n" + toString(values1), compareMatrices(out1.toMatrix(),values1) );
    }
    
    @Test
    public void testGetRowCount()
    {
        Assert.assertEquals(out1.getCurrRowCount(), values1.length);
    }
    
        @Test
    public void testGetColCount()
    {
        Assert.assertEquals(out1.getCurrColCount(), values1[0].length);
    }
    
    @Test
    public void testCoverColHeader()
    {
        byte[][] expected = new byte[][]{
            new byte[]{1,1,0,0},
            new byte[]{1,1,1,0}
        };
        out2.cover(out2.getColHeaderNode(2));
        assertTrue("testCoverColHeader produces error: \n" + toString(out2.toMatrix()) + " not equals \n" + toString(expected), compareMatrices(out2.toMatrix(),expected) );
    }
    
    @Test
    public void testCoverUncover()
    {
        out1.cover(out1.getColHeaderNode(2));
        out1.uncover(out1.getColHeaderNode(2));
        assertTrue("not equal: \n" + toString(out1.toMatrix()) + " not equals \n" + toString(values1), compareMatrices(out1.toMatrix(),values1) ); 
        
                
    }
    
    @Test
    public void testCoverColCaps()
    {
//        byte[][] expected = new byte[][]{
//            new byte[]{1,1,0,0},
//            new byte[]{1,1,1,0}
//        };
        out2.cover(out2.getColHeaderNode(2));
        assertEquals(2, out2.getColHeaderNode(0).getColCap());
        assertEquals(1, out2.getColHeaderNode(3).getColCap());
    }
    
    @Test
    public void testCoverUncoverColCaps()
    {
        out2.cover(out2.getColHeaderNode(2));
        out2.uncover(out2.getColHeaderNode(2));
        assertEquals(3, out2.getColHeaderNode(0).getColCap());
        assertEquals(1, out2.getColHeaderNode(3).getColCap());
    }
    
    private boolean compareMatrices(byte[][] matrix1, byte[][] matrix2)
    {
        if(matrix1.length != matrix2.length){
            return false;
        }
        for(int i = 0; i < matrix1.length; i++){
            if (!Arrays.equals(matrix1[i], matrix2[i])){
                return false;
            }
        }
        return true;
    }
    
    private String toString(byte[][] matrix)
    {
        String result = "";
        for(byte[] row: matrix){
            for(byte cell: row){
                result += cell + ",";
            }
            result += "\n";
        }
        return result;
    }
}
