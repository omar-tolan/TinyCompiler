/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinycompiler;

import GraphViz.SyntaxTree;
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
    private SyntaxTree tree;
    
    void match(String t) throws SyntaxException{
        if(!t.equals(currToken.getTokenType())){
            throw new SyntaxException("Expected " + t);
        }
    }
    
    void consume(){
        currToken = this.scanner.nextToken();
        if(currToken.getTokenType().equals("SEMICOLON")){
            currToken = this.scanner.nextToken(); 
        }
    }
    
    public Parser(Scanner scanner){
        this.scanner = scanner;
        this.currToken = scanner.nextToken();
        this.simpleExpsChain = new ArrayList<SimpleExpression>();
        this.termsChain = new ArrayList<Term>();
        this.tree = new SyntaxTree();
    }
    
    public Program parse() throws SyntaxException{
        Program program = program();
        tree.end();
        return program;
    }
    public SyntaxTree getTree(){
        return tree;
    }
    Program program() throws SyntaxException{
        Token ftoken = currToken;
        StatementSequence stmtseq = statementSequence();
        Program prog = new Program(ftoken, 0,stmtseq);
        return prog;
        
    }
    
    Expression expression() throws SyntaxException{
        Token ftoken = currToken;
        SimpleExpression simpleExp1 = simpleExpression();
        while(currToken.getTokenType().equals("EQUAL")|currToken.getTokenType().equals("LESSTHAN")|currToken.getTokenType().equals("GREATERTHAN")){
            tree.makeOPNode(currToken.getTokenVal());
            int opnode_ID = tree.getNodeCounter();
            ComparisonOp comparisonOp = new ComparisonOp(currToken ,opnode_ID,currToken.getTokenVal());
            consume();
            tree.addChild(opnode_ID, simpleExp1.getNodeId());
            SimpleExpression simpleExp2 = simpleExpression();
            tree.addChild(opnode_ID, simpleExp2.getNodeId());
            return new Expression(ftoken, opnode_ID,simpleExp1, comparisonOp, simpleExp2);
        }
       return new Expression(ftoken, simpleExp1.getNodeId(),simpleExp1); 
    }
    
    SimpleExpression simpleExpression() throws SyntaxException{
        int mainopNode_ID = 0;
        Token ftoken = currToken;
        Term term = term();
        simpleExpsChain.add(new SimpleExpression(ftoken, term.getNodeId(),term));
        simpleExpsIndex++;
        while(currToken.getTokenType().equals("PLUS") | currToken.getTokenType().equals("MINUS")){
            tree.makeOPNode(currToken.getTokenVal());
            int opnode_ID = tree.getNodeCounter();
            if((simpleExpsIndex - 1) == 0){
                 mainopNode_ID = opnode_ID;
            }
            AddOp addOp = new AddOp(currToken, opnode_ID,currToken.getTokenVal());
            consume();
            Term sTerm = term();
            tree.addChild(opnode_ID, simpleExpsChain.get(simpleExpsIndex - 1).getNodeId());
            tree.addChild(opnode_ID, sTerm.getNodeId());
            simpleExpsChain.add(new SimpleExpression(currToken, mainopNode_ID,simpleExpsChain.get(simpleExpsIndex-1), addOp, sTerm));
            simpleExpsIndex++;
        }
        return simpleExpsChain.get(simpleExpsChain.size()-1);        
    }
    
    Term term() throws SyntaxException{
        int mainopNode_ID = 0;
        Token ftoken = currToken;
        Factor factor = factor();
        termsChain.add(new Term(ftoken, factor.getNodeId(),factor));
        termsIndex++;
        while(currToken.getTokenType().equals("MULT") | currToken.getTokenType().equals("DIV")){
            tree.makeOPNode(currToken.getTokenVal());
            int opnode_ID = tree.getNodeCounter();
            if((termsIndex - 1) == 0){
                 mainopNode_ID = opnode_ID;
            }
            MulOp mulOp = new MulOp(currToken, opnode_ID,currToken.getTokenVal());
            consume();
            Factor sFactor = factor();
            tree.addChild(opnode_ID, termsChain.get(termsIndex - 1).getNodeId());
            tree.addChild(opnode_ID, sFactor.getNodeId());
            termsChain.add(new Term(currToken, mainopNode_ID,termsChain.get(termsIndex-1), mulOp, sFactor));
            termsIndex++;
        }
        return termsChain.get(termsChain.size()-1);
        
    }
    
    Factor factor() throws SyntaxException{
        Token ftoken = currToken;
        if(currToken.getTokenType().equals("OPENBRACKET")){
            consume();
            Expression expression = expression();
            match("CLOSEDBRACKET");
            consume();
            return new Factor(ftoken, expression.getNodeId(),expression);
        }else if(currToken.getTokenType().equals("NUMBER")){
            tree.makeConstNode(currToken.getTokenType());
            int constNode_ID = tree.getNodeCounter();
            consume();
            return new Factor(ftoken,constNode_ID ,Integer.parseInt(ftoken.getTokenVal()));
        }else{
            tree.makeIDNode(currToken.getTokenVal());
            int constNode_ID = tree.getNodeCounter();
            consume();
            return new Factor(ftoken,constNode_ID ,ftoken.getTokenVal());
        }
    }
  
    
    StatementSequence statementSequence() throws SyntaxException{
        Token ftoken = currToken;
        ArrayList<Statement> stmts = new ArrayList<Statement>();
        int stmtsIndex = 0;
        stmts.add(statement());
        while(currToken.getTokenType().equals("IF") | currToken.getTokenType().equals("READ") | currToken.getTokenType().equals("WRITE") | currToken.getTokenType().equals("IDENTIFIER") | currToken.getTokenType().equals("REPEAT")){
            stmtsIndex++;
            stmts.add(statement());
            int parent = stmts.get(stmtsIndex - 1).getNodeId();
            int child = stmts.get(stmtsIndex).getNodeId();
            tree.addChild(parent, child);
            tree.sameRank(parent, child);
        }
        return new StatementSequence(ftoken,stmts.get(0).getNodeId(), stmts);
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
        }else if(currToken.getTokenType().equals("IDENTIFIER")){
            statement = assignStatement();
        }else if(currToken.getTokenType().equals("REPEAT")){
            statement = repeatStatement();
        }
        return statement;
    }
    
    IfStatement ifStatement() throws SyntaxException{
        Token ftoken = currToken;
        Expression expression = null;
        StatementSequence thenStatements = null;
        StatementSequence elseStatements = null;
        IfStatement ifstmt = null;
        match("IF");
        consume();
        tree.makeIFNode();
        int ifnode_ID = tree.getNodeCounter();
        expression = expression();
        tree.addChild(ifnode_ID, expression.getNodeId());
        match("then");
        consume();
        thenStatements = statementSequence();
        if(currToken.getTokenType().equals("ELSE")){
            match("ELSE");
            consume();
            elseStatements = statementSequence();
            tree.addChild(ifnode_ID,elseStatements.getNodeId());
            return new IfStatement(ftoken, ifnode_ID,expression, thenStatements, elseStatements);
        }
        return new IfStatement(ftoken, ifnode_ID,expression, thenStatements);
    }
    
    WriteStatement writeStatement() throws SyntaxException{
        Token ftoken = currToken;
        Expression expression = null;
        match("WRITE");
        consume();
        tree.makeWriteNode();
        int writenode_ID = tree.getNodeCounter();
        expression = expression();
        return new WriteStatement(ftoken, writenode_ID,expression);
    }
    
    ReadStatement readStatement() throws SyntaxException{
        Token ftoken = currToken;
        Identifier ident = null;
        match("READ");
        consume();
        match("IDENTIFIER");
        ident = new Identifier(currToken, 0,currToken.getTokenVal());
        consume();
        tree.makeReadNode(ident.identName);
        int readnode_ID = tree.getNodeCounter();
        return new ReadStatement(ftoken,readnode_ID, ident);
    }
    
    AssignStatement assignStatement() throws SyntaxException{
        Token ftoken = currToken;
        Identifier identifier = new Identifier(ftoken, 0, currToken.getTokenVal());
        match("IDENTIFIER");
        consume();
        match("ASSIGN");
        consume();
        tree.makeAssignNode(identifier.identName);
        int assignNode_ID = tree.getNodeCounter();
        Expression expression = expression();
        tree.addChild(assignNode_ID, expression.getNodeId());
        return new AssignStatement(ftoken, assignNode_ID,identifier, expression);
    }
    
    RepeatStatement repeatStatement() throws SyntaxException{
        Token ftoken = currToken;
        StatementSequence stmtseq = null;
        Expression expression = null;
        match("REPEAT");
        consume();
        tree.makeRepeatNode();
        int repeatnode_ID = tree.getNodeCounter();
        stmtseq = statementSequence();
        match("UNTIL");
        consume();
        expression = expression();
        tree.addChild(repeatnode_ID, stmtseq.getNodeId());
        tree.addChild(repeatnode_ID,expression.getNodeId());
        return new RepeatStatement(ftoken, repeatnode_ID,stmtseq, expression);
    }
}
