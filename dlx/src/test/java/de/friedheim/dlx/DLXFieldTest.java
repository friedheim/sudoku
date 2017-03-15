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

/**
 *
 * @author friedheim
 */
public class DLXFieldTest 
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    
//    private final byte[][] values= new byte[][]{
//        new byte[]{1,1,0,0,0},
//        new byte[]{1,1,1,0,0},
//        new byte[]{1,1,0,1,0},
//        new byte[]{0,0,1,0,1}
//    };
    
    private final byte[][] values= new byte[][]{
        new byte[]{0,0,1,0,1,1,0},
        new byte[]{1,0,0,1,0,0,1},
        new byte[]{0,1,1,0,0,1,0},
        new byte[]{1,0,0,1,0,0,0},
        new byte[]{0,1,0,0,0,0,1},
        new byte[]{0,0,0,1,1,0,1}
    };
    
    private DLXField out;

    @Before
    public void setUp()
    {
        out = new DLXField(values[0].length,values.length,values);
    }
    
    @Test //(expected=IllegalArgumentException.class)
    public void testInitializeCorrectly() //throws Exception
    {
//        thrown.expect(IllegalArgumentException.class);
        new DLXField(values[0].length,values.length,values);
    }
    
        @Test 
    public void testInitializeThrowsExc()
    {
        thrown.expect(IllegalArgumentException.class);
        new DLXField(values[0].length-1,values.length,values);
    }
    
    @Test
    public void testToByteMatrix()
    {
        for(byte[] row: out.toMatrix())
        {
            for(byte el: row)
            {
                System.out.print(el + ",");
            }
            System.out.println("");
        }
        Assert.assertTrue("matrixes are not Equal: \n" + toString(out.toMatrix()) + " not equals \n" + toString(values), compareMatrices(out.toMatrix(),values) );
    }
    
    @Test
    public void testGetRowCount()
    {
        Assert.assertEquals(out.getCurrRowCount(), values.length);
    }
    
        @Test
    public void testGetColCount()
    {
        Assert.assertEquals(out.getCurrColCount(), values[0].length);
    }
    
//    @Test
//    public void testCoverColHeader()
//    {
//        byte[][] expected = new byte[][]{
//            new byte[]{1,1,0,0},
//            new byte[]{1,1,1,0}
//        };
//        out.cover(out.getColHeaderNode(2));
//        Assert.assertTrue("testCoverColHeader produces error: \n" + toString(out.toMatrix()) + " not equals \n" + toString(expected), compareMatrices(out.toMatrix(),expected) );
//    }
    
    @Test
    public void testSolve()
    {
        out.solve();
    }
    
    @Test
    public void testCoverUncover()
    {
        out.cover(out.getColHeaderNode(2));
        out.uncover(out.getColHeaderNode(2));
        Assert.assertTrue("not equal: \n" + toString(out.toMatrix()) + " not equals \n" + toString(values), compareMatrices(out.toMatrix(),values) ); 
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
