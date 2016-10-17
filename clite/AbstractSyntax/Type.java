package clite.AbstractSyntax;

public class Type {
    // Type = int | bool | char | float 
    public final static Type INT = new Type("int");
    public final static Type BOOL = new Type("bool");
    public final static Type CHAR = new Type("char");
    public final static Type FLOAT = new Type("float");
    // final static Type UNDEFINED = new Type("undef");
    
    private String id;

    private Type (String t) { id = t; }

    public String toString ( ) { return id; }
}
