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
        for(int i = 0; i < level; i++) {
            p.append(TAB);
        }
        p.append(v);
        p.append("\n");
    }

    public int getlevel() {
        return level; 
    }
    public void incLevel() {
        level++;
    }
    public void decLevel() {
        level--;
    }

    public String toString() {
        return p.toString();
    }
    public static void main(String args[]) {
        
    }
}
