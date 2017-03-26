/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.dlx;

/**
 *
 * @author friedheim
 */
class ColHNode extends Node
{
    private int m_colCap = 0;
    
    ColHNode(int colNr, int rowNr)
    {
        super(colNr, rowNr);
    }
    
    void incrementColCap()
    {
       m_colCap++; 
    }
    
    void decrementColCap()
    {
        m_colCap--;
    }
    
    int getColCap()
    {
        return m_colCap;
    }
}
