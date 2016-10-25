package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public abstract class Expression implements Visitable {
    // Expression = Variable | Value | Binary | Unary
    //public void display(String padding) { }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
