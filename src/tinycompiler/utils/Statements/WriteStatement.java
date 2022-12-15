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
public class WriteStatement extends Statement {
    
    public Expression expression;

    public WriteStatement(Token firstToken, Expression expression) {
        super(firstToken);
        this.expression = expression;
    }
    
}
