package clite.AbstractSyntax;

public class Loop extends Statement {
// Loop = Expression test; Statement body
    Expression test;
    Statement body;

    public Loop (Expression t, Statement b) {
        test = t; body = b;
    }
    
}
