package clite.AbstractSyntax;

public class Unary extends Expression {
    // Unary = Operator op; Expression term
    Operator op;
    Expression term;

    public Unary (Operator o, Expression e) {
        op = o; term = e;
    } // unary
    
    public String toString() {
        return "" + op + " " + term;
    }

}

