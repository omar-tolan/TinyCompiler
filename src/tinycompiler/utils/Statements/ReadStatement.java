/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils.Statements;

import tinycompiler.Token;
import tinycompiler.terminals.Identifier;

/**
 *
 * @author tolan
 */
public class ReadStatement extends Statement{
    
    public Identifier identifier;
    
    public ReadStatement(Token firstToken, int counter,Identifier identifier) {
        super(firstToken,counter);
        this.identifier = identifier;
    }
    
}
