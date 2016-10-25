package clite.AbstractSyntax;

import clite.Patterns.Visitor;
import clite.Patterns.Visitable;

public class Program implements Visitable { 
    // Progra m = Declarations decpart ; Block body
    Declarations decpart;
    Block body;

    public Program (Declarations d, Block b) {
        decpart = d;
        body = b;
    }

    public void display() {
        PrettyPrinter pp = new PrettyPrinter();
        this.accept(pp);
        System.out.println(pp);
    } 

    public String toString() {
        return "Program: "; 
    }
    public void accept( Visitor v ) { 
        v.visit(this);
        
        v.incLevel();

        decpart.accept(v);
        body.accept(v);
        
        v.decLevel();
    }

}
