package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class PrettyPrinter implements Visitor {
    private final static String TAB = "    "; // four spaces

    private StringBuilder p;
    private int level;

    public PrettyPrinter() {
        p = new StringBuilder();
        level = 0;
    }

    public void visit(Visitable v){
        indent(level);

        p.append(v);
        
        p.append("\n");
    }

    protected void indent(int l) {
        for(int i = 0; i < l; i++) {
            p.append(TAB);
        }
    }

    public int getlevel() {
        return level; 
    }
    public void pushState() {
        level++;
    }
    public void popState() {
        level--;
    }

    public String toString() {
        return p.toString();
    }
    public static void main(String args[]) {
        
    }
}
