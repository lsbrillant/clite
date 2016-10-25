package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public abstract class Statement implements Visitable {
    // Statement = Skip | Block | Assignment | Conditional | Loop
    public void accept(Visitor v) {
        v.visit(this);
    }
}
