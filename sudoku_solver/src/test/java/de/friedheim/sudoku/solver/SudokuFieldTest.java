/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.sudoku.solver;

import org.junit.Test;

/**
 *
 * @author friedheim
 */
public class SudokuFieldTest {
//    @Test
//    public void testInitialize()
//    {
//        byte[][] sudoku4 = new byte[][]{
//            {0,0,0,1},
//            {0,0,2,0},
//            {0,4,0,0},
//            {3,0,4,0}
//        };
//        SudokuField sf = new SudokuField((byte)4,sudoku4);
//        sf.solve();
//    }
    
    @Test
    public void testSudoku9_1()
    {
        //Sudoku aus Heft 6.3
        byte[][] sudoku9_1 = new byte[][]
        {
            {0,0,0,0,0,0,4,0,7},
            {7,0,0,9,6,4,0,1,0},
            {4,0,1,0,8,0,6,0,0},
            {0,0,9,6,2,0,7,3,1},
            {0,6,0,0,0,0,0,2,0},
            {5,7,2,0,1,3,9,0,0},
            {0,0,7,0,3,0,1,0,4},
            {0,9,0,4,7,1,0,0,3},
            {3,0,4,0,0,0,0,0,0}
        };        
        
        byte[][] expected = new byte[][]
        {
            {9,3,6,1,5,2,4,8,7},
            {7,8,5,9,6,4,3,1,2},
            {4,2,1,3,8,7,6,5,9},
            {8,4,9,6,2,5,7,3,1},
            {1,6,3,7,4,9,8,2,5},
            {5,7,2,8,1,3,9,4,6},
            {6,5,7,2,3,8,1,9,4},
            {2,9,8,4,7,1,5,6,3},
            {3,1,4,5,9,6,2,7,8}
        };
        SudokuField sf = new SudokuField((byte)9,sudoku9_1);
        byte[][] solution = sf.solve();
        Assertx.assertMatrixEquals( expected, solution);
    }
}
