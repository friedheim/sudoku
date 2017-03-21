/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.sudoku.solver;

import de.friedheim.dlx.DLXField;

/**
 *
 * @author friedheim
 */
public class SudokuField 
{
    private final int m_n;
    private final byte[][] m_initValues;
    private byte[][] m_filledValues;
    
    public SudokuField(int n, byte[][] initValues)
    {
        m_n = n;
        m_initValues = initValues;
        m_filledValues = initValues;
        __initialize();
    }
    
    public void solve()
    {
        DLXField x = new DLXField(5, 4, m_initValues);
    }
    
    private void __initialize()
    {
        
    }
}
