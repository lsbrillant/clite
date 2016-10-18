package clite.AbstractSyntax;

import java.util.*;

public class Declaration {
// Declaration = Variable v; Type t
    Variable v;
    Type t;

    public Declaration (Variable var, Type type) {
        v = var; t = type;
    } // declaration */

    public void display(String padding) {
        System.out.print(padding + "< " + t + " " + v + " >");
    }
}
