/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils.Expressions;

import tinycompiler.Token;
import tinycompiler.utils.Node;
import tinycompiler.utils.Operations.ComparisonOp;
/**
 *
 * @author tolan
 */
public class Expression extends Node{

    SimpleExpression simpleExp1;
    SimpleExpression simpleExp2;
    ComparisonOp comparisonOp;
    
    public Expression(Token t, int counter,SimpleExpression simpleExp) {
        super(t,counter);
        this.simpleExp1 = simpleExp;
    }

    public Expression(Token t,int counter, SimpleExpression simpleExp1, ComparisonOp comparisonOp, SimpleExpression simpleExp2) {
        super(t,counter);
        this.simpleExp1 = simpleExp1;
        this.simpleExp2 = simpleExp2;
        this.comparisonOp = comparisonOp;
    }
}
