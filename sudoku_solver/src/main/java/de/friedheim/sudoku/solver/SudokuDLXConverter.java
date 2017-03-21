/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.sudoku.solver;

import de.friedheim.dlx.DLXField;
import de.friedheim.dlx.Node;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author friedheim
 */
public class SudokuDLXConverter {
    private final byte m_n;
    
    private final byte[][] m_initialDLX;
    
    private FieldSol[] m_fieldSols;
    
    
    public static void main(String[] args) {
        SudokuDLXConverter conv = new SudokuDLXConverter((byte)4);
        DLXField dlx = conv.getInitialDLX();
        System.out.println("currRow: " + dlx.getCurrRowCount() + " col: " + dlx.getCurrColCount());
        dlx.setInitial(12);
        System.out.println("currRow: " + dlx.getCurrRowCount() + " col: " + dlx.getCurrColCount());
        System.out.println(dlx.toString());
        dlx.setInitial(25);
        dlx.setInitial(50);
        dlx.setInitial(59);
        List<Node> solution = dlx.solve();
        List<List<Node>> fs = dlx.getFinalSolutions();
        for(List<Node> nodeL:fs)
        {
            System.out.println("nodel: " + nodeL);
            for(Node n: nodeL)
            {
                System.out.println("sol: " + conv.getFieldSol(n) );
            }
        }
    }
    
    public SudokuDLXConverter(byte n)
    {
        m_n = n;
        m_initialDLX = createInitialDLXArr();
    }
    
    public DLXField convertSudokuToDLX()
    {
        return null;
    }
    
    private byte[][] createInitialDLXArr()
    {
        byte[][] dlxArr = new byte[getDLXRowCount()][]; //createCeroFilledArray(getDLXRowCount(), getDLXColCount());
        m_fieldSols = new FieldSol[getDLXRowCount()];
        //loop over rows
        int count = 0;
        for(byte row = 1; row <= m_n; row++)
        {
            //loop over columns
            for(byte col = 1; col <= m_n; col++)
            {
                //loop over numbers
                for(byte cand = 1; cand <= m_n; cand++ ) 
                {
                    FieldSol sol = new FieldSol(m_n,cand, row, col);
                    m_fieldSols[count] = sol;
                    dlxArr[count]= createDLXRow(sol);
                    System.out.println("("+cand + "," + row + "," + col + ")");
                    count++;
                }
            }
        }
        System.out.println("rows: " + getDLXRowCount() + " cols: " + getDLXColCount());
        return dlxArr;
    }
    
    DLXField getInitialDLX()
    {
        DLXField field = new DLXField(getDLXColCount(), getDLXRowCount(), m_initialDLX);
        System.out.println(field.toString());
        return field;
    }
    
    private int getDLXRowCount()
    {
        return (int)Math.pow(m_n,3);
    }
    
    private int getDLXColCount()
    {
        return (int)Math.pow(m_n, 2) * 4;
    }
    
    private byte[] createDLXRow(FieldSol solInput)
    {
        byte[] result = new byte[getDLXColCount()];
        Arrays.fill(result, (byte)0);
        
        //fill 1 for field used (first part)
        int index1 = (solInput.getColNr()-1)+(solInput.getRowNr()-1)*m_n;
        result [index1] = 1;
        
        //fill 1 for nr in row
        int offSet2 = (int)(1 * Math.pow(m_n, 2));
        int index2 = (solInput.getRowNr()-1)*m_n+(solInput.getCand()-1);
        result [offSet2+index2] = 1;
        
        //fill 1 for nr in col
        int offSet3 = (int)(2 * Math.pow(m_n, 2));
        int index3 = (solInput.getColNr()-1)*m_n+(solInput.getCand()-1);
        result[offSet3+index3] = 1;

        //fill 1 for nr in region
        int offSet4 = (int)(3 * Math.pow(m_n, 2));
        int index4 = (solInput.getRegion()-1)*m_n+(solInput.getCand()-1);
        result[offSet4+index4] = 1;
        
        return result;
    }
    
    public int getDLXRowIndex(FieldSol sol)
    {
        byte[] dlxRowArr = createDLXRow(sol);
        return getDLXRowIndex(dlxRowArr);
    }
    
    private int getDLXRowIndex(byte[] dlxRowArr)
    {
        int count = 0;
        for(byte[] row : m_initialDLX)
        {
            if(Arrays.equals(row, dlxRowArr))
            {
                return count;
            }
            count++;
        }
        throw new Error("searching for dlx-row, that does not exist");
    }
    
    public FieldSol getFieldSol(Node n)
    {
        return getFieldSol(n.getRowNr());
    }
    
    public FieldSol getFieldSol(int row)
    {
        return m_fieldSols[row];
    }
}
