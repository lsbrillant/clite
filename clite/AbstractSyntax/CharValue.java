package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class CharValue extends Value {
    private char value = ' ';

    public CharValue ( ) { type = Type.CHAR; }

    public CharValue (char v) { this( ); value = v; undef = false; }

    public char charValue ( ) {
        assert !undef : "reference to undefined char value";
        return value;
    }

    public String toString( ) {
        if (undef)  return "undef";
        return "'" + value + "'";
    }

}
