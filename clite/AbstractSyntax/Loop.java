package clite.AbstractSyntax;

public class Loop extends Statement {
// Loop = Expression test; Statement body
    Expression test;
    Statement body;

    public Loop (Expression t, Statement b) {
        test = t; body = b;
    }
    
    public void display(String padding) {
        System.out.println(padding + "Loop:");
        System.out.println(padding + "  Test:");
        test.display(padding+"  ");
        System.out.println(padding + "Body:");
        body.display(padding + "  ");
    }
    
}
