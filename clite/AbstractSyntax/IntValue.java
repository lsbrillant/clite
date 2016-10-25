package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class IntValue extends Value {
    private int value = 0;

    public IntValue ( ) { type = Type.INT; }

    public IntValue (int v) { this( ); value = v; undef = false; }

    public int intValue ( ) {
        assert !undef : "reference to undefined int value";
        return value;
    }

    public String toString( ) {
        if (undef)  return "undef";
        return "" + value;
    }

}
