package clite.AbstractSyntax;

public class Conditional extends Statement {
// Conditional = Expression test; Statement thenbranch, elsebranch
    Expression test;
    Statement thenbranch, elsebranch;
    // elsebranch == null means "if... then"
    
    public Conditional (Expression t, Statement tp) {
        test = t; thenbranch = tp; elsebranch = new Skip( );
    }
    
    public Conditional (Expression t, Statement tp, Statement ep) {
        test = t; thenbranch = tp; elsebranch = ep;
    }
    
}
