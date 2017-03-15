/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friedheim.dlx;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author friedheim
 */
public class NodeTest {
    
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    
    
    @Test 
    public void testRootNode() //throws Exception
    {
        Node rootNode = new Node(-1,-1);
        Assert.assertTrue(rootNode.isRootNode());
    }
    
    
    @Test //(expected=IllegalArgumentException.class)
    public void testNotRootNode() //throws Exception
    {
        Node oNode = new Node(-1,2);
        Assert.assertFalse(oNode.isRootNode());
    }
    
    @Test
    public void testAddUp() //throws Exception
    {
        Node hNode = new Node(2,-1);
        Node xNode = new Node(2,3);
        hNode.addUp(xNode);
        Assert.assertEquals(hNode.m_down,xNode);
        Assert.assertEquals(hNode.m_up,xNode);
        Assert.assertEquals(hNode.m_left, hNode);
        Assert.assertEquals(xNode.m_down,hNode);
        Assert.assertEquals(xNode.m_up,hNode);
        Assert.assertEquals(xNode.m_left, xNode);
    }
    
        
    @Test
    public void testAddDown() //throws Exception
    {
        Node hNode = new Node(2,-1);
        Node xNode = new Node(2,3);
        hNode.addDown(xNode);
        Assert.assertEquals(hNode.m_down,xNode);
        Assert.assertEquals(hNode.m_up,xNode);
        Assert.assertEquals(hNode.m_left, hNode);
        Assert.assertEquals(xNode.m_down,hNode);
        Assert.assertEquals(xNode.m_up,hNode);
        Assert.assertEquals(xNode.m_left, xNode);
    }
    
    @Test
    public void testAddLeft() //throws Exception
    {
        Node rNode = new Node(-1,2);
        Node xNode = new Node(3,2);
        rNode.addLeft(xNode);
        Assert.assertEquals(rNode.m_left,xNode);
        Assert.assertEquals(rNode.m_right,xNode);
        Assert.assertEquals(rNode.m_down, rNode);
        Assert.assertEquals(xNode.m_left,rNode);
        Assert.assertEquals(xNode.m_right,rNode);
        Assert.assertEquals(xNode.m_down, xNode);
    }
    
    @Test
    public void testAddRight() //throws Exception
    {
        Node rNode = new Node(-1,2);
        Node xNode = new Node(3,2);
        rNode.addRight(xNode);
        Assert.assertEquals(rNode.m_right,xNode);
        Assert.assertEquals(rNode.m_left,xNode);
        Assert.assertEquals(rNode.m_down, rNode);
        Assert.assertEquals(xNode.m_right,rNode);
        Assert.assertEquals(xNode.m_left,rNode);
        Assert.assertEquals(xNode.m_down, xNode);
    }
    
}
