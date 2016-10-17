package clite.AbstractSyntax;

public abstract class Value extends Expression {
    // Value = IntValue | BoolValue |
    //         CharValue | FloatValue
    protected Type type;
    protected boolean undef = true;

    public int intValue ( ) {
        assert false : "should never reach here";
        return 0;
    }
    
    public boolean boolValue ( ) {
        assert false : "should never reach here";
        return false;
    }
    
    public char charValue ( ) {
        assert false : "should never reach here";
        return ' ';
    }
    
    public float floatValue ( ) {
        assert false : "should never reach here";
        return 0.0f;
    }

    public boolean isUndef( ) { return undef; }

    public Type type ( ) { return type; }

    public static Value mkValue (Type type) {
        if (type == Type.INT) return new IntValue( );
        if (type == Type.BOOL) return new BoolValue( );
        if (type == Type.CHAR) return new CharValue( );
        if (type == Type.FLOAT) return new FloatValue( );
        throw new IllegalArgumentException("Illegal type in mkValue");
    }
}
