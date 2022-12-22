/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils.Statements;

import tinycompiler.Token;
import tinycompiler.utils.Expressions.Expression;

/**
 *
 * @author tolan
 */
public class IfStatement extends Statement{
    
    public StatementSequence thenStatements;
    public StatementSequence elseStatements;
    public Expression expression;
    
    public IfStatement(Token firstToken, int counter,Expression expression, StatementSequence thenStatements) {
        super(firstToken,counter);
        this.expression = expression;
        this.thenStatements = thenStatements;
    }
    
    public IfStatement(Token firstToken, int counter,Expression expression, StatementSequence thenStatements, StatementSequence elseStatements) {
        super(firstToken,counter);
        this.expression = expression;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
    }
}
