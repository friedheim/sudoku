/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.sudoku.solver;

import java.util.Arrays;
import java.util.List;

/**
 * Class for representation of a sudoku-field filled with an value. 
 * The values are 1-based indexes.
 * @author friedheim
 */
public class FieldSol {

    private final byte m_colNr;
    private final byte m_rowNr;
    private final byte m_cand;
    private final byte m_n;
    
    private final List<Byte> POSSIBLE_N_VALUES = Arrays.asList((byte)4,(byte)9,(byte)16); //new byte[]{4,9,16};
    
    public FieldSol(byte n, byte cand, byte rowNr, byte colNr)
    {
        _checkConstrParameters(n, cand, rowNr, colNr);
        m_n = n;
        m_cand = cand;
        m_rowNr = rowNr;
        m_colNr = colNr;
    }
    
    private void _checkConstrParameters(byte n, byte cand, byte rowNr, byte colNr)
    {
        if(!POSSIBLE_N_VALUES.contains(n))
        {
            throw new IllegalArgumentException("Value n can only be one of: " + POSSIBLE_N_VALUES + " but is: " + n);
        }
        
        if(cand <= 0 || cand > n)
        {
            throw new IllegalArgumentException("Value cand is invalid: " + cand);
        }
        
        if(rowNr <= 0 || rowNr > n)
        {
            throw new IllegalArgumentException("Value rowNr is invalid: " + rowNr);
        }
        
        if(colNr <= 0 || colNr > n)
        {
            throw new IllegalArgumentException("Value colNr is invalid: " + colNr);
        }
        
        
    }

    public byte getColNr() 
    {
        return m_colNr;
    }

    public byte getRowNr() 
    {
        return m_rowNr;
    }

    public byte getCand() 
    {
        return m_cand;
    }
    
    public byte getRegion()
    {
        int colRegion = (getColNr()-1)/getNRoot();
        int rowRegion = (getRowNr()-1)/getNRoot();
        return (byte)((colRegion + (rowRegion)*getNRoot()) + 1);
    }
    
    private byte getNRoot()
    {
        return (byte)Math.sqrt(m_n);
    }
    
    public byte getN() {
        return m_n;
    }
    
    @Override
    public String toString()
    {
        return "(" + m_cand + "," + m_rowNr + "," + m_colNr + ")";
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.m_colNr;
        hash = 97 * hash + this.m_rowNr;
        hash = 97 * hash + this.m_cand;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FieldSol other = (FieldSol) obj;
        if (this.m_colNr != other.m_colNr) {
            return false;
        }
        if (this.m_rowNr != other.m_rowNr) {
            return false;
        }
        if (this.m_cand != other.m_cand) {
            return false;
        }
        return true;
    }
}
