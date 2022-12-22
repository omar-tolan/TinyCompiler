/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils.Statements;

import tinycompiler.Token;
import tinycompiler.terminals.Identifier;
import tinycompiler.utils.Expressions.Expression;

/**
 *
 * @author tolan
 */
public class AssignStatement extends Statement{
    
    public Identifier identifier;
    public Expression expression;
    
    public AssignStatement(Token firstToken, int counter,Identifier identifier, Expression expression) {
        super(firstToken,counter);
        this.identifier = identifier;
        this.expression = expression;
    }
    
}
