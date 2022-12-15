/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils;

import tinycompiler.Token;
import tinycompiler.utils.Expressions.Expression;

/**
 *
 * @author tolan
 */
public class Factor extends Node{
    public String leftBracket;
    public String rightBracket;
    public Expression expression;
    public int number;
    public String identifier;
    
    public Factor(Token ftoken, int number){
        super(ftoken);
        this.number = number;
    }
    
    public Factor(Token ftoken, String identifier){
        super(ftoken);
        this.identifier = identifier;
    }
    
    public Factor(Token ftoken, Expression expression){
        super(ftoken);
        this.leftBracket = "{";
        this.rightBracket = "}";
        this.expression = expression;
    }
}
