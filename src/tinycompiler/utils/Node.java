/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils;
import tinycompiler.Token;

/**
 *
 * @author tolan
 */
public class Node {
    private Token firstToken;
    private int nodeId;
    
    public Node(Token t, int counter){
        this.firstToken = t;
        this.nodeId = counter;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }
    
    public Token getFirstToken(){
        return this.firstToken;
    }
}
