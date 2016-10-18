package clite;

import java.util.*;
import clite.AbstractSyntax.*;
import static clite.TokenType.*;

public class Parser {
    // Recursive descent parser that inputs a C++Lite program and 
    // generates its abstract syntax.  Each method corresponds to
    // a concrete syntax grammar rule, which appears as a comment
    // at the beginning of the method.
  
    Token token;          // current token from the input stream
    Lexer lexer;
  
    public Parser(Lexer ts) { // Open the C++Lite source program
        lexer = ts;                          // as a token stream, and
        token = lexer.next();            // retrieve its first Token
    }
  
    private String match (TokenType t) {
        String value = token.value();
        if (token.type().equals(t))
            token = lexer.next();
        else
            error(t);
        return value;
    }
  
    private void error(TokenType tok) {
        System.err.println("Syntax error: expecting: " + tok 
                           + "; saw: " + token);
        System.exit(1);
    }
  
    private void error(String tok) {
        System.err.println("Syntax error: expecting: " + tok 
                           + "; saw: " + token);
        System.exit(1);
    }
  
    public Program program() {
        // Program --> int main ( ) '{' Declarations Statements '}'
        TokenType[ ] header = {Int, Main, LeftParen, RightParen};
        for (int i=0; i<header.length; i++)   // bypass "int main ( )"
            match(header[i]);
       
        match(LeftBrace); // { 

        Declarations d = declarations();
        Block s = statements();

        Program p = new Program(d, s);

        match(RightBrace); // }
        
        return p;
    }
  
    private Declarations declarations () {
        // Declarations --> { Declaration }
        Declarations d = new Declarations();
        while (isType()) {
            declaration(d);
        }
        return d;
    }

    private void declaration (Declarations ds) {
        // Declaration  --> Type Identifier { , Identifier } ;
        Type t = type();
        while(!token.type().equals(Semicolon)) {
            ds.add(new Declaration((new Variable(match(Identifier))), t));
            if (token.type().equals(Comma)) {
                match(Comma);
            }
        }
    }
  
    private Type type () {
        Type t = null; 
        switch(token.type()){
            case Int: t = Type.INT; break;
            case Bool: t = Type.BOOL; break;
            case Float: t = Type.FLOAT; break;
            case Char: t = Type.CHAR; break;
            default:
                error("Int | Bool | Float | Char");
        }
        match(token.type());
        return t;
    }
  
    private Statement statement() {
        // Statement --> ; | Block | Assignment | IfStatement | WhileStatement
        Statement s = new Skip();
        switch (token.type()) {
            case RightBrace:
                s = block();
                break;
            case If:
                s = ifStatement();
                break;
            case While:
                s = whileStatement();
                break;
            case Identifier:
                s = assignment(); 
                break;
            case Semicolon:
                break;
            default:
                error("Block | Assignment | IfStatement | WhileStatement");
        }
        return s;
    }
  
    private Block statements () {
        // Statements --> { Statement }
        
        Block b = new Block();
        // not the best condition, but I think it would work.
        while(!token.type().equals(RightBrace)){
            b.members.add(statement());
        }
        return b;
    }
    
    private Block block() {
        // Block --> '{' Statements '}'
        Block b = new Block();
        match(LeftBrace); // {
        
        b.members.addAll(statements().members); // Statements
        
        match(RightBrace); // }
        return b;
    
    }
  
    private Assignment assignment () {
        // Assignment --> Identifier '=' Expression ;
        match(Identifier);
        match(Assign);
        //expresion();
        return null;  // student exercise
    }
  
    private Conditional ifStatement () {
        // IfStatement --> 'if' '(' Expression ')' Statement [ else Statement ]
        match(If);
        match(RightParen);

        Expression e = expression();

        match(LeftParen);

        Statement s = statement();

        return new Conditional(e,s);  // student exercise
    }
  
    private Loop whileStatement () {
        // WhileStatement --> while ( Expression ) Statement
        match(While); // while
        match(LeftParen); // (

        Expression e = expression();

        match(RightParen); // )

        Statement s = statement();

        Loop l = new Loop(e, s);
        return l;  // student exercise
    }

    private Expression expression () {
        // Expression --> Conjunction { || Conjunction }
        Expression e = conjunction();
        while (token.type().equals(Or)) {
            Operator op = new Operator(match(token.type()));
            Expression eq2 = conjunction();
            e = new Binary(op, e, eq2);
        }
        return e;
    }
  
