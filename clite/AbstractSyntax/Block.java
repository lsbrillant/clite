package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

import java.util.*;

public class Block extends Statement {
    // Block = Statement*
    //         (a Vector of members)
    public ArrayList<Statement> members = new ArrayList<Statement>();

    /*
    public void display(String padding){
        for(Statement s : members) {
            s.display(padding + "  ");
        }
    }
    */
    public String toString() {
        return "Block:";
    }

    public void accept(Visitor v) {
        v.visit(this);
        v.incLevel();
        for(Statement s : members) {
            s.accept(v);
        }
        v.decLevel();
    }

}
