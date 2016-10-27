package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class Binary extends Expression {
// Binary = Operator op; Expression term1, term2
    Operator op;
    Expression term1, term2;

    public Binary (Operator o, Expression l, Expression r) {
        op = o; term1 = l; term2 = r;
    } // binary

    public String toString() {
        return "Binary:";
    }
    public void accept(Visitor v) {
        v.visit(this);
        v.pushState();

        op.accept(v);
        term1.accept(v);
        term2.accept(v);

        v.popState();
    }

}

