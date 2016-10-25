package clite.AbstractSyntax;

import java.util.*;
import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class Declaration implements Visitable {
// Declaration = Variable v; Type t
    Variable v;
    Type t;

    public Declaration (Variable var, Type type) {
        v = var; t = type;
    } // declaration */

    public void display(String padding) {
        System.out.print(padding + "< " + t + " " + v + " >");
    }
    public void accept(Visitor v) { 
        v.visit(this);
        this.v.accept(v);
        t.accept(v);
    }

    public String toString() {
        return "Decleration: ";
    } 
}
