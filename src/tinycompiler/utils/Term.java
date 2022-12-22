/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler.utils;

import tinycompiler.Token;
import tinycompiler.utils.Operations.MulOp;

/**
 *
 * @author tolan
 */
public class Term extends Node{
    
    public Factor factor;
    public MulOp mulOp;
    public Term childTerm;
    
    public Term(Token ftoken, int counter,Factor factor){
        super(ftoken,counter);
        this.factor = factor;
    }
    
    public Term(Token ftoken, int counter,Term childTerm, MulOp mulOp, Factor factor){
        super(ftoken,counter);
        this.factor = factor;
        this.mulOp = mulOp;
        this.childTerm = childTerm;
    }
    
}
