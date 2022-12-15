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
public class RepeatStatement extends Statement{
    public StatementSequence stmtseq;
    public Expression expression;

    public RepeatStatement(Token firstToken, StatementSequence stmtseq, Expression expression) {
        super(firstToken);
        this.stmtseq = stmtseq;
        this.expression = expression;
    }
    
}
