/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler;

import java.util.ArrayList;
import tinycompiler.terminals.Identifier;
import tinycompiler.utils.Expressions.Expression;
import tinycompiler.utils.Expressions.SimpleExpression;
import tinycompiler.utils.Factor;
import tinycompiler.utils.Operations.AddOp;
import tinycompiler.utils.Operations.ComparisonOp;
import tinycompiler.utils.Operations.MulOp;
import tinycompiler.utils.Program;
import tinycompiler.utils.Statements.AssignStatement;
import tinycompiler.utils.Statements.IfStatement;
import tinycompiler.utils.Statements.ReadStatement;
import tinycompiler.utils.Statements.RepeatStatement;
import tinycompiler.utils.Statements.Statement;
import tinycompiler.utils.Statements.StatementSequence;
import tinycompiler.utils.Statements.WriteStatement;
import tinycompiler.utils.Term;

/**
 *
 * @author tolan
 */
public class Parser {
    
    Scanner scanner;
    Token currToken;
    ArrayList<SimpleExpression> simpleExpsChain;
    ArrayList<Term> termsChain;
    int termsIndex;
    int simpleExpsIndex;
    
    void match(String t) throws SyntaxException{
        if(!t.equals(currToken.getTokenType())){
            throw new SyntaxException();
        }
    }
    
    void consume(){
        currToken = this.scanner.nextToken();
    }
    
    Parser(Scanner scanner){
        this.scanner = scanner;
        this.currToken = scanner.nextToken();
        this.simpleExpsChain = new ArrayList<SimpleExpression>();
        this.termsChain = new ArrayList<Term>();
    }
    
    Program Parse() throws SyntaxException{
        Program program = program();
        return program;
    }
    
    Program program() throws SyntaxException{
        Token ftoken = currToken;
        StatementSequence stmtseq = statementSequence();
        Program prog = new Program(ftoken, stmtseq);
        return prog;
        
    }
    
    Expression expression() throws SyntaxException{
        Token ftoken = currToken;
        SimpleExpression simpleExp1 = simpleExpression();
        Expression currExp = new Expression(ftoken, simpleExp1);
        while(currToken.getTokenType().equals("EQUAL")|currToken.getTokenType().equals("LESSTHAN")|currToken.getTokenType().equals("GREATERTHAN")){
            ComparisonOp comparisonOp = new ComparisonOp(currToken ,currToken.getTokenVal());
            consume();
            SimpleExpression simpleExp2 = simpleExpression();
            return new Expression(ftoken, simpleExp1, comparisonOp, simpleExp2);
        }
       return new Expression(ftoken, simpleExp1); 
    }
    
    SimpleExpression simpleExpression() throws SyntaxException{
        Token ftoken = currToken;
        Term term = term();
        simpleExpsChain.add(new SimpleExpression(ftoken, term));
        simpleExpsIndex++;
        while(currToken.getTokenType().equals("PLUS") | currToken.getTokenType().equals("MINUS")){
            AddOp addOp = new AddOp(currToken, currToken.getTokenVal());
            consume();
            Term sTerm = term();
            simpleExpsChain.add(new SimpleExpression(currToken, simpleExpsChain.get(simpleExpsIndex-1), addOp, sTerm));
            simpleExpsIndex++;
        }
        return simpleExpsChain.get(simpleExpsChain.size()-1);        
    }
    
    Term term() throws SyntaxException{
        Token ftoken = currToken;
        Factor factor = factor();
        termsChain.add(new Term(ftoken, factor));
        termsIndex++;
        while(currToken.getTokenType().equals("MULT") | currToken.getTokenType().equals("DIV")){
            MulOp mulOp = new MulOp(currToken, currToken.getTokenVal());
            consume();
            Factor sFactor = factor();
            termsChain.add(new Term(currToken, termsChain.get(termsIndex-1), mulOp, sFactor));
            termsIndex++;
        }
        return termsChain.get(termsChain.size()-1);
        
    }
    
    Factor factor() throws SyntaxException{
        Token ftoken = currToken;
        if(currToken.getTokenType().equals("OPENBRACKET")){
            consume();
            Expression expression = expression();
            consume();
            return new Factor(ftoken, expression);
        }else if(currToken.getTokenType().equals("NUMBER")){
            consume();
            return new Factor(ftoken, Integer.parseInt(ftoken.getTokenVal()));
        }else{
            consume();
            return new Factor(ftoken, ftoken.getTokenVal());
        }
    }
  
    
    StatementSequence statementSequence() throws SyntaxException{
        Token ftoken = currToken;
        ArrayList<Statement> stmts = new ArrayList<Statement>();
        while(currToken.getTokenType().equals("IF") | currToken.getTokenType().equals("READ") | currToken.getTokenType().equals("WRITE") | currToken.getTokenType().equals("ASSIGN") | currToken.getTokenType().equals("REPEAT")){
            stmts.add(statement());
        }
        return new StatementSequence(ftoken, stmts);
    }
    
    Statement statement() throws SyntaxException{
        Token fToken = currToken;
        Statement statement = null;
        if(currToken.getTokenType().equals("IF")){
            statement = ifStatement();
        }else if(currToken.getTokenType().equals("WRITE")){
            statement = writeStatement();
        }else if(currToken.getTokenType().equals("READ")){
            statement = readStatement();
        }else if(currToken.getTokenType().equals("ASSIGN")){
            statement = assignStatement();
        }else if(currToken.getTokenType().equals("REPEAT")){
            statement = repeatStatement();
        }
        return statement;
    }
    
    IfStatement ifStatement() throws SyntaxException{
        Token ftoken = currToken;
        Expression expression = null;
        Statement statement = null;
        Statement elseStatement = null;
        IfStatement ifstmt = null;
        match("IF");
        consume();
        expression = expression();
        statement = statement();
        if(currToken.getTokenType().equals("else")){
            match("ELSE");
            elseStatement = statement();
            return new IfStatement(ftoken, expression, statement, elseStatement);
        }
        return new IfStatement(ftoken, expression, statement);
    }
    
    WriteStatement writeStatement() throws SyntaxException{
        Token ftoken = currToken;
        Expression expression = null;
        match("WRITE");
        expression = expression();
        return new WriteStatement(ftoken, expression);
    }
    
    ReadStatement readStatement() throws SyntaxException{
        Token ftoken = currToken;
        Identifier ident = null;
        match("READ");
        consume();
        match("IDENTIFIER");
        ident = new Identifier(currToken, currToken.getTokenVal());
        consume();
        return new ReadStatement(ftoken, ident);
    }
    
    AssignStatement assignStatement() throws SyntaxException{
        Token ftoken = currToken;
        Identifier identifier = null;
        Expression expression = null;
        
        match("IDENTIFIER");
        consume();
        match("ASSIGN");
        consume();
        expression = expression();
        
        return new AssignStatement(ftoken, identifier, expression);
    }
    
    RepeatStatement repeatStatement() throws SyntaxException{
        Token ftoken = currToken;
        StatementSequence stmtseq = null;
        Expression expression = null;
        
        match("REPEAT");
        consume();
        stmtseq = statementSequence();
        match("UNTIL");
        consume();
        expression = expression();
        
        return new RepeatStatement(ftoken, stmtseq, expression);
    }
}
