package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class Variable extends Expression {
    // Variable = String id
    private String id;

    public Variable (String s) { id = s; }

    public String toString( ) { return id; }
    
    public boolean equals (Object obj) {
        String s = ((Variable) obj).id;
        return id.equals(s); // case-sensitive identifiers
    }
    
    public void accept(Visitor v) {
        v.visit(this);    
    }
    
    public int hashCode ( ) { return id.hashCode( ); }
}
