/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tinycompiler;

import tinycompiler.utils.Program;

/**
 *
 * @author tolan
 */
public class TinyCompiler {

    /**
     * @param args the command line arguments
     */
    public static void run() throws SyntaxException{
        // TODO code application logic here
        
        String data = File.readFile("tinyProgram.txt");
        Scanner scanner = new Scanner(data);
        System.out.println(scanner.getTokens());
        Parser parser = new Parser(scanner);
        Program prog = parser.Parse();
        System.out.println("done");
    }
}
