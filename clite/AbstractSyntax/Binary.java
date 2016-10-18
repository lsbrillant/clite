package clite.AbstractSyntax;

public class Binary extends Expression {
// Binary = Operator op; Expression term1, term2
    Operator op;
    Expression term1, term2;

    public Binary (Operator o, Expression l, Expression r) {
        op = o; term1 = l; term2 = r;
    } // binary

    public String toString() {
        return "" + term1 + " " + op + " " + term2;
    }

}

