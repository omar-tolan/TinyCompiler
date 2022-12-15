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
    
    public Statement statement;
    public Statement elseStatement;
    public Expression expression;
    
    public IfStatement(Token firstToken, Expression expression, Statement statement) {
        super(firstToken);
        this.expression = expression;
        this.statement = statement;
    }
    
    public IfStatement(Token firstToken, Expression expression, Statement statement, Statement elseStatement) {
        super(firstToken);
        this.expression = expression;
        this.statement = statement;
        this.elseStatement = elseStatement;
    }
}
