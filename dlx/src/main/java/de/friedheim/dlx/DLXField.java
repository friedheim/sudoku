/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.dlx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author friedheim
 */
public class DLXField {
    private final int m_colCount;
    private final int m_rowCount;
    private final byte[][] m_inputArray;
    
    private final List<ColHNode> m_colHeaderNodes = new ArrayList<>();
    private final List<Node> m_rowHeaderNodes = new ArrayList<>();
    private final ColHNode m_rootNode;
    private final List<Node> m_solutions = new ArrayList<>();
    private final List<List<Node>> m_finalSolutions = new ArrayList<>();
    
    /**
     * Constructor for initialization of DLX object
     * 
     * @param colCount number of columns in dlx-fields
     * @param rowCount number of rows in dlx-fields
     * @param inputArray values for initialization of the DLX-Algorithm (inner array are rows)
     */
    public DLXField(int colCount, int rowCount, byte[][] inputArray)
    {
        if(inputArray.length != rowCount)
        {
            throw new IllegalArgumentException("Rowsize not corresponds with input data");
        }
        for(byte[] row: inputArray)
        {
            if(row.length != colCount)
            {
              throw new IllegalArgumentException("Colsize not corresponds with input data");  
            }
        }
        
        m_colCount = colCount;
        m_rowCount = rowCount;
        m_inputArray = inputArray;
        
        //initialize rootNode
        m_rootNode = new ColHNode(-1,-1);
        System.out.println("vor initialize");
        initialize();
        System.out.println("nach initialize");
    }
    
    private void initialize()
    {
        //initialize colHeaderNodes
        for(int i = 0; i< m_colCount; i++)
        {
            ColHNode newNode = new ColHNode(i,-1);
            m_colHeaderNodes.add(newNode);
            m_rootNode.addLeft(newNode);
        }
        //initialize rowHeaderNodes
        for(int i = 0; i< m_rowCount; i++)
        {
            Node newNode = new Node(-1,i);
            m_rowHeaderNodes.add(newNode);
            m_rootNode.addUp(newNode);
        }
        
        for(int row = 0;row < m_rowCount; row++)
        {
            for(int col = 0; col < m_colCount; col++)
            {
                byte cell = m_inputArray[row][col];
                if(cell == (byte)1)
                {
                    addNode(new Node(col,row));
                }
            }
        }
    }
    
    public byte[][] toMatrix()
    {
        byte[][] result = createCeroFilledArray(getCurrRowCount(),getCurrColCount());
        int row = 0;
        for(Node currRowHeaderNode = m_rootNode.getDown(); !currRowHeaderNode.isRootNode(); currRowHeaderNode = currRowHeaderNode.getDown())
        {
            Node currNode = currRowHeaderNode.getRight();
            int col = 0;
            for(Node currColHeaderNode = m_rootNode.getRight(); !currColHeaderNode.isRootNode(); currColHeaderNode = currColHeaderNode.getRight())
            {
                if(currNode.getColNr() == currColHeaderNode.getColNr())
                {
                    result[row][col]=1;
                    currNode = currNode.getRight();
                }
                col++;
            }
            row++;
        }
        return result;
    }
    
    
    public List<List<Node>> getFinalSolutions()
    {
        return m_finalSolutions;
    }
    
    public List<Node> solve()
    {
        printDuration();
                
        if (m_rootNode.getRight() == m_rootNode) {
            printSolution();
            m_finalSolutions.add(new ArrayList<>(m_solutions));
            return m_solutions;
        } else {
            Node column = chooseNextColumn();
            cover(column);

            for (Node row = column.getDown(); row != column; row = row.getDown()) {
                m_solutions.add(row);

                for (Node n = row.getRight(); n != row; n = n.getRight()) {
                    if(!n.isRowHeaderNode())
                    {
                        cover(n);
                    }
                }

                solve();
                
                m_solutions.remove(row);
                column = getColHeaderNode(row) ;

                for (Node leftNode = row.getLeft(); leftNode != row; leftNode = leftNode.getLeft()) {
                    if(!leftNode.isRowHeaderNode())
                    {
                        uncover(leftNode);
                    }
                }
            }
            uncover(column);
        }
        return null;
    }
    
