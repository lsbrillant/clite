package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class Unary extends Expression {
    // Unary = Operator op; Expression term
    Operator op;
    Expression term;

    public Unary (Operator o, Expression e) {
        op = o; term = e;
    } // unary
    
    public String toString() {
        return "Unary:";
    }
    public void accept(Visitor v) {
        v.visit(this);
        v.incLevel();

        op.accept(v);
        term.accept(v);

        v.decLevel();
    }

}

