/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tinycompiler;

import tinycompiler.utils.Expressions.Expression;

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
        Scanner scanner = new Scanner("c + 3  * 3 < 2 + x / 5");
        System.out.println(scanner.getTokens());
        Parser parser = new Parser(scanner);
        Expression exp = parser.expression();
        System.out.println("done");
    }
    
}
