package clite.AbstractSyntax;

import java.util.*;


public class Declarations extends ArrayList<Declaration> {
    // Declarations = Declaration*
    // (a list of declarations d1, d2, ..., dn)
    
    public void display(String padding) {
        System.out.println(padding + "Declarations : ");
        for(Declaration d: this) {
            d.display(padding + "  ");
        }
        System.out.println();
    }

}

