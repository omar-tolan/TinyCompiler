/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils;

import tinycompiler.utils.Statements.Statement;
import java.util.ArrayList;
import tinycompiler.Token;
import tinycompiler.utils.Statements.StatementSequence;

/**
 *
 * @author tolan
 */
public class Program extends Node{
    
    private StatementSequence stmtSeq;
    
    public Program(Token firstToken, StatementSequence stmts){
           super(firstToken);
           this.stmtSeq = stmts;
       }
}
