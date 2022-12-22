/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils.Expressions;

import tinycompiler.Token;
import tinycompiler.utils.Node;
import tinycompiler.utils.Operations.AddOp;
import tinycompiler.utils.Term;

/**
 *
 * @author tolan
 */
public class SimpleExpression extends Node{
    
    public Term term;
    public AddOp addOp;
    public SimpleExpression childExpression;

    public SimpleExpression(Token t, int counter,Term term) {
        super(t,counter);
        this.term = term;
    }
    
    public SimpleExpression(Token t,int counter,SimpleExpression simpleExp, AddOp addOp, Term term){
        super(t,counter);
        this.term = term;
        this.addOp = addOp;
        this.childExpression = simpleExp;
    }
    
}
