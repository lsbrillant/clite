package clite.AbstractSyntax;

import java.util.*;
import clite.Patterns.Visitor;
import clite.Patterns.Visitable;


public class Declarations extends ArrayList<Declaration> implements Visitable {
    // Declarations = Declaration*
    // (a list of declarations d1, d2, ..., dn)
   /* 
    public void display(String padding) {
        System.out.println(padding + "Declarations : ");
        for(Declaration d: this) {
            d.display(padding + "  ");
        }
        System.out.println();
    }
    */
    public void accept(Visitor v) {
        v.visit(this);
        
        v.incLevel();
        for(Declaration d: this) {
            d.accept(v);
        }
        v.decLevel();
    }
    public String toString() {
        return "Declarations:";
    }

}

