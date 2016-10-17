package clite.AbstractSyntax;

public class Assignment extends Statement {
    // Assignment = Variable target; Expression source
    Variable target;
    Expression source;

    public Assignment (Variable t, Expression e) {
        target = t;
        source = e;
    }

}
