/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.terminals;

import tinycompiler.Token;
import tinycompiler.utils.Node;

/**
 *
 * @author tolan
 */
public class Identifier extends Node{
    
    public String identName;

    public Identifier(Token t , String identName) {
        super(t);
        this.identName = identName;
    }
    
    
}
