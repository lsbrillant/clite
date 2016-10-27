package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class Loop extends Statement {
// Loop = Expression test; Statement body
    Expression test;
    Statement body;

    public Loop (Expression t, Statement b) {
        test = t; body = b;
    }
    /* 
    public void display(String padding) {
        System.out.println(padding + "Loop:");
        System.out.println(padding + "  Test: " + test );
        System.out.println(padding + "Body:");
        body.display(padding + "  ");
    }
    */
    public String toString() {
        return "Loop:";
    }
    public void accept(Visitor v) {
        v.visit(this);

        v.pushState();
       
        test.accept(v);
        
        v.pushState();
        
        body.accept(v);

        v.popState();
        v.popState();
    } 
}
