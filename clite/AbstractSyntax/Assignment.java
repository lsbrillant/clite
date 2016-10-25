package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class Assignment extends Statement {
    // Assignment = Variable target; Expression source
    Variable target;
    Expression source;

    public Assignment (Variable t, Expression e) {
        target = t;
        source = e;
    }
    
    public void display(String padding) { 
        System.out.println(padding + "Assignment: " + target + " = " + source);
    }

    public String toString() {
        return "Assignment: ";
    }

    public void accept(Visitor v) {
        v.visit(this);
        v.incLevel();
        target.accept(v);
        source.accept(v);
        v.decLevel();

    }

}
