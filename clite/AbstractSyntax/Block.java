package clite.AbstractSyntax;

import java.util.*;

public class Block extends Statement {
    // Block = Statement*
    //         (a Vector of members)
    public ArrayList<Statement> members = new ArrayList<Statement>();

}