    private Expression conjunction () {
        // Conjunction --> Equality { && Equality }
        Expression e = equality();
        while (token.type().equals(And)) {
            Operator op = new Operator(match(token.type()));
            Expression eq2 = equality();
            e = new Binary(op, e, eq2);
        }
        return e;
    }
  
    private Expression equality () {
        // Equality --> Relation [ EquOp Relation ]
        Expression e = relation();
        if (isEqualityOp()) {
            Operator op = new Operator(match(token.type()));
            Expression relation2 = relation();
            e = new Binary(op, e, relation2);
        }
        return e;
    }

    private Expression relation (){
        // Relation --> Addition [RelOp Addition] 
        Expression e = addition();
        if (isRelationalOp()) {
            Operator op = new Operator(match(token.type()));
            Expression addition2 = addition();
            e = new Binary(op, e, addition2);
        }
        return e;
    }
  
    private Expression addition () {
        // Addition --> Term { AddOp Term }
        Expression e = term();
        while (isAddOp()) {
            Operator op = new Operator(match(token.type()));
            Expression term2 = term();
            e = new Binary(op, e, term2);
        }
        return e;
    }
  
    private Expression term () {
        // Term --> Factor { MultiplyOp Factor }
        Expression e = factor();
        while (isMultiplyOp()) {
            Operator op = new Operator(match(token.type()));
            Expression term2 = factor();
            e = new Binary(op, e, term2);
        }
        return e;
    }
  
    private Expression factor() {
        // Factor --> [ UnaryOp ] Primary 
        if (isUnaryOp()) {
            Operator op = new Operator(match(token.type()));
            Expression term = primary();
            return new Unary(op, term);
        }
        else return primary();
    }
  
    private Expression primary () {
        // Primary --> Identifier | Literal | ( Expression )
        //             | Type ( Expression )
        Expression e = null;
        if (token.type().equals(TokenType.Identifier)) {
            e = new Variable(match(TokenType.Identifier));
        } else if (isLiteral()) {
            e = literal();
        } else if (token.type().equals(TokenType.LeftParen)) {
            token = lexer.next();
            e = expression();       
            match(TokenType.RightParen);
        } else if (isType( )) {
            Operator op = new Operator(match(token.type()));
            match(TokenType.LeftParen);
            Expression term = expression();
            match(TokenType.RightParen);
            e = new Unary(op, term);
        } else error("Identifier | Literal | ( | Type");
        return e;
    }

    private Value literal( ) {
        return null;  // student exercise
    }
  

    private boolean isAddOp( ) {
        return token.type().equals(TokenType.Plus) ||
               token.type().equals(TokenType.Minus);
    }
    
    private boolean isMultiplyOp( ) {
        return token.type().equals(TokenType.Multiply) ||
               token.type().equals(TokenType.Divide);
    }
    
    private boolean isUnaryOp( ) {
        return token.type().equals(TokenType.Not) ||
               token.type().equals(TokenType.Minus);
    }
    
    private boolean isEqualityOp( ) {
        return token.type().equals(TokenType.Equals) ||
            token.type().equals(TokenType.NotEqual);
    }
    
    private boolean isRelationalOp( ) {
        return token.type().equals(TokenType.Less) ||
               token.type().equals(TokenType.LessEqual) || 
               token.type().equals(TokenType.Greater) ||
               token.type().equals(TokenType.GreaterEqual);
    }
    
    private boolean isType( ) {
        return token.type().equals(TokenType.Int)
            || token.type().equals(TokenType.Bool) 
            || token.type().equals(TokenType.Float)
            || token.type().equals(TokenType.Char);
    }
    
    private boolean isLiteral( ) {
        return token.type().equals(TokenType.IntLiteral) ||
            isBooleanLiteral() ||
            token.type().equals(TokenType.FloatLiteral) ||
            token.type().equals(TokenType.CharLiteral);
    }
    
    private boolean isBooleanLiteral( ) {
        return token.type().equals(TokenType.True) ||
            token.type().equals(TokenType.False);
    }
    
    public static void main(String args[]) {
        Parser parser  = new Parser(new Lexer(args[0]));
        Program prog = parser.program();
        prog.display();           // display abstract syntax tree
    } //main

} // Parser
