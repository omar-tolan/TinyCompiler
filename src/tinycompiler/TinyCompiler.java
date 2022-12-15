/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tinycompiler;

import tinycompiler.utils.Expressions.SimpleExpression;

/**
 *
 * @author tolan
 */
public class TinyCompiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SyntaxException{
        // TODO code application logic here
        
        //String data = File.readFile("data.txt");
        Scanner scanner = new Scanner(" 5 + 2 * 3 / 2 * 4 + 4");
        System.out.println(scanner.getTokens());
        Parser parser = new Parser(scanner);
        SimpleExpression exp = parser.simpleExpression();
        System.out.println("done");
    }
    
}
