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
    @Test
    public void testInitialize()
    {
        byte[][] sudoku4 = new byte[][]{
            {0,0,0,1},
            {0,0,2,0},
            {0,4,0,0},
            {3,0,4,0}
        };
        SudokuField sf = new SudokuField((byte)4,sudoku4);
        sf.solve();
    }
}
