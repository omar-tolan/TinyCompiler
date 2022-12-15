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
    
    public Node(Token t){
        this.firstToken = t;
    }
    public Token getFirstToken(){
        return this.firstToken;
    }
}
