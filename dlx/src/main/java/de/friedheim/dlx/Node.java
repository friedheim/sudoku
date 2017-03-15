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
public final class Node {
    Node m_left;
    Node m_right;
    Node m_up;
    Node m_down;
    int m_colNr;
    int m_rowNr;

    public Node(int colNr, int rowNr){
            this.m_colNr = colNr;
            this.m_rowNr = rowNr;
            this.m_left = this;
            this.m_right = this;
            this.m_down = this;
            this.m_up = this;
    }

    public int getColNr(){
            return this.m_colNr;
    }

    public int getRowNr(){
            return this.m_rowNr;
    }

    public Node getLeft() {
        return m_left;
    }

    void setLeft(Node left) {
        this.m_left = left;
    }

    public Node getRight() {
        return m_right;
    }

    void setRight(Node right) {
        this.m_right = right;
    }

    public Node getUp() {
        return m_up;
    }

    void setUp(Node up) {
        this.m_up = up;
    }

    public Node getDown() {
        return m_down;
    }

    void setDown(Node down) {
        this.m_down = down;
    }
    
    void addLeft(Node nleft) throws IllegalArgumentException{
            if (!isSameRow(nleft)){
                    throw new IllegalArgumentException();
            }
            this.m_left.m_right = nleft;
            nleft.m_left = this.m_left;
            this.m_left = nleft;
            nleft.m_right = this;
    }

    void addRight(Node nright){
            if (!isSameRow(nright)){
                    throw new IllegalArgumentException();
            }
            this.m_right.m_left = nright;
            nright.m_right = this.m_right;
            this.m_right = nright;
            nright.m_left = this;
    }

    void addDown(Node nbottom){
            if (!isSameCol(nbottom)){
                    throw new IllegalArgumentException();
            }
//		this.m_down.m_up = nbottom;
//		nbottom.m_up = this.m_down;
//		this.m_down = nbottom;
//		nbottom.m_up = this;


            Node oldBottom = this.m_down;
            oldBottom.m_up = nbottom;
            nbottom.m_up = this;
            this.m_down = nbottom;
            nbottom.m_down = oldBottom;

    }

    void addUp(Node ntop){
            if (!isSameCol(ntop)){
                    throw new IllegalArgumentException();
            }
            Node oldTop = this.m_up;
            oldTop.m_down = ntop;
            ntop.m_down = this;
            this.m_up = ntop;
            ntop.m_up = oldTop;
    }

    public boolean isRootNode() {
            return this.m_colNr == -1 && this.m_rowNr == -1;
    }

    public boolean isColHeaderNode() {
            return this.m_rowNr == -1;
    }

    public boolean isRowHeaderNode() {
            return this.m_colNr == -1;
    }

    boolean isFirstInRow(){
            return m_left.m_colNr >= m_colNr;
    }

    boolean isSameRow(Node otherNode){
            return this.m_rowNr == otherNode.m_rowNr;
    }

    boolean isSameCol(Node otherNode){
            return this.m_colNr == otherNode.m_colNr;
    }
    
    @Override
    public String toString(){
            return m_colNr + "/" + m_rowNr;
    }
	
}