    public void cover(Node nodeToCover) 
    {
        ColHNode column = getColHeaderNode(nodeToCover);
        column.getRight().setLeft(column.getLeft());
        column.getLeft().setRight(column.getRight());

        for (Node row = column.getDown(); row != column; row = row.getDown()) 
        {
            for (Node currNode = row.getRight(); currNode != row; currNode = currNode.getRight()) 
            {
                currNode.getUp().setDown(currNode.getDown());
                currNode.getDown().setUp(currNode.getUp());
                if(!currNode.isRowHeaderNode())
                {
                    getColHeaderNode(currNode).decrementColCap();
                }
            }
        }
    }
    
    public void setInitial (int row)
    {
        Node rhn = getRowHeaderNode(row);
        for(Node n = rhn.getRight(); n != rhn; n = n.getRight())
        {
            cover(n);    
        }
    }
    
    public void uncover(Node nodeToUncover) {
        ColHNode column = getColHeaderNode(nodeToUncover);
        
        for (Node row = column.getUp(); row != column; row = row.getUp()) {
            for (Node currNode = row.getLeft(); currNode != row; currNode = currNode.getLeft()) {
                currNode.getUp().setDown(currNode);
                currNode.getDown().setUp(currNode);
                if(!currNode.isRowHeaderNode())
                {
                    getColHeaderNode(currNode).incrementColCap();
                }
            }
        }
        column.getRight().setLeft(column);
        column.getLeft().setRight(column);
    }
    
    ColHNode getColHeaderNode(Node col)
    {
        return m_colHeaderNodes.get(col.getColNr());
    }
    
    Node getRowHeaderNode(Node row)
    {
        return m_rowHeaderNodes.get(row.getRowNr());
    }
    
    ColHNode getColHeaderNode(int colNr)
    {
        return m_colHeaderNodes.get(colNr);
    }
    
    Node getRowHeaderNode(int rowNr)
    {
        return m_rowHeaderNodes.get(rowNr);
    }
    
    public int getCurrRowCount()
    {
        int count=0;
        for(Node row = m_rootNode.getDown(); row != m_rootNode ; row = row.getDown())
        {
            count++;
        }
        return count;
    }
    
    public int getCurrColCount()
    {
        int count=0;
        for(Node col = m_rootNode.getRight(); col != m_rootNode ; col = col.getRight())
        {
            count++;
        }
        return count;
    }
    
    private void addNode(Node addedNode)
    {
        ColHNode colHeaderNode = getColHeaderNode(addedNode.getColNr());
        Node rowHeaderNode = getRowHeaderNode(addedNode.getRowNr());
        colHeaderNode.addUp(addedNode);
        rowHeaderNode.addLeft(addedNode);
        colHeaderNode.incrementColCap();
    }

    private byte[][] createCeroFilledArray(int rowCount, int colCount) 
    {
        byte[][] result = new byte[rowCount][colCount];
        for (byte[] row: result)
        {
            Arrays.fill(row, (byte)0);
        }
        return result;
    }

    private Node chooseNextColumn() 
    {
        int minCapacity = m_rowCount+1;
        ColHNode minCapacityNode = (ColHNode)m_rootNode.getRight();
        for(ColHNode currColHNode = (ColHNode)m_rootNode.getRight(); !currColHNode.isRootNode() ;
                currColHNode = (ColHNode)currColHNode.getRight())
        {
            if(currColHNode.getColCap() < minCapacity)
            {
                minCapacity = currColHNode.getColCap();
                minCapacityNode = currColHNode;
            }
        }
        return minCapacityNode;
//        return m_rootNode.getRight();
    }

    private void printSolution() {
        System.out.println("riddle is solved!!! solution is: " + m_solutions);
    }
    
    @Override
    public String toString()
    {
        printDuration();
        byte[][] matrix = toMatrix();
        printDuration();
//        String result = "";
        StringBuilder s = new StringBuilder();
        printDuration();
        for(int row = 0; row < matrix.length; row++)
        {
            for(int col = 0; col < matrix[0].length; col++)
            {
                if((col%(16) == 0))
                {
                    s.append("|");
                }
                s.append(matrix[row][col]);
            }
            s.append("\n");
        }
        printDuration();
        return s.toString();
    }

    private void printDuration() {
//        System.out.println("time: " + (System.currentTimeMillis() - m_currentTimeMilliesStart) + " millisekunden!!!!");
    }
}
