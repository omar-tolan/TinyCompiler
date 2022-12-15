/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils.Operations;

import tinycompiler.Token;
import tinycompiler.utils.Node;

/**
 *
 * @author tolan
 */
public class ComparisonOp extends Node{
    
    public String operation;

    public ComparisonOp(Token t, String operation) {
        super(t);
        this.operation = operation;
    }
}
