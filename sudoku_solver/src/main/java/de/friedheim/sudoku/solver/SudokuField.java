/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.sudoku.solver;

import de.friedheim.dlx.DLXField;
import de.friedheim.dlx.Node;
import java.util.List;

/**
 *
 * @author friedheim
 */
public class SudokuField 
{
    private final byte m_n;
    private final byte[][] m_initValues;
    private final byte[][] m_filledValues;
    
    public static void main(String[] args)
    {
        byte[][] sudoku4 = new byte[][]
        {
            {0,0,0,1},
            {0,0,2,0},
            {0,4,0,0},
            {3,0,4,0}
        };
        byte[][] sudokuhardest_1 = new byte[][]
        {
            {0,0,0,0,7,0,1,0,0},
            {1,0,2,8,3,0,0,4,7},
            {8,0,0,0,0,0,0,6,0},
            {0,0,0,0,0,0,0,0,0},
            {0,5,0,0,0,7,9,0,4},
            {0,0,6,0,5,0,0,1,0},
            {0,0,8,9,4,3,0,0,0},
            {3,0,0,0,0,0,0,0,0},
            {5,4,0,7,2,0,0,0,3}
        };
        
        byte[][] sudokuhardest_2 = new byte[][]
        {
            {0,0,2,4,0,8,3,0,0},
            {0,0,0,0,0,0,0,0,0},
            {5,0,9,0,0,0,7,0,1},
            {9,0,0,0,6,0,0,0,5},
            {0,0,0,8,0,3,0,0,0},
            {1,0,0,0,4,0,0,0,3},
            {4,0,7,0,0,0,5,0,6},
            {0,0,0,0,0,0,0,0,0},
            {0,0,8,7,0,4,1,0,0}
        };
        
        //Sudoku aus Heft 6.3
        byte[][] sudoku9 = new byte[][]
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
        
        //Sudoku aus Heft 6.29
        byte[][]sudoku9_2 = new byte[][]
        {
            {2,0,0,0,9,0,0,0,0},
            {0,9,0,0,0,0,2,0,0},
            {0,7,3,8,0,0,0,0,4},
            {0,0,0,1,2,0,0,0,8},
            {4,0,7,0,0,0,5,0,6},
            {8,0,0,0,4,6,0,0,0},
            {3,0,0,0,0,8,7,9,0},
            {0,0,2,0,0,0,0,6,0},
            {0,0,0,0,7,0,0,0,1}
            
        };
        
        byte[][] sudoku16_normal = new byte[][]
        {
            {11,0,0,0,0,16,4,0,14,0,0,0,0,0,6,13},
            {0,0,0,3,0,1,9,0,0,10,0,0,0,5,0,0},
            {0,0,0,15,7,5,0,0,0,0,0,13,3,2,14,12},
            {6,1,9,13,14,2,0,0,0,7,0,11,15,4,16,0},
            {8,0,7,0,0,0,0,0,12,0,0,0,0,6,13,0},
            {0,12,14,0,0,6,0,9,0,15,0,4,0,0,0,8},
            {0,0,16,0,0,0,5,8,0,0,0,0,0,14,0,3},
            {13,6,1,9,0,0,0,3,0,0,5,0,0,0,0,0},
            {0,0,0,7,0,15,0,16,0,2,12,0,6,13,0,1},
            {9,0,6,0,0,0,0,2,11,8,7,0,16,0,15,0},
            {0,0,10,0,8,0,0,0,0,9,6,0,0,12,0,0},
            {0,0,12,14,0,0,0,1,0,4,0,16,7,0,0,5},
            {0,5,8,0,16,4,0,0,2,14,0,0,0,0,0,0},
            {0,0,0,12,0,9,13,6,0,0,15,0,11,0,5,0},
            {16,4,0,0,5,8,0,7,0,0,13,6,12,3,0,0},
            {0,0,0,0,0,0,12,0,8,0,11,0,10,15,0,0}
        };
        
        System.out.println("hallo Friedheim");
        SudokuField sf = new SudokuField((byte)9,sudokuhardest_1);
        sf.solve();
        sf.printSolution();
    }
    
    public SudokuField(byte n, byte[][] initValues)
    {
        m_n = n;
        m_initValues = initValues;
        m_filledValues = initValues;
    }
    
    public byte[][] solve()
    {
        SudokuDLXConverter conv = new SudokuDLXConverter(m_n);
        DLXField dlx = conv.getInitialDLX();
//        System.out.println("currRow: " + dlx.getCurrRowCount() + " col: " + dlx.getCurrColCount());
//        dlx.setInitial(12);
//        System.out.println("currRow: " + dlx.getCurrRowCount() + " col: " + dlx.getCurrColCount());
//        System.out.println(dlx.toString());
//        dlx.setInitial(25);
//        dlx.setInitial(50);
//        dlx.setInitial(59);
        byte rowNr = 1;
        for(byte[] row: m_initValues)
        {
            byte colNr = 1;
            for(byte fieldVal : row)
            {
                if(fieldVal != 0)
                {
                    FieldSol sol = new FieldSol(m_n, fieldVal, rowNr, colNr);
                    System.out.println("sol: " + sol);
                    int rowIndex = conv.getDLXRowIndex(sol);
                    dlx.setInitial(rowIndex);
                }
                colNr++;
            }
            
            rowNr++;
        }
        dlx.solve();
        List<List<Node>> fs = dlx.getFinalSolutions();
        List<Node> nodeL = fs.get(0);
        System.out.println("nodel: " + nodeL);
        nodeL.forEach(n -> 
        {
            FieldSol sol = conv.getFieldSol(n);
            m_filledValues[sol.getRowNr()-1][sol.getColNr()-1] = sol.getCand();
            System.out.println("sol: " + conv.getFieldSol(n) );
        });
        return m_filledValues;
    }
    
    public void printSolution()
    {
        for(byte[] row: m_filledValues)
        {
            for(byte cel: row)
            {
                System.out.print(cel);
            }
            System.out.println();
        }
    }
}
