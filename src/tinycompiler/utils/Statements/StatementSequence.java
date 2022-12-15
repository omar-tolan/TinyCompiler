/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils.Statements;

import java.util.ArrayList;
import tinycompiler.Token;
import tinycompiler.utils.Node;

/**
 *
 * @author tolan
 */
public class StatementSequence extends Node{

    public ArrayList<Statement> statements;
    
    public StatementSequence(Token t, ArrayList<Statement> statements) {
        super(t);
        this.statements = statements;
    }
    
}
